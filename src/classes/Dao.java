package classes;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public interface Dao<T> {
	
	void add(T object) throws SQLException ;
	void update(T object) throws SQLException;
	void delete (T object) throws SQLException;
	Optional<T> select (UUID id) throws SQLException;

}
