package control;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.Carrello;
import DTO.ScarpaOrdine;

@WebServlet("/common/RimuoviDalCarrello")
public class RimuoviDalCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Carrello carrello=(Carrello) session.getAttribute("carrello");
		String scarpaId=request.getParameter("scarpaId");
		
		Optional<ScarpaOrdine>scarpaDaRimuovere=carrello.getScarpe().parallelStream().filter(s->s.getId()==Integer.parseInt(scarpaId)).findFirst();
		carrello.getScarpe().remove(scarpaDaRimuovere.get());
		session.setAttribute("carrello", carrello);
		response.sendRedirect(request.getContextPath()+"/common/carrello.jsp");
	}

}
