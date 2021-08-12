package classes;

import java.util.UUID;

public class Ingredient extends Entity {

	private UUID id;
	private String name;
	private double price;

	public Ingredient(String name, double price) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.price = price;
	}

	public Ingredient(UUID id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public UUID getId() {
		return id;
	}
	

}
