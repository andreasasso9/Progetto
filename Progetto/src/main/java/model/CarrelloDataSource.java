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
		String sqlCarrello="INSERT INTO ordine(username, riepilogo, data) VALUES(?,?,?)";
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
			String riepilogo=bean.getScarpe()+"<br>Totale: "+bean.getScarpe().parallelStream().mapToDouble(c->c.getPrezzo()*c.getQuantità()).sum();
			psCarrello.setString(2, riepilogo);
			psCarrello.setDate(3, new java.sql.Date(System.currentTimeMillis()));
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
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="SELECT * FROM ordine";
		
		Collection<Carrello> ordini=new LinkedList<>();
		
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery();
			
			while (rs.next()) {
				Carrello c=new Carrello();
				c.setUsername(rs.getString("username"));
				c.setRiepilogo(rs.getString("riepilogo"));
				c.setData(rs.getDate("data"));
				
				ordini.add(c);
			}
		} finally {
			try {
				if (ps!=null)
					ps.close();
			} finally {
				if (con!=null)
					con.close();
			}
		}
		return ordini;
	}

	public Collection<Carrello> getOrdini(String username) throws SQLException{
		Connection con=null;
		PreparedStatement psOrdine=null;
		
		ResultSet rsOrdine=null;
		
		Collection<Carrello> ordini=new LinkedList<>();
		
		String sqlOrdine="SELECT riepilogo, data FROM ordine WHERE username=?";
		try {
			con=ds.getConnection();
			psOrdine=con.prepareStatement(sqlOrdine);
			
			psOrdine.setString(1, username);
			
			rsOrdine=psOrdine.executeQuery();
			while (rsOrdine.next()) {
				Carrello c=new Carrello();
				c.setRiepilogo(rsOrdine.getString("riepilogo"));
				c.setData(rsOrdine.getDate("data"));
				
				ordini.add(c);
			}
		} finally {
			try {
				if (psOrdine!=null)
					psOrdine.close();
			}finally {
				if (con!=null)
					con.close();
			}
		}
		return ordini;
	}

}
