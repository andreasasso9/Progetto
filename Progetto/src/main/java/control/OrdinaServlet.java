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
import model.CarrelloDataSource;

@WebServlet("/common/OrdinaServlet")
public class OrdinaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CarrelloDataSource cds=new CarrelloDataSource();
		HttpSession session=request.getSession();
		Carrello carrello=(Carrello) session.getAttribute("carrello");
		try {
			cds.doSave(carrello);
			session.setAttribute("carrello", null);
			response.sendRedirect(request.getContextPath()+"/common/index.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
