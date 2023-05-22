package Model;
import java.sql.SQLException;
import java.util.Collection;

public interface IBeanDAO<T> {
	public void doSave(T bean) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public T doRetrieveByKey(String code) throws SQLException;
	
	public Collection<T> doRetrieveAll(String order) throws SQLException;
}
