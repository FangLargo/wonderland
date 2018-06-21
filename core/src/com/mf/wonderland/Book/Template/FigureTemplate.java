package com.mf.wonderland.Book.Template;

import com.badlogic.gdx.utils.Array;

public class FigureTemplate {
	public FigureTemplate() {
		
	}
	
	public FigureTemplate(String rN, float sX, float sY, float sc, float sR) {
		this.regionName = rN;
		
		this.startX = sX;
		this.startY = sY;
		
		this.startRotation = sR;
	}

	public String regionName;
	
	public float startX;
	public float startY;
	//public float startScale;
	public float startRotation;
	
	public float parallaxDist = 0;
	
	public Array<AnimTemplate> anims = new Array<AnimTemplate>();
	
}
