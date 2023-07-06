package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		int taglia=Integer.parseInt(request.getParameter("taglia"));
		double prezzo=Double.parseDouble(request.getParameter("prezzo"));
		
		Scarpa scarpa=new Scarpa();
		scarpa.setNome(nome);
		scarpa.setTaglia(taglia);
		scarpa.setPrezzo(prezzo);
		
		ScarpaDataSource ds=new ScarpaDataSource();
		
		try {
			ds.doSave(scarpa);
			
			Collection<Scarpa> scarpe=ds.doRetrieveAll();
			request.setAttribute("scarpe", scarpe);
			
			getServletContext().getRequestDispatcher("index.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
