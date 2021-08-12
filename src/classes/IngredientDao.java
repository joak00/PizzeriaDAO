package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class IngredientDao implements Dao<Ingredient> {

	@Override
	public void add(Ingredient ingredient) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
		try {
			connection.setAutoCommit(false);
			PreparedStatement stmt = connection
					.prepareStatement("""
					INSERT
					INTO Ingredient (id, name, price)
					VALUES (?, ? ,?);
					""");
			stmt.setBytes(1, UuidAdapter.getBytesFromUUID(ingredient.getId()));
			stmt.setString(2, ingredient.getName());
			stmt.setDouble(3, ingredient.getPrice());

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
	public void update(Ingredient ingredient) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
		try {
			connection.setAutoCommit(false);
			PreparedStatement stmt = connection
					.prepareStatement("""
					UPDATE Ingredient
					SET name = ?,
					price = ?
					WHERE id = ?;
					""");
			stmt.setString(1, ingredient.getName());
			stmt.setDouble(2, ingredient.getPrice());
			stmt.setBytes(3, UuidAdapter.getBytesFromUUID(ingredient.getId()));

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
	public void delete(Ingredient ingredient) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
		try {
			connection.setAutoCommit(false);
			PreparedStatement stmt = connection
					.prepareStatement("""
					DELETE FROM Ingredient 
					WHERE id = ?;
					""");
			stmt.setBytes(1, UuidAdapter.getBytesFromUUID(ingredient.getId()));

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
    public Optional<Ingredient> select(UUID id) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
        Ingredient ingredientQuery = null;
        try{
            PreparedStatement stmt = connection.prepareStatement( """
                    SELECT id, name
                    FROM ingredient
                    WHERE id = ?;
                    """);
            byte[] ingredientId = UuidAdapter.getBytesFromUUID(id);
            stmt.setBytes(1, ingredientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                ingredientQuery = new Ingredient(id, name, price);
            }
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
        	connection.close();
        }
        Optional<Ingredient> oIngredient = Optional.of(ingredientQuery);
        return oIngredient;
    }

}
