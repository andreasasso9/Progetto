package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.PasswordHash;
import Model.SignupDataSource;


@WebServlet(name = "SignupServlet", urlPatterns = "/Signup")
public class SignupServlet extends HttpServlet implements PasswordHash {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome, cognome, telefono, username, email, password;
		int età;
		
		nome=request.getParameter("nome");
		cognome=request.getParameter("cognome");
		età=Integer.parseInt(request.getParameter("età"));
		telefono=request.getParameter("telefono");
		username=request.getParameter("username");
		email=request.getParameter("email");
		password=request.getParameter("password");
		password=toHash(password);
		
		SignupDataSource ds=new SignupDataSource();
	}

}
