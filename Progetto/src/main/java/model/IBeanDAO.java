package model;
import java.sql.SQLException;
import java.util.Collection;

public interface IBeanDAO<T> {
	public boolean doSave(T bean) throws SQLException;

	public boolean doDelete(String nome) throws SQLException;

	public T doRetrieveByKey(String code) throws SQLException;
	
	public Collection<T> doRetrieveAll() throws SQLException;
}
