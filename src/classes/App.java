package classes;

import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws SQLException {
		Dao<Pizza> pizzaDao = new PizzaDao();
		Pizza pizzaBolonhesa = new Pizza("Bolonhesa", "fotoBolonhesa");
		Pizza pizzaKebab = new Pizza ("Kebab", "fotoPizzaKebab");
		Ingredient pepperoni = new Ingredient("Pepperoni", 2);
		Ingredient queso = new Ingredient ("Queso", 1);
		

		pizzaBolonhesa.addIngredient(pepperoni);
		pizzaBolonhesa.addIngredient(queso);
		
		pizzaDao.add(pizzaBolonhesa);
		pizzaDao.delete(pizzaKebab);
		
		pizzaBolonhesa.setName("Boloniesa");
		pizzaBolonhesa.setPhotoUrl("pizzaBoloniesa");
		
		pizzaDao.update(pizzaBolonhesa);

		System.out.println(pizzaDao.select(pizzaKebab.getId()));

	}
}
