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
		PreparedStatement psCarrello=null;
		PreparedStatement psHa=null;
		
		String sqlCarrello="INSERT INTO ordine(codice) VALUES(?)";
		String sqlHa="INSERT INTO ha(codiceScarpa) VALUES(?)";
		
		try {
			con=ds.getConnection();
			con.setAutoCommit(false);
			
			psCarrello=con.prepareStatement(sqlCarrello);
			
			psCarrello.setInt(1, bean.getCodice());
			
			psHa=con.prepareStatement(sqlHa);
			for (Scarpa s:bean.getScarpe()) 
				psHa.setInt(1, s.getId());
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (psHa!=null)
					psHa.close();
				if (psCarrello!=null)
					psCarrello.close();
			} finally {
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
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
