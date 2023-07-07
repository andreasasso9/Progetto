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

import DTO.Scarpa;

public class ScarpaDataSource implements IBeanDAO<Scarpa> {

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
	public synchronized boolean doSave(Scarpa scarpa) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO scarpa (nome, taglia, prezzo) VALUES (?, ?, ?)";
				
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			ps=con.prepareStatement(insertSQL);
			
			ps.setString(1, scarpa.getNome());
			ps.setInt(2, scarpa.getTaglia());
			ps.setDouble(3, scarpa.getPrezzo());

			ps.executeUpdate();
			con.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (con != null)
					con.close();
			}
		}
		return true;
	}

	@Override
	public synchronized boolean doDelete(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		int result = 0;
		
		String deleteSQL = "DELETE FROM scarpa WHERE id = ?";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(deleteSQL);
			
			ps.setInt(1, Integer.parseInt(id));

			result = ps.executeUpdate();

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (con != null)
					con.close();
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized Collection<Scarpa> doRetrieveAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		Collection<Scarpa> products = new LinkedList<>();

		String selectSQL=null;

		selectSQL = "SELECT * FROM scarpa";

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectSQL);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Scarpa scarpa = new Scarpa();
				
				scarpa.setId(rs.getInt("id"));
				scarpa.setNome(rs.getString("nome"));
				scarpa.setPrezzo(rs.getDouble("prezzo"));
				scarpa.setTaglia(rs.getInt("taglia"));
				
				products.add(scarpa);
			}

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (con != null)
					con.close();
			}
		}
		return products;
	}
	
	@Override
	public synchronized Scarpa doRetrieveByKey(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Scarpa scarpa = new Scarpa();
		String selectSQL = "SELECT * FROM scarpa WHERE CODE = ?";
		
		try {
			connection = ds.getConnection();	
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				scarpa.setNome(rs.getString("nome"));
				scarpa.setPrezzo(rs.getDouble("prezzo"));
				scarpa.setTaglia(rs.getInt("taglia"));
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return scarpa;
	}
}


