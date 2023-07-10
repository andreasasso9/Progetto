package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.Carrello;
import DTO.Scarpa;
import model.ScarpaDataSource;

@WebServlet("/common/AggiungiCarrelloServlet")
public class AggiungiCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scarpaId=request.getParameter("scarpaId");
		HttpSession session=request.getSession();
		
		Carrello carrello=(Carrello) session.getAttribute("carrello");
		if (carrello==null)
			carrello=new Carrello();
		ScarpaDataSource sds=new ScarpaDataSource();
		try {
			Scarpa s= sds.doRetrieveByKey(scarpaId);
			carrello.getScarpe().add(s);
			session.setAttribute("carrello", carrello);
			response.sendRedirect(request.getContextPath()+"/common/index.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
