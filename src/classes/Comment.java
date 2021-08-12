package classes;

import java.util.Date;
import java.util.UUID;

public class Comment extends Entity {
	
	private UUID id;
	private String text;
	private float rating;
	private Date date;
	private User user;
	private UUID userId;
	private Pizza pizza;
	private UUID pizzaId;
	
	public Comment(String text, float rating, Date date, User user, Pizza pizza, UUID userId, UUID pizzaId) {
		this.id = UUID.randomUUID();
		this.text = text;
		this.rating = rating;
		this.date = date;
		this.userId = user.getId();
		this.pizzaId = pizza.getId();
	}

	public Comment(UUID id, String text, float rating, java.sql.Date date, UUID userId, UUID pizzaId) {
		this.id = id;
		this.text = text;
		this.rating = rating;
		this.date = date;
		this.userId = user.getId();
		this.pizzaId = pizza.getId();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public UUID getId() {
		return id;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public UUID getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(UUID pizzaId) {
		this.pizzaId = pizzaId;
	}
	
	
	

}
