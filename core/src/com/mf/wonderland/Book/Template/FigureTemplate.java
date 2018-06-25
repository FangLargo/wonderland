package com.mf.wonderland.Book.Template;

import com.badlogic.gdx.utils.Array;

public class FigureTemplate {
	public FigureTemplate() {
		
	}
	
	public FigureTemplate(String regionN, float sX, float sY, float sc, float sR, float parallax, int paraMode) {
		this.regionName = regionN;
		
		this.startX = sX;
		this.startY = sY;
		this.startScale = sc;
		this.startRotation = sR;
		this.parallaxDist = parallax;
		this.parallaxMode = paraMode;
		
		this.autoAnims = new Array<AutoAnimTemplate>();
	}

	public String regionName;
	
	public float startX;
	public float startY;
	public float startScale;
	public float startRotation;
	
	public float parallaxDist;
	public int parallaxMode;
	
	public Array<AnimTemplate> anims = new Array<AnimTemplate>();
	public Array<AutoAnimTemplate> autoAnims = new Array<AutoAnimTemplate>();
	
}
