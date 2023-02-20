package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.BoardService;
import page.Paging;


//@WebServlet("/board")
public class IndexController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("p");
		int pageNumber = 1;
		if(p != null) {
			if(!p.isEmpty()) {
				pageNumber = Integer.parseInt(p);
			}
		}
		
		Cookie cookie = null;
		Cookie[] cookies = req.getCookies();
		for(Cookie c: cookies) {
			if(c.getName().equals("cnt")) {
				cookie = c;
			}
		}
		String cnt = req.getParameter("cnt");
		int pageListLimit = 10;
		if(cnt != null) {
			if(!cnt.isEmpty()) {
				pageListLimit = Integer.parseInt(cnt);
				cookie = new Cookie("cnt", cnt);
				cookie.setMaxAge(60*60*24*5);
				resp.addCookie(cookie);	
			}
		} else {
			if(cookie != null) {
				pageListLimit = Integer.parseInt(cookie.getValue());
			}
		}
		
		BoardService service = new BoardService();
		Paging paging = service.getPage(pageNumber, pageListLimit);		
		
		req.setAttribute("paging", paging);
		req.getRequestDispatcher("/WEB-INF/view/board/index.jsp").forward(req, resp);
	}

}
