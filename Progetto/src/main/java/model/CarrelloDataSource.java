package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

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
		PreparedStatement psCarrelloId=null;

		String sqlEffettua="INSERT INTO effettua(username) VALUES(?)";
		String sqlCarrello="INSERT INTO ordine(username) VALUES(?)";
		String sqlHa="INSERT INTO ha(codiceOrdine, codiceScarpa) VALUES(?,?)";
		String getCarrelloId="SELECT MAX(codice) FROM ordine";

		try {
			con=ds.getConnection();
			con.setAutoCommit(false);

			psEffettua=con.prepareStatement(sqlEffettua);
			psEffettua.setString(1, bean.getUsername());
			psEffettua.executeUpdate();

			psCarrello=con.prepareStatement(sqlCarrello);
			psCarrello.setString(1, bean.getUsername());
			psCarrello.executeUpdate();
			con.commit();
			
			psCarrelloId=con.prepareStatement(getCarrelloId);
			ResultSet rs=psCarrelloId.executeQuery();
			rs.next();
			int codiceCarrello=rs.getInt("max(codice)");
			
			psHa=con.prepareStatement(sqlHa);
			psHa.setInt(1, codiceCarrello);
			for (Scarpa s:bean.getScarpe()) {
				psHa.setInt(2, s.getId());
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
				if (psEffettua!=null)
					psEffettua.close();
				if (psCarrelloId!=null)
					psCarrelloId.close();
			} finally {
				if (con!=null)
					con.close();
			}
		}
		return true;
	}

	@Override
	public boolean doDelete(String nome) throws SQLException {
		return false;
	}

	@Override
	public Carrello doRetrieveByKey(String code) throws SQLException {
		return null;
	}

	@Override
	public Collection<Carrello> doRetrieveAll() throws SQLException {
		Connection con=null;
		PreparedStatement psOrdine=null;
		PreparedStatement psHa=null;
		ResultSet rsOrdine=null;
		ResultSet rsHa=null;

		Collection<Carrello> ordini=new LinkedList<>();

		String sqlOrdine="SELECT * FROM ORDINE";
		String sqlHa="SELECT codiceScarpa FROM ha WHERE codiceOrdine=?";

		try {
			con=ds.getConnection();
			psHa=con.prepareStatement(sqlHa);
			psOrdine=con.prepareStatement(sqlOrdine);
			rsOrdine=psOrdine.executeQuery();
			
			while (rsOrdine.next()) {
				psHa.setInt(1, rsOrdine.getInt("codice"));
				rsHa=psHa.executeQuery();
				Carrello carrello=new Carrello();
				while (rsHa.next()) {
					Scarpa s=new Scarpa();
					s.setId(rsHa.getInt("codiceScarpa"));
					carrello.getScarpe().add(s);
				}
				
				carrello.setCodice(rsOrdine.getInt("codice"));
				carrello.setUsername(rsOrdine.getString("username"));
				
				ordini.add(carrello);
			}
		} finally {
			try {
				if (psOrdine!=null)
					psOrdine.close();
				if (psHa!=null)
					psHa.close();
			}finally {
				if (con!=null)
					con.close();
			}
		}
		return ordini;
	}


}
