package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.BoardDTO;
import model.service.BoardService;
import model.service.CommentService;
import page.Paging;

//@WebServlet("/board/detail")
public class DetailController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		BoardDTO dto = new BoardDTO();
		dto.setId(Integer.parseInt(id));
		
		BoardService service = new BoardService();
		
		BoardDTO data = service.detail(dto);
		
		
		req.setAttribute("data", data);
		
		String p = req.getParameter("p");
		int pageNumber = 1;
		if(p != null) {
			if(!p.isEmpty()) {
				pageNumber = Integer.parseInt(p);
			}
		}
		
		int pageListLimit = 10;
		
		CommentService cService = new CommentService();
		Paging paging = cService.getPage(pageNumber, pageListLimit, Integer.parseInt(id));		
		
		req.setAttribute("cmtPaging", paging);
		
		req.getRequestDispatcher("/WEB-INF/view/board/detail.jsp").forward(req, resp);
	}
	
}
