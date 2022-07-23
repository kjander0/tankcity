package com.gmail.kjander0.tankcity.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.gmail.kjander0.tankcity.Entity;

/*
 * This system performs two main functions
 * 	1) check collisions between entities which have CollisionComp.
 *  2) resolve penetration between collided entities that require it.
 */
public class CollisionSystem {
	private Array<Collision> collisions = new Array<Collision>();
	
	public Array<Collision> checkCollisions(Array<Entity> entities) {
		collisions.clear();
		for (int i = 0; i < entities.size-1; i++) {
			for (int j = i+1; i < entities.size; j++) {
				var coll = checkCollision(entities.get(i), entities.get(j));
				if (coll == null) {
					continue;
				}
				collisions.add(coll);
			}
		}
		return collisions;
	}
	
	public void resolvePenetration(Array<Collision> collisions) {
		
	}
	
	private Collision checkCollision(Entity a, Entity b) {
		if (a.collComp == null || b.collComp == null) {
			return null;
		}
		
		var shapeA = (CircleShape)a.collComp.shape;
		var shapeB = (CircleShape)b.collComp.shape;
		var targetSep = checkSphere(a.pos, b.pos, shapeA.radius, shapeB.radius);
		if (targetSep == null) {
			return null;
		}
		
		var coll = new Collision(a, b);
		coll.targetSeperation = targetSep;
		
		return coll;
	}
	
	private Vector2 checkSphere(Vector2 posA, Vector2 posB, float radiusA, float radiusB) {
		var dist = posA.dst(posB);
		if (dist > radiusA + radiusB) {
			return null;
		}
		
		var targetSeperation = new Vector2(posA).sub(posB);
		var len = targetSeperation.len();
		if (len == 0) {
			targetSeperation.x = radiusA + radiusB;
		} else {
			targetSeperation.scl((radiusA + radiusB) / len);
		}
		return targetSeperation;
	}
}
