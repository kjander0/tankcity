package com.gmail.kjander0.tankcity;

import com.badlogic.gdx.graphics.Texture;

/*
 * textures/sounds etc to be referenced elsewhere
 */
public class Assets {
	public static Texture img;
	
	public static void init() {
		img = new Texture("badlogic.jpg");
	}
	
	public static void dispose() {
		img.dispose();
	}
}
