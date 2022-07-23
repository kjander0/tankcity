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
		var tankComp = tank.tankPhysicsComp;
		
		if (tankComp.turnLeft) {
			tankComp.tankAngle += tankComp.turnSpeed * dt;
		}
		if (tankComp.turnRight) {
			tankComp.tankAngle -= tankComp.turnSpeed * dt;
		}
		
		/*
		 * TODO
		 * - should be moment when gear is disengaged, slowing down due to friction
		 * - tank should turn slower when at speed
		 */
		var tankDir = new Vector2((float)Math.cos(tankComp.tankAngle), (float)Math.sin(tankComp.tankAngle));
		var targetSpeed = tankComp.tankSpeeds[tankComp.tankGear];
		var currentSpeed = tankComp.tankVel.dot(tankDir);
		var newSpeed = currentSpeed;
		tankComp.lastDeltaV = 0f;
		if (currentSpeed < targetSpeed) {
			newSpeed += tankComp.tankAccels[tankComp.tankGear] * dt;
			newSpeed = Math.min(newSpeed, targetSpeed);
		} else if (currentSpeed > targetSpeed) {
			newSpeed -= tankComp.tankAccels[tankComp.tankGear] * dt;
			newSpeed = Math.max(newSpeed, targetSpeed);

		}
		tankComp.lastDeltaV = currentSpeed - newSpeed;

		
		tankComp.tankVel.set(tankDir).scl(newSpeed);
		tankComp.tankPos.add(new Vector2(tankComp.tankVel).scl(dt));
	}
}
