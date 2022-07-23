package com.gmail.kjander0.tankcity.collisions;

import com.badlogic.gdx.math.Vector2;
import com.gmail.kjander0.tankcity.Entity;

public class Collision {
	public Entity entityA, entityB;
	public Vector2 targetSeperation;
	
	public Collision(Entity a, Entity b) {
		this.entityA = a;
		this.entityB = b;
	}
}
