package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		doGet(request, response);
		String email = request.getParameter("email");
		
		JoinDTO dto = new JoinDTO();
		dto.setEmail(email);
		
		JoinService service = new JoinService();
		
//		int result = service.getUserEmail();
//		if(result > 0) {
//			request.getRequestDispatcher("/WEB-INF/view/findid.jsp").forward(request, response);
//			
//		} else {
//			request.setAttribute("error", "입력하신 이메일의 아이디는 존재하지 않습니다.");
//			request.getRequestDispatcher("/WEB-INF/view/findid.jsp").forward(request, response);
//		}
	}

}
