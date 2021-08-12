package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class UserDao implements Dao<User> {

	@Override
	public void add(User user) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
		try {
			connection.setAutoCommit(false);
			PreparedStatement stmt = connection
					.prepareStatement("""
					INSERT INTO
					User (id, name, lastName, email, password)
					VALUES (?, ? ,?, ?, ?);
					""");
			stmt.setBytes(1,  UuidAdapter.getBytesFromUUID(user.getId()));
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getLastName());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getPassword());

			stmt.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}

	@Override
	public void update(User user) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
		try {
			connection.setAutoCommit(false);

			PreparedStatement stmt = connection
					.prepareStatement("""
					UPDATE User
					SET name = ?,
					lastName = ?,
					email = ?,
					password = ?
					WHERE id = ?;
					""");
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());
			stmt.setBytes(5, UuidAdapter.getBytesFromUUID(user.getId()));

			stmt.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}

	}

	@Override
	public void delete(User user) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
		try {
			PreparedStatement stmt = connection
					.prepareStatement("""
					DELETE FROM User
					WHERE id = ?;
					""");
			stmt.setBytes(1, UuidAdapter.getBytesFromUUID(user.getId()));

			stmt.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}

	}

	@Override
    public Optional<User> select(UUID id) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
        User userQuery = null;
        try{
            PreparedStatement stmt = connection.prepareStatement( """
                    SELECT id, name, lastName, email, password 
                    FROM User
                    WHERE id = ?;
                    """);
            byte[] userId = UuidAdapter.getBytesFromUUID(id);
            stmt.setBytes(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String password = rs.getString("password");
                userQuery = new User(id, name, lastName, email, password);
            }
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
        	connection.close();
        }
        Optional<User> oUser = Optional.of(userQuery);
        return oUser;
    }

}
