package com.gmail.kjander0.tankcity;

import com.badlogic.gdx.math.Vector2;

public class TankPhysicsComp {
	boolean turnLeft, turnRight;
	
	// values for determining drive performance
	float weight = 1.f;
	float wheelBase = 128.f;
	float power; // from engines
	
	Vector2 vel = new Vector2();
	
	float angle = 0f;
	float angularVel = 0f;

	float lastDeltaV = 0f;
	int gear = 0;
	float speedMap[] = {
			0f,
			.4f,
			.7f,
			.9f,
			1f,
	};
	float accelMap[] = {
			0f,
			1f,
			.9f,
			.7f,
			.4f,
	};
}
