package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.JoinDTO;
import model.service.JoinService;

public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("login") != null) {
			if((boolean)session.getAttribute("login")) {
				resp.sendRedirect(req.getContextPath() + "/");
				return;
			}
		} 
		
		Cookie[] cookies = req.getCookies();
		for(Cookie c: cookies) {
			if(c.getName().equals("remember")) {
				req.setAttribute("rememberId", c.getValue());
			}
		}
		req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		
		JoinDTO dto = new JoinDTO();
		dto.setUserId(userId);
		dto.setPassword(password);
		
		JoinService service = new JoinService();
		JoinDTO data = service.login(dto);
		
		if(data == null) {
			req.setAttribute("error", "아이디 또는 패스워드가 잘못되었습니다.");
			req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
			
		} else {
			session.setAttribute("login", true);
			session.setAttribute("user", data);
			Cookie cookie = new Cookie("remember", userId);
			if(remember != null) {
				cookie.setMaxAge(60*60*24);
			} else {
				cookie.setMaxAge(0);
			}
			resp.addCookie(cookie);
			resp.sendRedirect(req.getContextPath() + "/");
		}
		
	}
}
