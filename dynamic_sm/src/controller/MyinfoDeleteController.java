package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.JoinDTO;
import model.service.JoinService;

/**
 * Servlet implementation class MyinfoDeleteController
 */
//@WebServlet("/myinfo/delete")
public class MyinfoDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyinfoDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.getRequestDispatcher("/WEB-INF/view/myinfodelete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		JoinDTO dto = new JoinDTO();
		dto.setUserId(userId);
		dto.setPassword(password);
		
		HttpSession session = request.getSession();
		JoinDTO data = (JoinDTO)session.getAttribute("user");
		int result = new JoinService().deleteUser(dto, data);
		if(result == 1) {
			response.sendRedirect(request.getContextPath() + "/logout");
		} else {
			request.setAttribute("error", "비밀번호가 다릅니다. 다시 입력해주세요.");
			request.getRequestDispatcher("/WEB-INF/view/myinfodelete.jsp").forward(request, response);
		}
	}

}
