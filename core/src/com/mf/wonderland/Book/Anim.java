package com.mf.wonderland.Book;

import com.badlogic.gdx.math.Vector2;

public class Anim {
	
	public Anim() {
		
	}
	
	public Anim(String Type, float startPos, float sX, float sY, float endPos, float eX, float eY) {
		type = Type;
		
		startScroll = startPos;
		startX = sX;
		startY = sY;
		
		endScroll = endPos;
		endX = eX;
		endY = eY;
	}
	
	public String type;
	
	//Only X value is used for rotation. 
	public float startScroll;
	public float startX;
	public float startY;
	
	public float endScroll;
	public float endX;
	public float endY;
	
	public Vector2 updateAnim(float progress) {
		if(queryAnim(progress)) {
			return Anim.interpolate(progress, startScroll, endScroll, startX, startY, endX, endY);
		}
		
		return null;
	}
	
	public static Vector2 interpolate(float mid, float startS, float endS, float sX, float sY, float eX, float eY) {
		
		float intervalX = eX - sX;
		float intervalY = eY - sY;
		float intervalScroll = endS - startS;
		
		float midScroll = mid - startS;
		float midX = (midScroll/intervalScroll)*intervalX + sX;
		float midY = (midScroll/intervalScroll)*intervalY + sY;
		
		return new Vector2(midX, midY);
	}
	
	public boolean queryAnim(float progress) {
		if(progress >= this.startScroll && progress < this.endScroll) {
			return true;
		}
		return false;
	}
	
	public static String TRANSLATE = "translate";
	public static String ROTATE = "rotate";
	public static String SCALE = "scale";

}
