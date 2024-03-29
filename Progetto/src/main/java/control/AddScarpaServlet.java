package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Scarpa;
import model.ScarpaDataSource;

@WebServlet("/admin/AddScarpaServlet")
public class AddScarpaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		double prezzo=Double.parseDouble(request.getParameter("prezzo"));
		
		Scarpa scarpa=new Scarpa();
		scarpa.setNome(nome);
		scarpa.setPrezzo(prezzo);
		
		ScarpaDataSource ds=new ScarpaDataSource();
		
		try {
			ds.doSave(scarpa);
			
			response.sendRedirect(request.getContextPath()+"/admin/add.jsp");
		} catch (SQLException e) {
		}
	}

}
