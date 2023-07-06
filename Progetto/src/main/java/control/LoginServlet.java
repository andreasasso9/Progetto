package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginDataSource;
import model.StringFunctions;


@WebServlet(name = "LoginServlet", urlPatterns = "/common/Login")
public class LoginServlet extends HttpServlet implements StringFunctions{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username;
		String password;
		String errors="";
		boolean check=false;
		RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
		
		username=request.getParameter("username");
		password=request.getParameter("password");
		
		LoginDataSource checkLogin=new LoginDataSource();

		try {
			if (username.isBlank()) {
				errors+="Inserisci la Username<br>";
			}

			if (password.isBlank())
				errors+="Inserisci la Password<br>";
			
			if (errors.isEmpty()) {
				password=toHash(password);
				check=checkLogin.checkUser(username, password);

				if (check) {
					HttpSession session=request.getSession();
					String admin=getServletContext().getInitParameter("admin");
					session.setAttribute("isAdmin", username.equals(admin));
					
					username=filter(username);
					session.setAttribute("user", username);
					
					response.sendRedirect(request.getContextPath()+"/common/index.jsp");
				} else {
					errors+="Username o password errati";
					request.setAttribute("errors", errors);
					dispatcher.forward(request, response);
				}
			} else {
				request.setAttribute("errors", errors);
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
