package com.goldbytes.stages;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.goldbytes.db.DataBase;
import com.goldbytes.screens.TestScreen;
import com.goldbytes.utils.Assets;
import com.goldbytes.utils.Constants;

public class TestStage extends Stage {

	private TextButton textButton;
	private List<String> data;
	
	private Label text;
	private DataBase db;
	
	public TestStage() {
		initStage();
		db = new DataBase();
	}
	
	private void initStage() {
		text = new Label("DataBase", Assets.skin);
		
		data = new List<String>(Assets.skin);
		textButton = new TextButton("Insert Data", Assets.skin);
		Table table = new Table(Assets.skin);
		table.setBounds(0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		
		Table t = new Table(Assets.skin);
		t.add(data).row();
		ScrollPane pane = new ScrollPane(t, Assets.skin);
		
		

		table.add(text).width(table.getWidth()).row();
		table.add(pane).height(table.getHeight() * (2.0f / 3.0f)).width(table.getWidth()).space(25).row();
		table.add(textButton).space(25);
		
		
		textButton.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				data.setItems(db.getComents());
			}
		});
		addActor(table);
		
		getCamera().viewportWidth = Constants.GAME_WIDTH;
		getCamera().viewportHeight = Constants.GAME_HEIGHT;
		getCamera().position.set(Constants.GAME_WIDTH / 2, Constants.GAME_HEIGHT / 2, 0);
	}
	@Override
	public void act(float delta) {
		super.act(delta);
	}

}
