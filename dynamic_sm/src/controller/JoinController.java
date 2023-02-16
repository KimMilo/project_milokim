package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.JoinDTO;
import model.service.JoinService;

public class JoinController extends HttpServlet  {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		
		JoinDTO dto = new JoinDTO();
		dto.setUserId(userId);
		dto.setPassword(password);
		dto.setEmail(email);
		
		JoinService service = new JoinService();
		/*
		boolean result = service.add(dto);
		if(result) {
			resp.sendRedirect("./login");
		} else {
			//resp.sendRedirect("./join");
			// sendRedirect는 join으로 가도 이전 입력 데이터는 저장 되지 않음.
			
			 실패했을 때 기존 고객 입력데이터를 복구하고 싶은 경우
			   1. 기존 parameter를 활용할 수 있도록 join.jsp를 포워딩하고
			   2. join.jsp에 복구하고 싶은 데이터에 value값을 ${param.해당input의 name}을 활용하여 지정하면 됨. 
			   	  ex) email은 가입 에러나도 기존 값 복구하고자 할 경우 ${param.email}
			    
			req.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(req, resp);
			
		}
		*/
		/* 좀더 세분화하여 에러메시지 띄우기 */
		int result = service.add(dto);
		switch(result) {
		/* 절대 경로의 contextPath()-여기서는 /web01를 불러와서 절대경로화 하기. */
			case 1:
				resp.sendRedirect(req.getContextPath() + "/login"); break;
			case -1:
				resp.sendRedirect(req.getContextPath() + "/error"); break;
			case -2:
				req.setAttribute("error", "동일한 아이디가 사용중입니다.");
				req.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(req, resp);
				break;
		}
	}
}
