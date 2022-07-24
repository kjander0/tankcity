package com.gmail.kjander0.tankcity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;

public class RenderSystem implements Disposable {
	// Systems shouldn't have state, move this to god object
	OrthographicCamera cam = new OrthographicCamera();
	SpriteBatch batch;

	Sprite tankSprite;
	
	public RenderSystem() {
		batch = new SpriteBatch();
		tankSprite = new Sprite(Assets.img);
		tankSprite.setOriginCenter();
	}
	
	public void update(GameWorld world) {
		for (var ent : world.entities) {
			if (ent.tankPhysicsComp != null) {
				ScreenUtils.clear(1, 0, 0, 1);
				cam.update();
				batch.setProjectionMatrix(cam.combined);
				batch.begin();
				var wiggle = (float)Math.random() * ent.tankPhysicsComp.lastDeltaV / 30f;
				// TODO: probs don't need sprites, just use batch.draw() commands with textures and maybe batch.setTransformMatrix() to rotate etc
				tankSprite.setPosition(ent.pos.x,  ent.pos.y);
				tankSprite.setRotation((ent.tankPhysicsComp.angle + wiggle) * 180f / (float)Math.PI);
				tankSprite.draw(batch);
				batch.end();
			}
		}

	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
