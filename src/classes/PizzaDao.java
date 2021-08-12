package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.sql.Connection;

public class PizzaDao implements Dao<Pizza> {

	@Override
	public void add(Pizza pizza) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
		try {
			connection.setAutoCommit(false);
			Set<Ingredient> listaIngredients = pizza.getIngredients();
			PreparedStatement stmt1 = connection.prepareStatement("""
					INSERT INTO Pizza (id, name, url)
					VALUES (?,?,?);
					""");
			stmt1.setBytes(1, UuidAdapter.getBytesFromUUID(pizza.getId()));
			stmt1.setString(2, pizza.getName());
			stmt1.setString(3, pizza.getPhotoUrl());

			stmt1.executeUpdate();

			for (Ingredient ingredient : listaIngredients) {
				PreparedStatement stmt2 = connection.prepareStatement("""
						INSERT INTO Ingredient (id, name, price)
						VALUES (?, ?, ?);
						""");
				stmt2.setBytes(1, UuidAdapter.getBytesFromUUID(ingredient.getId()));
				stmt2.setString(2, ingredient.getName());
				stmt2.setDouble(3, ingredient.getPrice());
				
				stmt2.executeUpdate();
				PreparedStatement stmt3 = connection.prepareStatement("""
						INSERT INTO pizza_ingredient (id, id_pizza, id_ingredient)
						VALUES (?, ?, ?);
						""");
				stmt3.setBytes(1, UuidAdapter.getBytesFromUUID(UUID.randomUUID()));
				stmt3.setBytes(2, UuidAdapter.getBytesFromUUID(pizza.getId()));
				stmt3.setBytes(3, UuidAdapter.getBytesFromUUID(ingredient.getId()));
				
				stmt3.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}

	@Override
	public void update(Pizza pizza) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
		try {
			connection.setAutoCommit(false);
			PreparedStatement stmt = connection.prepareStatement("""
					UPDATE Pizza
					SET name = ?,
					url = ?
					WHERE id = ?;
					""");

			stmt.setString(1, pizza.getName());
			stmt.setString(2, pizza.getPhotoUrl());
			stmt.setBytes(3, UuidAdapter.getBytesFromUUID(pizza.getId()));

			stmt.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}

	}

	@Override
	public void delete(Pizza pizza) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
		try {
			connection.setAutoCommit(false);
			PreparedStatement deletePizza = connection.prepareStatement("""
					DELETE FROM Pizza
					WHERE id = ?;
					""");
			deletePizza.setBytes(1, UuidAdapter.getBytesFromUUID(pizza.getId()));

			deletePizza.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}

	}

	@Override
    public Optional<Pizza> select(UUID id) throws SQLException {
		Connection connection = ConnectionDB.getConnection();
        Pizza pizzaQuery = null;
        try{
            PreparedStatement stmt = connection.prepareStatement( """
                    SELECT id, name, url FROM pizza
                    WHERE id = ?;
                    """);
            byte[] pizzaId = UuidAdapter.getBytesFromUUID(id);
            stmt.setBytes(1, pizzaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                String url = rs.getString("url");
                pizzaQuery = new Pizza(id, name, url);
            }
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
        	connection.close();
        }
        Optional<Pizza> oPizza  = Optional.of(pizzaQuery);
        return oPizza;
    }
	

}
