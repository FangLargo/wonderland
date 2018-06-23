package com.mf.wonderland.Book;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Figure {
	
	public Figure(TextureAtlas atlas, String regionName, float stageScale, float imageScale, float X, float Y, float startScale, float Rot, float parallax, int paraMode) {		
		AtlasRegion region = atlas.findRegion(regionName);
		this.figureSprite = atlas.createSprite(regionName);

		float xT = X/stageScale;
		float yT = Y/stageScale;
		this.figureSprite.setBounds(0, 0, region.getRegionWidth()/stageScale/imageScale, region.getRegionHeight()/stageScale/imageScale);

		this.figureSprite.setPosition(xT, yT);
		
//		this.figureSprite.setOriginCenter();
//		this.figureSprite.setRotation(Rot);

		this.startX = xT;
		this.startY = yT;
		this.parallaxDist = parallax;
		this.parallaxMode = paraMode;
	}
	
	public Sprite figureSprite;
	public float scale;
	public float parallaxDist;
	public float parallaxMode;
	
	public float startX;
	public float startY;
	
	public Array<Anim> anims = new Array<Anim>();
	
	private Anim currentTransAnim;
	private Anim currentRotAnim;
	private Anim currentScaleAnim;
	
	public void updateFigureAnim(float progress) {
		if(anims.size > 0) {
			for(Anim a: anims) {
//				Vector3 trans = new Vector3(0, 0, 0);
//				boolean transDone = false;
//				Vector2 rot = new Vector2(0, 0);
//				boolean rotDone = false;
//				
				if(a.updateAnim(progress) != null) {
					Vector2 val = new Vector2(a.updateAnim(progress));
					
					if(a.type.equals(Anim.TRANSLATE)) {
						this.figureSprite.setPosition(val.x, val.y);
//						transDone = true;
					} else if(a.type.equals(Anim.ROTATE)) {
						this.figureSprite.setOriginCenter();
						this.figureSprite.setRotation(val.x);
//						rotDone = true;
					}
				} else {
					//this.figureSprite.setPosition(this.startX, this.startY);
//					if(a.type.equals(Anim.TRANSLATE) && a.endScroll > trans.z && progress > a.endScroll) {
//						trans.set(a.endX, a.endY, a.endScroll);
//					} else if(a.type.equals(Anim.ROTATE) && a.endScroll > rot.y && progress > a.endScroll) {
//						rot.set(a.endX, a.endScroll);
//					}
				}
				
//				if(!transDone) {
//					this.figureSprite.setPosition(trans.x, trans.y);
//				}
//				if(!rotDone) {
//					this.figureSprite.setRotation(rot.x);
//				}
				
				//System.out.println("AnimTriggered: " + progress + ": " + a.startScroll + ", " + a.endScroll);
				
			}
		} else {
			this.figureSprite.setPosition(this.startX, this.startY);
			this.figureSprite.setOriginCenter();
			this.figureSprite.setScale(1);
		}
	}
	
	public void updateFigureParallax(Vector3 camera) {
		float mult = 0.2f;
		float pow = 0.15f/camera.z;
		
		float dispX = (camera.x - this.figureSprite.getX() + this.figureSprite.getWidth()/2)*mult*this.parallaxDist*pow;
		float dispY = (camera.y - this.figureSprite.getY() + this.figureSprite.getHeight()/2)*mult*this.parallaxDist*pow;
		//System.out.println(camera.z);
		this.figureSprite.setOriginCenter();
		
		//TODO: Implement zoom parallax later.
		if(this.parallaxMode == Figure._X) {
			this.figureSprite.translateX(dispX);
		} else if(this.parallaxMode == Figure._Y) {
			this.figureSprite.translateY(dispY);
		} else if(this.parallaxMode == Figure._BOTH) {
			this.figureSprite.translate(dispX, dispY);
		}
		
//		if(camera.z != 1f) {
//		}
		
	}
	
	public static int _X = 0;
	public static int _Y = 1;
	public static int _BOTH = 2;
}