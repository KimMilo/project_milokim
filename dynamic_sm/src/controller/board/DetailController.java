package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.BoardDTO;
import model.service.BoardService;

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
		
		req.getRequestDispatcher("/WEB-INF/view/board/detail.jsp").forward(req, resp);
	}
	
}
