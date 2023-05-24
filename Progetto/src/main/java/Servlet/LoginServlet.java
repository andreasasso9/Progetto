package Servlet;

import java.io.IOException;
import java.sql.SQLException;

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
		username=request.getParameter("username");
		password=request.getParameter("password");//la password deve essere codificata prima di essere controllata
		
		LoginDataSource checkLogin=new LoginDataSource();
		
		try {
			boolean check=checkLogin.checkUser(username, password);
			
			if (check) {
				HttpSession oldSession=request.getSession(false);
				
				if (oldSession!=null)
					oldSession.invalidate();
				
				HttpSession currentSession=request.getSession();
				currentSession.setAttribute("user", username);
				
				response.sendRedirect("index.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
		}
		
	}

}
