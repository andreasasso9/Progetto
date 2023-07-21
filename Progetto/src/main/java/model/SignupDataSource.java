package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SignupDataSource {
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/progetto");

		} catch (NamingException e) {
		}
	}
	
	public synchronized void insertNewUser(String nome, String cognome, String username, String email, String password, int età, String[] telefoni) throws SQLException {
		Connection con=null;
		PreparedStatement psUser=null;
		PreparedStatement psTel=null;
		
		String userQuery="INSERT INTO utente(username, email, pass, nome, cognome, età) VALUES(?,?,?,?,?,?)";
		String telQuery="INSERT INTO telefono(num, username) VALUES(?,?)";
		
		try {
			con=ds.getConnection();
			con.setAutoCommit(false);
			
			psUser=con.prepareStatement(userQuery);
			
			
			psUser.setString(1, username);
			psUser.setString(2, email);
			psUser.setString(3, password);
			psUser.setString(4, nome);
			psUser.setString(5, cognome);
			psUser.setInt(6, età);
			
			psUser.executeUpdate();
			
			psTel=con.prepareStatement(telQuery);
			psTel.setString(2, username);
			for (String s:telefoni) {
				psTel.setString(1, s);
				psTel.executeUpdate();
			}
			con.commit();
		} finally {
			try {
				if (psUser!=null)
					psUser.close();
				if (psTel!=null)
					psTel.close();
			} finally {
				if (con!=null)
					con.close();
			}
		}
	}
}
