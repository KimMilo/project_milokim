package filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebFilter("/*") @WebFilter("url주소") , "/*"로 하면 /모든선택
public class LoggingFilter implements Filter {

	/* 초기화 시 1회 동작
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}*/
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
							// 요청				// 응답				// 다음 필터 연계 객체
			throws IOException, ServletException {
		// 참고로 HttpServlet 보다 Servlet이 상위임. 따라서 다운캐스팅 해야함.
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		// 요청에 대한 선행 작업은 이 곳에서 한다.
		String uriPath = request.getRequestURI(); //주소 입력창 주소(웹) 중 path정보만 추출
		String localPath = request.getServletContext().getRealPath(uriPath); // 실제경로
		String method = request.getMethod(); // get 인지 post인지 조회
		String query = request.getQueryString(); //?뒤 값을 조회
//		Map<String, String[]> params = request.getParameterMap(); // parameter 타입을 Map + 배열로 조회
//		for(Entry<String, String[]> e: params.entrySet()) {
//			System.out.println(e.getKey() + ":" + Arrays.toString(e.getValue()));
//		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss.SSS");
		Date now = new Date();
		
		System.out.println("LoggingFilter - Start");
//		System.out.println(localPath);
//		System.out.println(method);
//		System.out.println(query);
		
		System.out.printf("[%s] - %s: %s\n", df.format(now), method, uriPath);
		chain.doFilter(req, resp); // 다음 필터 및 서블릿이 실행될 수 있도록 한다.
		
		// 응답에 대한 후행 작업은 이 곳에서 한다.
		int status = response.getStatus(); 
		// 응답코드 조회 - 응답코드를 통해 상태확인 가능(200 이면 정상상태, 404는 찾을 수 없음, 302 리다이렉트 응답, 500번대부터는 서버에러)
		
		System.out.println("LoggingFilter - End");
//		System.out.println(location);
		
		now = new Date();
		switch(status / 100) {
			case 2: //200번대
				System.out.printf("[%s] - %s: %s\n", df.format(now), status, "정상응답");
				break;
			case 3: //300번대
				String location = response.getHeader("location"); // 302 리다이렉트가 되면 http response 의 header 내의 location 조회
				System.out.printf("[%s] - %s: %s -> %s\n", df.format(now), status, "리다이렉트 응답", location);
				break;
			case 4: //400번대
				System.out.printf("[%s] - %s: %s\n", df.format(now), status, "요청에러");
				break;
			case 5: //500번대
				System.out.printf("[%s] - %s: %s\n", df.format(now), status, "서버에러");
				break;
				
		}
	}
	
	/* 소멸 시 1회 동작
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}*/
	
}
