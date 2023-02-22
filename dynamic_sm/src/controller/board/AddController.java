package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.BoardDTO;
import model.dto.JoinDTO;
import model.service.BoardService;

//@WebServlet("/board/add")
public class AddController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/board/add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String context = req.getParameter("context");
		String btype = req.getParameter("btype");
		
		HttpSession session = req.getSession();
		JoinDTO data = (JoinDTO)session.getAttribute("user");
		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setContext(context);
		dto.setBtype(btype);
		dto.setWriter(data.getUserId());;
		
		BoardService service = new BoardService();
		
		
		int result = service.insertBoard(dto);
		if(result > 0) {
			resp.sendRedirect(req.getContextPath() + "/board");
		} else {
			req.setAttribute("error", "게시글 작성 중 에러가 발생하였습니다. 다시 시도 바랍니다.");
			req.getRequestDispatcher("/WEB-INF/view/board/add.jsp").forward(req, resp);
		}
		
	}
}
