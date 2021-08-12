package classes;

import java.util.UUID;

public abstract class Entity {
	
	protected final UUID id = null;
	
	public UUID getUUID() {
		return id;
	}
	
	public void validate() {
		//TODO id único y requerido
	}
	
	@Override
	public boolean equals (Object obj) {
		if (!(obj instanceof Entity)) {
			return false;
		}
		Entity tmpEntity = (Entity) obj;
		return this.getUUID().equals(tmpEntity.getUUID());
	}
	
	/*@Override
	public int hashCode() {
		return this.id.hashCode();
	}*/
	
	/*public abstract static Entity create() {
		Entity entity = new Entity();
		entity.id = UUID.randomUUID();
		return entity;
	}*/
	
}
