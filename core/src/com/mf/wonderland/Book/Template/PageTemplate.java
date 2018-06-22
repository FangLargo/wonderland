package com.mf.wonderland.Book.Template;

import com.badlogic.gdx.utils.Array;

public class PageTemplate {
	public PageTemplate() {
		
	}
	
	public PageTemplate(String pN, float sH, float figScale, float scrlW, float sPX, float sPY, float ePX, float ePY) {
		this.pageName = pN;
		
		this.stageHeight = sH;
		this.figureScale = figScale;
		this.scrollWidth = scrlW;
		
		this.startPositionX = sPX;
		this.startPositionY = sPY;
		
		this.endPositionX = ePX;
		this.endPositionY = ePY;
		
	}
	
	public String pageName;
	
	public float stageHeight;
	public float figureScale;
	public float scrollWidth;
	
	public float startPositionX;
	public float startPositionY;
	public float endPositionX;
	public float endPositionY;

	public String atlas;
	//public Array<String> atlases = new Array<String>();
	public Array<FigureTemplate> figures = new Array<FigureTemplate>();
	
	public Array<AnimTemplate> cameraAnims = new Array<AnimTemplate>();
}
