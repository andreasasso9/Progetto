package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CheckFields {
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
	
	public synchronized boolean checkUsername(String username) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean x;
		
		String query="SELECT username FROM utente WHERE username=?";
		
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(query);
			
			ps.setString(1, username);
			
			rs=ps.executeQuery();
			
			x=rs.next();//restituisce true se ha trovato l'utente, false se username non esiste
			
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
		return !x;
	}
	
	public synchronized boolean checkEmail(String email) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean x;
		
		String query="SELECT email FROM utente WHERE email=?";
		
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(query);
			
			ps.setString(1, email);
			
			rs=ps.executeQuery();
			x=rs.next();//restituisce true se ha trovato l'utente, false se email non esiste
			
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
		return !x;
	}
}
