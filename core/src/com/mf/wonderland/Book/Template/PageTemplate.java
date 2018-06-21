package com.mf.wonderland.Book.Template;

import com.badlogic.gdx.utils.Array;

public class PageTemplate {
	
	public PageTemplate(float sc, float scrlW, float sPX, float sPY, float ePX, float ePY, Array<String> atlas, Array<FigureTemplate> figs, Array<AnimTemplate> a) {
		this.scale = sc;
		this.scrollWidth = scrlW;
		
		this.startPositionX = sPX;
		this.startPositionY = sPY;
		
		this.endPositionX = ePX;
		this.endPositionY = ePY;
		
		this.atlases = atlas;
		this.figures = figs;
		this.cameraAnims = a;
	}
	
	public float scale;
	public float scrollWidth;
	
	public float startPositionX;
	public float startPositionY;
	public float endPositionX;
	public float endPositionY;
	
	public float offsetX;
	public float offsetY;
	
	public Array<String> atlases = new Array<String>();
	public Array<FigureTemplate> figures = new Array<FigureTemplate>();
	
	public Array<AnimTemplate> cameraAnims = new Array<AnimTemplate>();
}
