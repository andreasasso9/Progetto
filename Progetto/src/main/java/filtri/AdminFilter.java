package filtri;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        HttpSession session = httpRequest.getSession();
        Boolean isAdmin=(Boolean) session.getAttribute("isAdmin");
        
        if (isAdmin!=null && isAdmin) {
        	chain.doFilter(request, response);
        } else {
        	httpResponse.sendRedirect(httpRequest.getContextPath()+"/common/index.jsp");
        }
    }

}
