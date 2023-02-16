package controller.bookmark;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.BookmarkDTO;
import model.dto.JoinDTO;
import model.service.BookmarkService;
import page.Paging;


/* class처럼 servlet을 우클릭 후 new 에서 생성할 수 있으며,
   이 때 클래스 패키지명과 클래스명 기재후 next하면 url-pattern 기재 -> next 하면 doGet, doPost 사용할거 체크하면 됨. 
   그리고 tomcat10부터 javax가 아닌 jakarta로 바껴서 이 부분은 수정해주면 됨 
   근데 자동생성보다 그냥 내가 만드는게 주석도 불필요하고 기본생성자, 직렬화 ID 도 불필요해서 수정할게 좀 있음.....
   */ 

// @WebServlet("/bookmark") :
// web.xml 의 <servlet></servlet> , <servlet-mapping></servlet-mapping> 이 부분을
// 톰캣서버에서 동작을 시킬 때 해당 어노테이션에 대해 매핑정보를 자동생성 및 인식하게 함.
// web.xml이 가장 먼저 동작하고 그 다음 어노테이션이 동작함.
// 이때, web.xml에 동일한 URL mapping 값이 있으면 충돌됨.
public class BookmarkController extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	// Object 객체 사용시 serializable 에 따른 직렬화 ID를 부여하는 부분인데, 
	// ID를 기준으로 같으면 직렬화 가능 다르면 직렬화가 되지 않음 이부분도 사실 없어도 무관함.


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		/* LoginCheckFilter로 대체
		 	if(session.getAttribute("login") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		 */
		
		String pageNumber = req.getParameter("p");
		int pNum;
		if(pageNumber == null || pageNumber.isEmpty()) {
			pNum = 1;
		} else {
			pNum = Integer.parseInt(pageNumber);
		}
		
		Cookie cookie = null;
		Cookie[] cookies = req.getCookies();
		for(Cookie c: cookies) {
			if(c.getName().equals("cntBookmark")) {
				cookie = c;
			}
		}
		
		String cnt = req.getParameter("cnt");
		if(cnt != null) {
			if(cnt.isEmpty()) {
				if(cookie != null) {
					cnt = cookie.getValue();
				} else {
					cnt = "10";
				}
			}
		} else {
			if(cookie != null) {
				cnt = cookie.getValue();
			} else {
				cnt = "10";
			}
		}
		
		cookie = new Cookie("cntBookmark", cnt);
		cookie.setMaxAge(60*60*24*5);
		resp.addCookie(cookie);
		
		
		JoinDTO joinData = (JoinDTO)session.getAttribute("user");
		BookmarkDTO dto = new BookmarkDTO();
		dto.setUserId(joinData.getUserId());
		
		BookmarkService service = new BookmarkService();
		//List<BookmarkDTO> bookmarkList = service.getPage(pNum, Integer.parseInt(cnt));
		//List<Integer> pageList = service.getPageList(Integer.parseInt(cnt));
		
		//int lastPageNumber = service.getLastPageNumber(Integer.parseInt(cnt));
		
//		int start = 0;
//		int end = 10;
//		if(pNum > (10 / 2) + 1) {
//			start = pNum - 6;
//			end = pNum + 4;
//		}
//		if(end > lastPageNumber) {
//			end = lastPageNumber;
//		}
//		pageList = pageList.subList(start, end);
		
		Paging paging = service.getPage(dto, pNum, Integer.parseInt(cnt));
		
		//req.setAttribute("data",bookmarkList);
		//req.setAttribute("pageList", pageList);
		//req.setAttribute("lastPageNumber", lastPageNumber);
		//req.setAttribute("cntBookmark", cnt);
		req.setAttribute("paging", paging);
		req.getRequestDispatcher("/WEB-INF/view/bookmark.jsp").forward(req, resp);
		
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String url = req.getParameter("url");
		//String name = req.getParameter("name");
		
		HttpSession session = req.getSession();
		
		/* LoginCheckFilter로 대체
	 	if(session.getAttribute("login") == null) {
		resp.sendRedirect(req.getContextPath() + "/login");
		return;
		}
		 */
		
		JoinDTO addData = (JoinDTO)session.getAttribute("user");
		BookmarkDTO dto = new BookmarkDTO();
		dto.setUserId(addData.getUserId());
		dto.setUrl(req.getParameter("url"));
		dto.setName(req.getParameter("name"));
		
		BookmarkService service = new BookmarkService();
		boolean result = service.add(dto);
		
		if(result) {
			resp.sendRedirect("./bookmark");
		} else {
			resp.sendRedirect("./fail");
		}
	}
}
