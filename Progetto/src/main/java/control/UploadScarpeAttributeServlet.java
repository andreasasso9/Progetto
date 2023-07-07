package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Scarpa;
import model.ScarpaDataSource;

@WebServlet("/admin/UploadScarpeAttributeServlet")
public class UploadScarpeAttributeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ScarpaDataSource ds=new ScarpaDataSource();
		
		try {
			Collection<Scarpa> scarpe=ds.doRetrieveAll();
			
			request.setAttribute("scarpe", scarpe);
			
			this.getServletContext().getRequestDispatcher("/common/index.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	

}
