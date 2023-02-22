package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.CommentDTO;
import model.dto.JoinDTO;
import model.service.CommentService;

/**
 * Servlet implementation class CommentAddController
 */
//@WebServlet("/CommentAddController")
public class CommentAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentAddController() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String comment = req.getParameter("comment");
		String bnum = req.getParameter("bnum");
		System.out.println(bnum);
				
		HttpSession session = req.getSession();
		JoinDTO data = (JoinDTO)session.getAttribute("user");
		CommentDTO dto = new CommentDTO();
		dto.setContext(comment);
		dto.setBnum(Integer.parseInt(bnum));
		dto.setWriter(data.getUserId());
		
		CommentService service = new CommentService();
		
		int result = service.insertComment(dto);
		if(result > 0) {
			resp.sendRedirect(req.getContextPath() + "/board/detail?id=" + bnum);
		} else {
			resp.sendRedirect(req.getContextPath() + "/error");
		}
	}

}
