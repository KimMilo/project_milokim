package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.JoinDTO;
import model.service.JoinService;

public class MyinfoController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// myinfo 페이지를 로그인 상태에서만 접근할 수 있게 제어
		
		/* 
			LoginCheckFilter로 대체
 			HttpSession session = req.getSession();
	 		if(session.getAttribute("login") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
				return;
			}
		 	*/
		req.getRequestDispatcher("/WEB-INF/view/myinfo.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password = req.getParameter("password");
		String changePass = req.getParameter("changePass");
		String email = req.getParameter("email");
		
		HttpSession session = req.getSession();
		//참고로 세션정보도 서버가 껐다 켜지면 사라짐
		JoinDTO data = (JoinDTO)session.getAttribute("user");
		JoinDTO updateData = new JoinDTO();
		
		updateData.setPassword(changePass);
		updateData.setEmail(email);
		
		JoinService service = new JoinService();
		JoinDTO result = service.update(data, updateData, password);
		if(result == null) {
			req.setAttribute("error", "현재 패스워드가 일치하지 않거나 저장에 문제가 발생하였습니다.");
			req.getRequestDispatcher("/WEB-INF/view/myinfo.jsp").forward(req, resp);
		} else {
			// 문제 없으면 로그아웃 : 경로를 다음과같이 지정하면 로그아웃하고 메인페이지로 돌려보내줌.(기존에 그렇게 코딩함)
			resp.sendRedirect(req.getContextPath() + "/logout" );
		}
	}
}
