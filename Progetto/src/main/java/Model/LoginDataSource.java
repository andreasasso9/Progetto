package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LoginDataSource {
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/progetto");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	public synchronized boolean checkUser(String username, String password) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String query="SELECT username, pass FROM utente WHERE username=? and pass=?";
		
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(query);
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			rs=ps.executeQuery();
			
			return rs.next();//ritorna true se ha trovato l'utente, false se username o password sono errati o l'utente non esiste
			
		} finally {
			try {
				if (ps!=null)
					ps.close();
				if (rs!=null)
					rs.close();
			} finally {
				if (con!=null)
					con.close();
			}
		}
	}
}
