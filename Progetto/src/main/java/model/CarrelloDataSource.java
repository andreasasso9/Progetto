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
import DTO.ScarpaOrdine;

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
		PreparedStatement psTaglia=null;

		String sqlEffettua="INSERT INTO effettua(username) VALUES(?)";
		String sqlCarrello="INSERT INTO ordine(username) VALUES(?)";
		String sqlHa="INSERT INTO ha(codiceOrdine, codiceScarpa) VALUES(?,?)";
		String getCarrelloId="SELECT MAX(codice) FROM ordine";
		String sqlTaglia="INSERT INTO taglia(taglia, codiceScarpa, codiceOrdine) VALUES(?,?,?)";

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
			
			psTaglia=con.prepareStatement(sqlTaglia);
			psHa=con.prepareStatement(sqlHa);
			
			psTaglia.setInt(3, codiceCarrello);
			psHa.setInt(1, codiceCarrello);
			for (ScarpaOrdine s:bean.getScarpe()) {
				psHa.setInt(2, s.getId());
				psHa.executeUpdate();
				
				psTaglia.setInt(1, s.getTaglia());
				psTaglia.setInt(2, s.getId());
				psTaglia.executeUpdate();
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
		return null;
	}

	public Collection<Carrello> getOrdini(String username) throws SQLException{
		Connection con=null;
		PreparedStatement psOrdine=null;
		PreparedStatement psScarpe=null;
		
		ResultSet rsOrdine=null;
		ResultSet rsScarpe=null;
		
		Collection<Carrello> ordini=new LinkedList<>();
		ScarpaDataSource sds=new ScarpaDataSource();
		
		String sqlOrdine="SELECT codice FROM ordine WHERE username=?";
		String sqlScarpe="SELECT DISTINCT(t.codiceScarpa), t.taglia FROM ha, taglia as t WHERE ha.codiceOrdine=? and t.codiceOrdine=ha.codiceOrdine";
		try {
			con=ds.getConnection();
			psOrdine=con.prepareStatement(sqlOrdine);
			psScarpe=con.prepareStatement(sqlScarpe);
			
			psOrdine.setString(1, username);
			
			rsOrdine=psOrdine.executeQuery();
			while (rsOrdine.next()) {
				Carrello c=new Carrello();
				c.setUsername(username);
				int codiceCarrello=rsOrdine.getInt("codice");
				c.setCodice(codiceCarrello);
				
				psScarpe.setInt(1, codiceCarrello);
				rsScarpe=psScarpe.executeQuery();
				while (rsScarpe.next()) {
					int codiceScarpa=rsScarpe.getInt("codiceScarpa");
					int taglia=rsScarpe.getInt("taglia");
					ScarpaOrdine s=new ScarpaOrdine(sds.doRetrieveByKey(codiceScarpa+""));
					
					s.setTaglia(taglia);
					
					c.getScarpe().add(s);
				}
				ordini.add(c);
			}
		} finally {
			try {
				if (psOrdine!=null)
					psOrdine.close();
				if (psScarpe!=null)
					psScarpe.close();
			}finally {
				if (con!=null)
					con.close();
			}
		}
		return ordini;
	}

}
