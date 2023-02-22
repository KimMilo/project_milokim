package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.CommentDTO;
import model.dto.JoinDTO;
import model.service.BoardService;

public class CommentDeleteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		JoinDTO data = (JoinDTO)session.getAttribute("user");
		
		String id = req.getParameter("id");
		String bnum = req.getParameter("bnum");
		
		CommentDTO dto = new CommentDTO();
		dto.setId(Integer.parseInt(id));
		dto.setWriter(data.getUserId());
		
		BoardService service = new BoardService();
		int result = service.deleteComment(dto);
		
		if(result > 0) {
			resp.sendRedirect(req.getContextPath() + "/board/detail?id=" + bnum);
		} else {
			req.setAttribute("error", "게시글 삭제 중 에러가 발생하였습니다. 다시 시도 바랍니다.");
			req.getRequestDispatcher("/WEB-INF/view/board/detail.jsp").forward(req, resp);
		}
	}
}
