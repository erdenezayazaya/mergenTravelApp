package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter{
	
	private Logger logger = Logger.getLogger(AuthenticationFilter.class);

	public void init(FilterConfig fConfig) throws ServletException {
		logger.info("AuthenticationFilter initialized");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
				
		String uri = req.getRequestURI();
		logger.info("Requested Resource::"+uri);
	
        boolean loggedIn = session != null && session.getAttribute("User") != null;
       
        boolean loginRequest = false;
        
        if(uri.endsWith("login.jsp") || uri.equals("/travelApp/") || uri.equals("/travelApp/Login") 
        		|| uri.contains("Register") || uri.endsWith("register.jsp")
        		|| uri.endsWith("/travelApp/library/jquery/jquery-3.2.1.min.js") || uri.endsWith("/bootstrap-3.3.7-dist/js/bootstrap.min.js")
        		|| uri.endsWith("/travelApp/js/script.js") || uri.endsWith("/travelApp/style/style.css") 
        		|| uri.endsWith("/travelApp/library/bootstrap-3.3.7-dist/css/bootstrap.min.css") || uri.endsWith("background-min.png"))
        	loginRequest = true;
     
        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("login.jsp");
        }
	}

	public void destroy() {
		//close any resources here
	}
	
}
