package com.mf.wonderland.Book.Template;

import com.badlogic.gdx.utils.Array;

public class FigureTemplate {
	
	public FigureTemplate(String rN, float sX, float sY, float sR) {
		this.regionName = rN;
		
		this.startX = sX;
		this.startY = sY;
		
		this.startRotation = sR;
	}
	
	public FigureTemplate(String rN, float sX, float sY, float sR, Array<AnimTemplate> a) {
		this.regionName = rN;
		
		this.startX = sX;
		this.startY = sY;
		
		this.startRotation = sR;
		this.anims = a;
	}

	public String regionName;
	
	public float startX;
	public float startY;
	//public float startScale;
	public float startRotation;
	
	public Array<AnimTemplate> anims = new Array<AnimTemplate>();
	
}
