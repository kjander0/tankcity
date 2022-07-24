package com.gmail.kjander0.tankcity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter implements InputProcessor {
	private GameWorld gameWorld;
	
	private TankPhysicsSystem tankPhysicsSystem;
	private RenderSystem renderSystem;
	
	@Override
	public void create () {
		Assets.init();
		
		gameWorld = new GameWorld();
		
		tankPhysicsSystem = new TankPhysicsSystem();
		renderSystem = new RenderSystem();

		
		Gdx.input.setInputProcessor(this);
		
		// initial resize of everything
		this.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render () {
		gameWorld.deltaTime = Gdx.graphics.getDeltaTime();
		
		// TODO all systems have same interface, might as well chuck em in a list and just iterate through em
		tankPhysicsSystem.update(gameWorld);
		renderSystem.update(gameWorld);
	}
	
	@Override
	public void dispose () {
		Assets.dispose();
		renderSystem.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		renderSystem.cam.setToOrtho(false, width, height);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO tank input logic should be in a TankController system, etc
		var tankComp = gameWorld.playerTank.tankPhysicsComp;
		switch(keycode) {
		case Input.Keys.W:
			tankComp.gear = Math.min(tankComp.speedMap.length-1, tankComp.gear + 1);
			break;
		case Input.Keys.A:
			tankComp.turnLeft = true;
			break;
		case Input.Keys.D:
			tankComp.turnRight = true;
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		var tankComp = gameWorld.playerTank.tankPhysicsComp;
		switch(keycode) {
		case Input.Keys.S:
			tankComp.gear = Math.max(0, tankComp.gear - 1);
			break;
		case Input.Keys.A:
			tankComp.turnLeft = false;
			break;
		case Input.Keys.D:
			tankComp.turnRight = false;
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
