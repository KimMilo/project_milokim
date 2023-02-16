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
		/* session 활용 */
		HttpSession session = req.getSession();
		
		if(session.getAttribute("login") != null) { // null 이면 로그인페이지가 보여야하니까
			if((boolean)session.getAttribute("login")) {//login 세션 값이 true니까 boolean 다운캐스팅
				resp.sendRedirect(req.getContextPath() + "/");
				// 로그인이 되어있으면 로그인 링크 클릭해도 /web01 메인페이지로 돌아옴.
				return; // 함수 종료의 의미 return
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
		//Cookie cookie = new Cookie("쿠키명", "쿠키값"); import jakarta.servlet.http.Cookie;//
		
		/* 쿠키 설정하기
		Cookie cookie = new Cookie("name","value");
		cookie.setMaxAge(60*60*24); //유효시간 설정 (단위는 초단위)
		resp.addCookie(cookie);
		*/
		
		//개발자 모드(f12)에서 --> application --> cookies 저장된 cookie가 있음.
		//해당 프로젝트 내의 어떠한 브라우저 페이지에서도 쿠키는 같이 저장되어있음.
		
		/* 전달된 쿠키정보를 받기 위한 방법 
		Cookie[] cookies = req.getCookies(); //전달된 쿠키정보를 받음.
		for(Cookie c: cookies) {
			System.out.println(c.getName() + "|" + c.getValue());
		}*/
		
/* ---------------------------------------------------------------------- */
		/* Session 정보 활용 
		 	- HttpSession session = req.getSession(); : 세션 객체 반환 없으면 새로 생성하여 반환
		 		req.getSession(); session정보(세션ID)가 없으면 자동생성해줌.
		 		이 때, req.getSession(false);를 하면 자동생성 안함. null값이 저장됨.
		 	
		 	- session.getAttribute("속성명"); : 세션 객체에 저장된 속성값을 속성명으로 찾아서 반환
			- session.setAttribute("속성명", "속성값"); : 세션 객체에 속성명, 속성값 저장
			- session.removeAttribute("속성명"); : 세션 객체에 저장된 속성값을 속성명으로 찾아서 삭제
			- session.invalidate(); : 세션 객체 삭제
			- session.setMaxInactiveInterval(0); : 세션 객체 유지시간 설정
			
			추가적으로,
			request.getAttribute / request.setAttribute는 설정 후 응답되면 끝이지만,
			session에서 활용하면 서버 메모리에 세션정보가 저장되어 항상 활용 및 다른 브라우저 페이지에서도 가능함.
		 */
		HttpSession session = req.getSession();
		
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		
		/*
		  System.out.println("기억하기 체크박스 값 확인 : " + remember);
		  체크박스는 체크되어있으면 값은 on , 체크안되어있으면 값은 null 임
		 */
		
		
		JoinDTO dto = new JoinDTO();
		dto.setUserId(userId);
		dto.setPassword(password);
		
		JoinService service = new JoinService();
		JoinDTO data = service.login(dto);
		
		if(data == null) { //로그인실패
			req.setAttribute("error", "아이디 또는 패스워드가 잘못되었습니다.");
			req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
			
		} else { //로그인성공
			session.setAttribute("login", true); // 로그인 상태를 기록하기 위한 속성
			session.setAttribute("user", data); // 로그인 계정 정보를 기록하기 위한 속성
			Cookie cookie = new Cookie("remember", userId);
			if(remember != null) {
				cookie.setMaxAge(60*60*24);
			} else {
				cookie.setMaxAge(0); //유효시간을 0으로 설정하면 쿠키를 클라이언트에서 삭제함.
			}
			resp.addCookie(cookie);
			resp.sendRedirect(req.getContextPath() + "/");
		}
		
	}
}
