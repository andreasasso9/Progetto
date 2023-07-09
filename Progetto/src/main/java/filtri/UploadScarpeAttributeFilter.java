package filtri;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import DTO.Scarpa;
import model.ScarpaDataSource;

/**
 * Servlet Filter implementation class UploadScarpeAttributeFilter
 */
@WebFilter(filterName = "UploadScarpeAttributeFilter", urlPatterns = "/*")
public class UploadScarpeAttributeFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ScarpaDataSource ds=new ScarpaDataSource();

		try {
			Collection<Scarpa> scarpe=ds.doRetrieveAll();

			getServletContext().setAttribute("scarpe", scarpe);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		chain.doFilter(request, response);
	}

}
