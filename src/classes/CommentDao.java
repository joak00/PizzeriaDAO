package classes;

import java.sql.Connection; 
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class CommentDao implements Dao<Comment> {

	@Override
	public void add(Comment comment) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
		try {
			connection.setAutoCommit(false);
			PreparedStatement stmt = connection
					.prepareStatement("""
					INSERT INTO Comment (id, text, rating, date, id_user, id_pizza)
					VALUES (?, ? ,?, ?, ?, ?);
					""");
			stmt.setBytes(1, UuidAdapter.getBytesFromUUID(comment.getId()));
			stmt.setString(2, comment.getText());
			stmt.setFloat(3, comment.getRating());
			stmt.setDate(4, (Date) comment.getDate());
			stmt.setBytes(5, UuidAdapter.getBytesFromUUID(comment.getUserId()));																					// Fix
			stmt.setBytes(6, UuidAdapter.getBytesFromUUID(comment.getPizzaId()));

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
	public void update(Comment updatedComment) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
		try {
			PreparedStatement stmt = connection
					.prepareStatement("""
					UPDATE Comment
					SET text = ?,
					rating = ?,
					date = ?
					WHERE id = ?;
					""");
			stmt.setString(1, updatedComment.getText());
			stmt.setFloat(2, updatedComment.getRating());
			stmt.setDate(3, (Date) updatedComment.getDate());
			stmt.setBytes(4, UuidAdapter.getBytesFromUUID(updatedComment.getId()));
									
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
	public void delete(Comment comment) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
		try {
			PreparedStatement stmt = connection
					.prepareStatement("""
					DELETE FROM Comment WHERE id = ?;
					""");
			stmt.setBytes(1, UuidAdapter.getBytesFromUUID(comment.getId())); 
			
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
    public Optional<Comment> select(UUID id) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
        Comment commentQuery = null;
        try{
            PreparedStatement stmt = connection.prepareStatement( """
                    SELECT id, text, rating, date, id_user, id_pizza
                    FROM comment
                    WHERE id = ?;
                    """);
            byte[] commentId = UuidAdapter.getBytesFromUUID(id);
            stmt.setBytes(1, commentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String text = rs.getString("text");
                float rating = rs.getFloat("rating");
                Date date = rs.getDate("date");
                byte [] id_user = rs.getBytes("id_user");
                byte[] id_pizza = rs.getBytes("id_pizza");
                UUID userId = UuidAdapter.getUUIDFromBytes(id_user);
                UUID pizzaId = UuidAdapter.getUUIDFromBytes(id_pizza);
                
                commentQuery = new Comment(id, text, rating, date, userId, pizzaId);
            }
            
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
        	connection.close();
        }
        Optional<Comment> oComment = Optional.of(commentQuery);
        return oComment;
    }


}
