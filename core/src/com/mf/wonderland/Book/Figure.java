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
	public Array<AutoAnim> autoAnims = new Array<AutoAnim>();
	
	private Anim currentTransAnim;
	private Anim currentRotAnim;
	private Anim currentScaleAnim;
	
	public void updateFigureAnim(float progress) {
		if(anims.size > 0) {
			for(Anim a: anims) {
				if(a.updateAnim(progress) != null) {
					Vector2 val = new Vector2(a.updateAnim(progress));
					
					if(a.type.equals(Anim.TRANSLATE)) {
						this.figureSprite.setPosition(val.x, val.y);
						this.currentTransAnim = a;

					} else if(a.type.equals(Anim.ROTATE)) {
						this.figureSprite.setOriginCenter();
						this.figureSprite.setRotation(val.x);
						this.currentRotAnim = a;
					} else if(a.type.equals(Anim.SCALE)) {
						this.figureSprite.setScale(val.x, val.y);
						this.currentScaleAnim = a;
					}
				}
			}
			
			if(this.currentTransAnim != null) {
				if(progress > this.currentTransAnim.endScroll) {
					this.figureSprite.setPosition(this.currentTransAnim.endX, this.currentTransAnim.endY);
				} else if(progress < this.currentTransAnim.startScroll) {
					this.figureSprite.setPosition(this.currentTransAnim.startX, this.currentTransAnim.startY);
				}
			}
			
			if(this.currentRotAnim != null) {
				if(progress > this.currentRotAnim.endScroll) {
					this.figureSprite.setRotation(this.currentRotAnim.endX);
				} else if(progress < this.currentRotAnim.startScroll) {
					this.figureSprite.setRotation(this.currentRotAnim.startX);
				}
			}
			
			if(this.currentScaleAnim != null) {
				if(progress > this.currentScaleAnim.endScroll) {
					this.figureSprite.setScale(this.currentScaleAnim.endX, this.currentScaleAnim.endY);
				} else if(progress < this.currentScaleAnim.startScroll) {
					this.figureSprite.setScale(this.currentScaleAnim.startX, this.currentScaleAnim.startY);
				}
			}
		} else {
			this.figureSprite.setPosition(this.startX, this.startY);
			this.figureSprite.setOriginCenter();
			this.figureSprite.setScale(1);
		}
	}
	
	public void updateFigureAutoAnim(float progress, float delta) {
		if(autoAnims.size > 0) {
			for(AutoAnim a: autoAnims) {
				Vector2 val = new Vector2();
				
				if(a.updateAutoAnim(progress, delta) != null) {	
					val = a.updateAutoAnim(progress, 0);
//					System.out.println("Delta: " + delta + ", val: " + val);
					if(a.type.equals(Anim.TRANSLATE)) {
						this.figureSprite.translate(val.x, val.y);
						//this.currentTransAnim = a.toAnimData();

					} else if(a.type.equals(Anim.ROTATE)) {
						this.figureSprite.setOriginCenter();
						this.figureSprite.rotate(val.x);
						//this.currentRotAnim = a.toAnimData();
					} else if(a.type.equals(Anim.SCALE)) {
						this.figureSprite.setScale(val.x, val.y);
						//this.currentScaleAnim = a.toAnimData();
					}
				}
				//System.out.println(val.y);
			}
			
//			if(this.currentTransAnim != null) {
//				if(progress > this.currentTransAnim.endScroll) {
//					this.figureSprite.setPosition(this.currentTransAnim.endX, this.currentTransAnim.endY);
//				} else if(progress < this.currentTransAnim.startScroll) {
//					this.figureSprite.setPosition(this.currentTransAnim.startX, this.currentTransAnim.startY);
//				}
//			}
//			
//			if(this.currentRotAnim != null) {
//				if(progress > this.currentRotAnim.endScroll) {
//					this.figureSprite.setRotation(this.currentRotAnim.endX);
//				} else if(progress < this.currentRotAnim.startScroll) {
//					this.figureSprite.setRotation(this.currentRotAnim.startX);
//				}
//			}
//			
//			if(this.currentScaleAnim != null) {
//				if(progress > this.currentScaleAnim.endScroll) {
//					this.figureSprite.setScale(this.currentScaleAnim.endX, this.currentScaleAnim.endY);
//				} else if(progress < this.currentScaleAnim.startScroll) {
//					this.figureSprite.setScale(this.currentScaleAnim.startX, this.currentScaleAnim.startY);
//				}
//			}
		} else {
//			this.figureSprite.setPosition(this.startX, this.startY);
//			this.figureSprite.setOriginCenter();
//			this.figureSprite.setScale(1);
		}
	}
	
	public void updateFigureParallax(Vector3 camera) {
		float mult = 0.4f;
		float pow = 0.10f/camera.z;
		
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