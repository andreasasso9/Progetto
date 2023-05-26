package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.LoginDataSource;


@WebServlet(name = "LoginServlet", urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username;
		String password;
		List<String> errors=new ArrayList<>();
		boolean check=false;
		RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
		
		username=request.getParameter("username");
		password=request.getParameter("password");//la password deve essere codificata prima di essere controllata

		LoginDataSource checkLogin=new LoginDataSource();

		try {
			if (username.isBlank()) {
				errors.add("Inserisci la Username");
			}

			if (password.isBlank())
				errors.add("Inserisci la Password");

			if (errors.isEmpty()) {
				check=checkLogin.checkUser(username, password);

				if (check) {
					HttpSession oldSession=request.getSession(false);

					if (oldSession!=null)
						oldSession.invalidate();

					HttpSession currentSession=request.getSession();
					currentSession.setAttribute("user", username);

					response.sendRedirect("index.jsp");
				} else {
					request.setAttribute("notfound", "Username o Password errati");
					dispatcher.forward(request, response);
					return;
				}
			} else {
				request.setAttribute("errors", errors);
				dispatcher.forward(request, response);
				return;
			}
		} catch (SQLException e) {
		}

	}

}
