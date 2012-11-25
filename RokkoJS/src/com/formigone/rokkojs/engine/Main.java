package com.formigone.rokkojs.engine;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class Main implements EntryPoint {

	private Scene scene;
	private Renderer renderer;
	private Drawable entity;
	private List<Image> textures;
	private int resourcesReady;

	@Override
	public void onModuleLoad() {
		resourcesReady = 0;
		
		Canvas canvas = Canvas.createIfSupported();
		canvas.setCoordinateSpaceWidth(1600 / 2);
		canvas.setCoordinateSpaceHeight(900 / 2);

		textures = new ArrayList<Image>();
		
		textures.add(new Image(GWT.getModuleBaseURL() + "img/sprites/megaman-head.png"));
		RootPanel.get("imgShelf").add(textures.get(textures.size() - 1));

		textures.get(textures.size() - 1).addLoadHandler(new LoadHandler() {

			@Override
			public void onLoad(LoadEvent event) {
				resourcesReady++;
				runIfReady();
			}
		});

		entity = new Sprite2D(textures.get(textures.size() - 1));

		renderer = new Renderer2D(canvas);
		scene = new Scene(renderer);
		scene.setFps(2);
		scene.addDrawable(entity);

		RootPanel.get().add(canvas);
	}

	private void runIfReady() {
		if (resourcesReady == textures.size())
			scene.run();
	}
}
