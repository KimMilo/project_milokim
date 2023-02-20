package controller.bookmark;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.BookmarkDTO;
import model.dto.JoinDTO;
import model.service.BookmarkService;
import page.Paging;


// @WebServlet("/bookmark") :
public class BookmarkController extends HttpServlet {
	 private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String pageNumber = req.getParameter("p");
		int pNum;
		if(pageNumber == null || pageNumber.isEmpty()) {
			pNum = 1;
		} else {
			pNum = Integer.parseInt(pageNumber);
		}
		
		Cookie cookie = null;
		Cookie[] cookies = req.getCookies();
		for(Cookie c: cookies) {
			if(c.getName().equals("cntBookmark")) {
				cookie = c;
			}
		}
		
		String cnt = req.getParameter("cnt");
		if(cnt != null) {
			if(cnt.isEmpty()) {
				if(cookie != null) {
					cnt = cookie.getValue();
				} else {
					cnt = "10";
				}
			}
		} else {
			if(cookie != null) {
				cnt = cookie.getValue();
			} else {
				cnt = "10";
			}
		}
		
		cookie = new Cookie("cntBookmark", cnt);
		cookie.setMaxAge(60*60*24*5);
		resp.addCookie(cookie);
		
		
		JoinDTO joinData = (JoinDTO)session.getAttribute("user");
		BookmarkDTO dto = new BookmarkDTO();
		dto.setUserId(joinData.getUserId());
		
		BookmarkService service = new BookmarkService();
		
		Paging paging = service.getPage(dto, pNum, Integer.parseInt(cnt));

		req.setAttribute("paging", paging);
		req.getRequestDispatcher("/WEB-INF/view/bookmark.jsp").forward(req, resp);
		
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		JoinDTO addData = (JoinDTO)session.getAttribute("user");
		BookmarkDTO dto = new BookmarkDTO();
		dto.setUserId(addData.getUserId());
		dto.setUrl(req.getParameter("url"));
		dto.setName(req.getParameter("name"));
		
		BookmarkService service = new BookmarkService();
		boolean result = service.add(dto);
		
		if(result) {
			resp.sendRedirect("./bookmark");
		} else {
			resp.sendRedirect("./fail");
		}
	}
}
