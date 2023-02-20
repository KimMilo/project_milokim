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

// @WebFilter("") 
public class LoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		String uriPath = request.getRequestURI();
		String localPath = request.getServletContext().getRealPath(uriPath);
		String method = request.getMethod();
		String query = request.getQueryString();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss.SSS");
		Date now = new Date();
		
		System.out.println("LoggingFilter - Start");

		
		System.out.printf("[%s] - %s: %s\n", df.format(now), method, uriPath);
		chain.doFilter(req, resp); 
		
		int status = response.getStatus(); 
		
		System.out.println("LoggingFilter - End");
		
		now = new Date();
		switch(status / 100) {
			case 2: 
				System.out.printf("[%s] - %s: %s\n", df.format(now), status, "정상응답");
				break;
			case 3: 
				String location = response.getHeader("location"); // 302 리다이렉트가 되면 http response 의 header 내의 location 조회
				System.out.printf("[%s] - %s: %s -> %s\n", df.format(now), status, "리다이렉트 응답", location);
				break;
			case 4: 
				System.out.printf("[%s] - %s: %s\n", df.format(now), status, "요청에러");
				break;
			case 5: 
				System.out.printf("[%s] - %s: %s\n", df.format(now), status, "서버에러");
				break;
				
		}
	}

}
