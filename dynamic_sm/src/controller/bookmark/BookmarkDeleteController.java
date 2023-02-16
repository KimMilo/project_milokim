package controller.bookmark;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.BookmarkDTO;
import model.dto.JoinDTO;
import model.service.BookmarkService;

public class BookmarkDeleteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		/* LoginCheckFilter로 대체
	 	if(session.getAttribute("login") == null) {
		resp.sendRedirect(req.getContextPath() + "/login");
		return;
		}
		 */
		
		JoinDTO joinData = (JoinDTO)session.getAttribute("user");
		
		String id = req.getParameter("id");
		
		BookmarkDTO dto = new BookmarkDTO();
		dto.setId(Integer.parseInt(id));
		dto.setUserId(joinData.getUserId());
		
		BookmarkService service = new BookmarkService();
		boolean result = service.remove(dto);
		
		if(result) {
			resp.sendRedirect("../bookmark");
		} else {
			resp.sendRedirect("../fail");
		}
	}
}
