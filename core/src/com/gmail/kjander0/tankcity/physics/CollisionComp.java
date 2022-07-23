package com.gmail.kjander0.tankcity.physics;

import com.badlogic.gdx.utils.Array;

public class CollisionComp {
	public CollisionShape shape;
	public int collGroup; // bitmask of who I can collide with
	public Array<Collision> colls = new Array<Collision>();

	public CollisionComp(CollisionShape shape, int collGroup) {
		this.shape = shape;
		this.collGroup = collGroup;
	}
}
