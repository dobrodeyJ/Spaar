package com.goldbytes.spaar;

import com.badlogic.gdx.Game;
import com.goldbytes.screens.TestScreen;

public class Spaar extends Game {


	private TestScreen testScreen;
	
	
	private static Spaar instance = new Spaar();
	
	public static Spaar getInstance() {
		return instance;
	}
	private  Spaar() {}
	
	@Override
	public void create () {
		testScreen = new TestScreen();
		setScreen(testScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		
	}

}
