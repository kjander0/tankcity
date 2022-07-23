package com.gmail.kjander0.tankcity;

import com.badlogic.gdx.math.Vector2;

public class TankPhysicsComp {
	boolean turnLeft, turnRight;
	Vector2 tankPos = new Vector2();
	Vector2 tankVel = new Vector2();
	float tankAngle = 0f;
	float turnSpeed = 1f;
	float lastDeltaV = 0f;
	int tankGear = 0;
	float tankSpeeds[] = {
			0f,
			32f,
			64f,
			80f,
			100f,
	};
	float tankAccels[] = {
			80f,
			80f,
			40f,
			20f,
			8f,
	};
}
