package com.mf.wonderland.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mf.wonderland.Wonderland;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 360;
		config.width = 640;
		
		new LwjglApplication(new Wonderland(), config);
	}
}
