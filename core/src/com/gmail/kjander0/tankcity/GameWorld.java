package com.gmail.kjander0.tankcity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/*
 * All the game state, gets operated on by game logic (systems)
 */
public class GameWorld {
	public Array<Entity> entities;
	public Entity playerTank;
	public float deltaTime;
	
	public GameWorld() {
		entities = new Array<Entity>();
		
		playerTank = new Entity();
		playerTank.pos = new Vector2(10, 10);
		playerTank.tankPhysicsComp = new TankPhysicsComp();
		entities.add(playerTank);
	}
}
