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

		int result = service.add(dto);
		switch(result) {
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
