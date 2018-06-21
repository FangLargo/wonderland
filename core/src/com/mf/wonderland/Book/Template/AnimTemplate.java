package com.mf.wonderland.Book.Template;

public class AnimTemplate {
	
	public AnimTemplate(String ty, float sS, float eS, float sX, float sY, float eX, float eY) {
		this.type = ty;
		
		this.startScroll = sS;
		this.endScroll = eS;
		
		this.startX = sX;
		this.endX = eX;
		
		this.startY = sY;
		this.endY = eY;
	}
	
	public String type;
	
	//Only X value is used for rotation. 
	public float startScroll;
	public float startX;
	public float startY;
	
	public float endScroll;
	public float endX;
	public float endY;
	
}
