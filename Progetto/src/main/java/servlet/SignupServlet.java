package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CheckFields;
import model.SignupDataSource;
import model.StringFunctions;


@WebServlet(name = "SignupServlet", urlPatterns = "/common/Signup")
public class SignupServlet extends HttpServlet implements StringFunctions {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome, cognome, telefoni[], username, email, password, età;
		boolean usernameValid=false, emailValid=false;
		RequestDispatcher dispatcherToSignup=request.getRequestDispatcher("signup.jsp");

		nome=request.getParameter("nome");
		cognome=request.getParameter("cognome");
		età=request.getParameter("età");
		telefoni=request.getParameterValues("telefono");
		username=request.getParameter("username");
		email=request.getParameter("email");
		password=request.getParameter("password");

		String errors="";

		SignupDataSource ds=new SignupDataSource();
		CheckFields check=new CheckFields();

		try {
			if (nome.isBlank())
				errors+="Inserisci il nome<br>";
			if (cognome.isBlank())
				errors+="Inserisci il cognome<br>";
			if (età.isBlank())
				errors+="Inserisci l'età<br>";
			if (username.isBlank())
				errors+="Inserisci la username<br>";
			if (email.isBlank())
				errors+="Inserisci la email<br>";
			if (password.isBlank())
				errors+="Inserisci la password<br>";
			else if(password.length()<8)
				errors+="La password deve essere lunga almeno 8 caratteri";

			if (errors.isEmpty()) {
				usernameValid=check.checkUsername(username);
				emailValid=check.checkEmail(email);

				if (!usernameValid)
					errors+="Questa username è già esistente<br>";
				if (!emailValid)
					errors+="Questa e-mail è già registrata<br>";

				if (errors.isEmpty()) {
					password=toHash(password);
					ds.insertNewUser(nome, cognome, username, email, password, Integer.parseInt(età), telefoni);
					response.sendRedirect(request.getContextPath()+"/common/index.jsp");
					return;
				}else {
					request.setAttribute("errors", errors);
					dispatcherToSignup.forward(request, response);
					return;
				}
			}else {
				request.setAttribute("errors", errors);
				dispatcherToSignup.forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
