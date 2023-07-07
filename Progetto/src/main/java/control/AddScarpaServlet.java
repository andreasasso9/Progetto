package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DTO.Scarpa;
import model.ScarpaDataSource;

@WebServlet("/admin/AddScarpaServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddScarpaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		int taglia=Integer.parseInt(request.getParameter("taglia"));
		double prezzo=Double.parseDouble(request.getParameter("prezzo"));
		Collection<Part> foto=request.getParts();
		
		Scarpa scarpa=new Scarpa();
		scarpa.setNome(nome);
		scarpa.setTaglia(taglia);
		scarpa.setPrezzo(prezzo);
		
		ScarpaDataSource ds=new ScarpaDataSource();
		
		try {
			ds.doSave(scarpa);
			
			
			Collection<Scarpa> scarpe=ds.doRetrieveAll();
			request.setAttribute("scarpe", scarpe);
			
			this.getServletContext().getRequestDispatcher("/common/index.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
