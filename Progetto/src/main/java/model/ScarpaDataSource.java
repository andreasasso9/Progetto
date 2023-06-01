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
	public synchronized void doSave(Scarpa product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO scarpa VALUES (nome, taglia, costo, foto) VALUES (?, ?, ?, ?)";
				
		try {
			connection = ds.getConnection();
			
			preparedStatement=connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setInt(2, product.getTaglia());
			preparedStatement.setDouble(3, product.getCosto());
			preparedStatement.setByte(4, product.getFoto());

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		
		String deleteSQL = "DELETE FROM scarpa WHERE codice = ?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized Collection<Scarpa> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Scarpa> products = new LinkedList<>();

		String selectSQL=null;

		if (order != null && !order.equals("")) {
			selectSQL = "SELECT * FROM scarpa ORDER BY ?";
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, order);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Scarpa scarpa = new Scarpa();

				scarpa.setNome(rs.getString("nome"));
				scarpa.setCosto(rs.getDouble("costo"));
				scarpa.setTaglia(rs.getInt("taglia"));
				scarpa.setFoto(rs.getByte("foto"));
				
				products.add(scarpa);
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
				scarpa.setCosto(rs.getDouble("costo"));
				scarpa.setTaglia(rs.getInt("taglia"));
				scarpa.setFoto(rs.getByte("foto"));
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


