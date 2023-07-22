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

public class SecureFilter extends HttpFilter implements Filter {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		HttpServletResponse httpServletResponse=(HttpServletResponse) response;
		
		if (!httpServletRequest.isSecure()) {
			String url="https://"+httpServletRequest.getServerName()+httpServletRequest.getRequestURI();
			if (httpServletRequest.getQueryString()!=null)
				url+="?"+httpServletRequest.getQueryString();
			
			httpServletResponse.sendRedirect(url);
			return;
		}
			
		chain.doFilter(request, response);
	}

}
