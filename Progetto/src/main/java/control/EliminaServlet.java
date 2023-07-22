package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ScarpaDataSource;

@WebServlet("/admin/EliminaServlet")
public class EliminaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idScarpa=request.getParameter("scarpa");
		ScarpaDataSource ds=new ScarpaDataSource();
		try {
			ds.doDelete(idScarpa);
			
			response.sendRedirect(request.getContextPath()+"/admin/delete.jsp");
		} catch (SQLException e) {
		}
	}

}
