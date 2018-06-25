package com.mf.wonderland.Book.Template;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class AudioTemplate {
	public AudioTemplate() {
		
	}
	
	public AudioTemplate(int ty, String name, float st, float en, float maxVol) {
		this.type = ty;
		this.name = name;
		this.start = st;
		this.end = en;
		this.maxVolume = maxVol;
	}
	
	public int type;
	public String name;
	public float start;
	public float end;
	
	public Array<Vector2> frames = new Array<Vector2>();
	public float maxVolume;
}
