package com.gmail.kjander0.tankcity;

import com.badlogic.gdx.math.Vector2;
import com.gmail.kjander0.tankcity.collisions.CollisionComp;

public class Entity {
	public CollisionComp collComp; // for entities that can collide
	public TankPhysicsComp tankPhysicsComp;
	public Vector2 pos;
}
