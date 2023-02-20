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
		req.getRequestDispatcher("/WEB-INF/view/myinfo.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password = req.getParameter("password");
		String changePass = req.getParameter("changePass");
		String email = req.getParameter("email");
		
		HttpSession session = req.getSession();
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
			resp.sendRedirect(req.getContextPath() + "/logout" );
		}
	}
}
