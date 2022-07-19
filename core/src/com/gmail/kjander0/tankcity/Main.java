package com.gmail.kjander0.tankcity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;
	Sprite tankSprite;
	
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
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		tankSprite = new Sprite(img);
		tankSprite.setOriginCenter();
		
		Gdx.input.setInputProcessor(this);
	}
	
	public void update(float dt) {
		if (turnLeft) {
			tankAngle += turnSpeed * dt;
		}
		if (turnRight) {
			tankAngle -= turnSpeed * dt;
		}
		
		/*
		 * TODO
		 * - should be moment when gear is disengaged, slowing down due to friction
		 * - tank should turn slower when at speed
		 */
		var tankDir = new Vector2((float)Math.cos(tankAngle), (float)Math.sin(tankAngle));
		var targetSpeed = tankSpeeds[tankGear];
		var currentSpeed = tankVel.dot(tankDir);
		var newSpeed = currentSpeed;
		lastDeltaV = 0f;
		if (currentSpeed < targetSpeed) {
			newSpeed += tankAccels[tankGear] * dt;
			newSpeed = Math.min(newSpeed, targetSpeed);
		} else if (currentSpeed > targetSpeed) {
			newSpeed -= tankAccels[tankGear] * dt;
			newSpeed = Math.max(newSpeed, targetSpeed);

		}
		lastDeltaV = currentSpeed - newSpeed;

		
		tankVel.set(tankDir).scl(newSpeed);
		tankPos.add(new Vector2(tankVel).scl(dt));
	}

	@Override
	public void render () {
		var dt = Gdx.graphics.getDeltaTime();
		update(dt);
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		var wiggle = (float)Math.random() * lastDeltaV / 30f;
		tankSprite.setPosition(tankPos.x, tankPos.y);
		tankSprite.setRotation((tankAngle + wiggle) * 180f / (float)Math.PI);
		tankSprite.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode) {
		case Input.Keys.W:
			tankGear = Math.min(tankSpeeds.length-1, tankGear + 1);
			break;
		case Input.Keys.A:
			turnLeft = true;
			break;
		case Input.Keys.D:
			turnRight = true;
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode) {
		case Input.Keys.S:
			tankGear = Math.max(0, tankGear - 1);
			break;
		case Input.Keys.A:
			turnLeft = false;
			break;
		case Input.Keys.D:
			turnRight = false;
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}
