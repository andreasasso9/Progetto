
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

public class DAODataSource implements IBeanDAO<Scarpa> {

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

	private static final String TABLE_NAME = "scarpa";

	@Override
	public synchronized void doSave(Scarpa product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO ? VALUES (nome, taglia, costo, foto) VALUES (?, ?, ?, ?)";
				
		try {
			connection = ds.getConnection();
			
			preparedStatement=connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, TABLE_NAME);
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setInt(3, product.getTaglia());
			preparedStatement.setDouble(4, product.getCosto());
			preparedStatement.setByte(5, product.getFoto());

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
		
		String deleteSQL = "DELETE FROM ? WHERE codice = ?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			preparedStatement.setString(1, TABLE_NAME);
			preparedStatement.setInt(2, code);

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

		Collection<Scarpa> products = new LinkedList<Scarpa>();

		/*
		String selectSQL = "SELECT * FROM " + DAODataSource.TABLE_NAME;
		implementa query
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		*/

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Scarpa bean = new Scarpaa();

				/*implementa query*/
				products.add(bean);
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
	public synchronized Scarpa doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Scarpa bean = new Scarpa();
		/*
		String selectSQL = "SELECT * FROM " + DAODataSource.TABLE_NAME + " WHERE CODE = ?";
		implementa query
		*/
		try {
			connection = ds.getConnection();	
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				/*
				 * implementa query
				 * */
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
		return bean;
	}
}


