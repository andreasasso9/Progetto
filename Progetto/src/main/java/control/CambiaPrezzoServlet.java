package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ScarpaDataSource;

@WebServlet("/admin/CambiaPrezzoServlet")
public class CambiaPrezzoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scarpaId=request.getParameter("id");
		double prezzo=Double.parseDouble(request.getParameter("prezzo"));
		
		ScarpaDataSource ds=new ScarpaDataSource();
		try {
			ds.updatePrezzo(scarpaId, prezzo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/admin/add.jsp");
	}

}
