package com.mf.wonderland.Book.Template;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class AutoAnimTemplate {
	
	public AutoAnimTemplate() {
		
	}
	
	public AutoAnimTemplate(String ty, float startS, float endS, float l) {
		this.type = ty;
		this.startScroll = startS;
		this.endScroll = endS;
		this.length = l;
		this.frames = new Array<Vector3>();
	}

	public String type;
	public float startScroll;
	public float endScroll;
	public float length;
	
	public Array<Vector3> frames;

}
