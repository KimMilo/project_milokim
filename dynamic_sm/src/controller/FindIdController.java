package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.JoinDTO;
import model.service.JoinService;

/**
 * Servlet implementation class FindIdController
 */
@WebServlet("/findId")
public class FindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindIdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/findid.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		JoinDTO dto = new JoinDTO();
		dto.setEmail(email);
		
		JoinService service = new JoinService();
		HttpSession session = request.getSession();
		JoinDTO data = (JoinDTO)session.getAttribute("user");
		
		int result = service.getUserEmail(dto, data);
		if(result > 0) {
			PrintWriter out = response.getWriter();
			out.append("ok");
			out.flush();
			out.close();
			
		} else {
			request.setAttribute("error", "입력하신 이메일의 아이디는 존재하지 않습니다.");
			request.getRequestDispatcher("/WEB-INF/view/findid.jsp").forward(request, response);
		}
		
	}

}
