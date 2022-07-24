package com.gmail.kjander0.tankcity;

import com.badlogic.gdx.math.Vector2;

public class TankPhysicsSystem {
	public void update(GameWorld world) {
		for (var ent : world.entities) {
			if (ent.tankPhysicsComp == null) {
				continue;
			}
			moveTank(ent, world.deltaTime);
		}
	}
	
	public void moveTank(Entity tank, float dt) { 
		/*
		 * TODO
		 * - acceleration and top speed should drop off linearly so smaller cities can outrun bigger ones
		 * - if cities are modeled as tanks with two sets of tracks, then left and right tracks can be accelerated in opposite directions when turning
		 * 		and given difference in speed dv and wheel base b, angular velocity should be dv/b
		 * - should be moment when gear is disengaged, slowing down due to friction
		 * - tank should turn slower when at speed
		 */
		
		var baseSpeed = 100f;
		var baseAccel = 100f;
		var tankComp = tank.tankPhysicsComp;
		
		var targetAngularVel = 0f;
		if (tankComp.turnLeft) {
			targetAngularVel += baseSpeed * tankComp.speedMap[1];
		}
		if (tankComp.turnRight) {
			targetAngularVel -= baseSpeed * tankComp.speedMap[1];
		}
		targetAngularVel /= tankComp.wheelBase;
		tankComp.angle += targetAngularVel * dt;
		
		var targetSpeed = baseSpeed * tankComp.speedMap[tankComp.gear];
		var tankDir = new Vector2((float)Math.cos(tankComp.angle), (float)Math.sin(tankComp.angle));
		var currentSpeed = tankComp.vel.dot(tankDir);
		var newSpeed = currentSpeed;
		tankComp.lastDeltaV = 0f;
		if (currentSpeed < targetSpeed) {
			newSpeed += baseAccel * tankComp.accelMap[tankComp.gear] * dt;
			newSpeed = Math.min(newSpeed, targetSpeed);
		} else if (currentSpeed > targetSpeed) {
			newSpeed -= baseAccel * tankComp.accelMap[tankComp.gear] * dt;
		}
		tankComp.lastDeltaV = currentSpeed - newSpeed;
		
		tankComp.vel.set(tankDir).scl(newSpeed);
		tank.pos.add(new Vector2(tankComp.vel).scl(dt));
	}
}
