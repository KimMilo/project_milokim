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

//@WebServlet("/bookmark/update")
public class BookmarkUpdateController extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		
		JoinDTO joinData = (JoinDTO)session.getAttribute("user");
		
		String id = req.getParameter("id");
		BookmarkDTO dto = new BookmarkDTO();
		dto.setId(Integer.parseInt(id));
		dto.setUserId(joinData.getUserId());
		
		
		BookmarkService service = new BookmarkService();
		dto = service.get(dto);
		
		req.setAttribute("data", dto);
		req.getRequestDispatcher("/WEB-INF/view/bookmarkupdate.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		JoinDTO updateData = (JoinDTO)session.getAttribute("user");
		
		String id = req.getParameter("id");
		String url = req.getParameter("url");
		String name = req.getParameter("name");
		
		BookmarkDTO dto = new BookmarkDTO();
		dto.setId(Integer.parseInt(id));
		dto.setUserId(updateData.getUserId());
		dto.setUrl(url);
		dto.setName(name);
		
		BookmarkService service = new BookmarkService();
		boolean result = service.update(dto);
		
		if(result) {
			resp.sendRedirect("../bookmark");
		} else {
			resp.sendRedirect("../fail");
		}	
	}
}
