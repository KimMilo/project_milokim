package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.JoinDTO;
import model.service.JoinService;

/**
 * Servlet implementation class DupIdCheckController
 */
@WebServlet("/dupid")
public class DupIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DupIdCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("userId");
		JoinDTO dto = new JoinDTO();
		dto.setUserId(id);
		
		JoinService service = new JoinService();
		int result = service.dupIdChk(dto);
		
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.append("fail");
		} else {
			out.append("ok");
		}
		out.flush();
		out.close();
	}

}
