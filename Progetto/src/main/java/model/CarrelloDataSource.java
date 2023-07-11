package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import DTO.Carrello;
import DTO.Scarpa;

public class CarrelloDataSource implements IBeanDAO<Carrello>{

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

	@Override
	public boolean doSave(Carrello bean) throws SQLException {
		Connection con=null;
		PreparedStatement psEffettua=null;
		PreparedStatement psCarrello=null;
		PreparedStatement psHa=null;
		
		String sqlEffettua="INSEERT INTO effettua(username) VALUES(?)";
		String sqlCarrello="INSERT INTO ordine(username) VALUES(?)";
		String sqlHa="INSERT INTO ha(codiceScarpa) VALUES(?)";
		
		
		try {
			con=ds.getConnection();
			con.setAutoCommit(false);
			
			psEffettua=con.prepareStatement(sqlEffettua);
			psEffettua.setString(1, bean.getUsername());
			psEffettua.executeUpdate();
			
			psCarrello=con.prepareStatement(sqlCarrello);
			psCarrello.setString(1, bean.getUsername());
			psCarrello.executeUpdate();
			
			psHa=con.prepareStatement(sqlHa);
			for (Scarpa s:bean.getScarpe()) {
				psHa.setInt(1, s.getId());
				psHa.executeUpdate();
			}
			con.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if (psHa!=null)
					psHa.close();
				if (psCarrello!=null)
					psCarrello.close();
			} finally {
				if (con!=null)
					con.close();
			}
		}
		return true;
	}

	@Override
	public boolean doDelete(String nome) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Carrello doRetrieveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Carrello> doRetrieveAll() throws SQLException {
		return null;
	}
	
	
}
