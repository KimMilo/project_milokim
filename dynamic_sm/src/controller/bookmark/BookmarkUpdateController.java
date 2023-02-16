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
	/**
	 * 사용자가 수정 요청한 id에 해당하는 데이터 조회 후
	 * 수정 폼이 있는 JSP 페이지를 제공하여 사용자가 데이터를
	 * 수정할 수 있게 한다.
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		/* LoginCheckFilter로 대체
	 	if(session.getAttribute("login") == null) {
		resp.sendRedirect(req.getContextPath() + "/login");
		return;
		}
		 */
		
		JoinDTO joinData = (JoinDTO)session.getAttribute("user");
		
		String id = req.getParameter("id");
		/* 사용자가 전달한 모든 파라미터는 숫자로 보일지라도 모두 문자임 */
		BookmarkDTO dto = new BookmarkDTO();
		dto.setId(Integer.parseInt(id));
		dto.setUserId(joinData.getUserId());
		
		
		BookmarkService service = new BookmarkService();
		dto = service.get(dto);
		/* 앞에 dto는 id, url, name이 담김 dto로 만들거고, 뒤에 dto는 id만 해당 */
		
		req.setAttribute("data", dto);
		req.getRequestDispatcher("/WEB-INF/view/bookmarkupdate.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		/* LoginCheckFilter로 대체
	 	if(session.getAttribute("login") == null) {
		resp.sendRedirect(req.getContextPath() + "/login");
		return;
		}
		 */
		
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
