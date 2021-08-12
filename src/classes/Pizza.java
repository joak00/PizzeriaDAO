package classes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Pizza extends Entity {

	private final UUID id;
	private String name;
	private String photoUrl;
	private final Set<Ingredient> ingredients = new HashSet<Ingredient>();
	private final Set<Comment> comments = new HashSet<Comment>();
	private double price;

	public Pizza(String name, String photoUrl) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.photoUrl = photoUrl;
	}

	public Pizza(UUID id, String name, String photoUrl) {
		this.id = id;
		this.name = name;
		this.photoUrl = photoUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public double getPrice() {
		return price;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
	}

	public void removeIngredient(Ingredient ingredient) {
		ingredients.remove(ingredient);
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}

	public void removeComment(Comment comment) {
		comments.remove(comment);
	}

	public UUID getId() {
		return id;
	}

}
