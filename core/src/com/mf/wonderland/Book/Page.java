package com.mf.wonderland.Book;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Page {
	public Page() {
		
	}
	
//	public Page(float sHeight, float sWidth, float sPX, float sPY, float ePX, float ePY) {
//		
//	}
	
	// Everything in Page is in WORLD UNITS not RAW PIXELS
	// Every page has a scale - The assumed screen height. 
	// If screen height should be 100.
	// If stage height is 1440, then everything has to be /14.4 to fit. 
	// Because all positions will be fed in stage raw pixels, they all have to be divided by 10.8
	//
	
	public float stageHeight;
	public float scale;
	public float scrollStart;
	public float scrollEnd;
	
	public float startPositionX;
	public float startPositionY;
	public float endPositionX;
	public float endPositionY;
	
	// Offset is affected by previous chapters.
	// The start position of this chapter is at the end position of the
	// previous chapter. All sprites and are moved to this position.
	// This will be handled when the book is made. 
	//public float offsetX;
	//public float offsetY;

	//public String atlas;
	public Array<Figure> figures = new Array<Figure>();
	
	public Array<Anim> cameraAnims = new Array<Anim>();

	/**
	 * For each figures in this page, draw in SpriteBatch. Update figures before doing this. 
	 * @param sb Spritebatch for render.
	 */
	public void renderSprites(SpriteBatch sb) {
		for(Figure f: figures) {
			f.figureSprite.draw(sb);
		}
	}
	
	/**
	 * For each figure, calls updateFigureAnim, which should take care of updates.
	 * @param progress
	 */
	public void updateFigures(float progress, Vector3 cameraProps) {
		for(Figure f: figures) {
			f.updateFigureAnim(progress);
			f.updateFigureParallax(cameraProps);
		}
	}
	
	private Anim currentTransAnim;
	private Anim currentRotAnim;
	private Anim currentScaleAnim;
	
	/**
	 * Updates camera position and scale/rotation according to progress.
	 * @param cam
	 * @param progress
	 */
	public void updateCamera(OrthographicCamera cam, float progress) {
		Vector2 pos = new Vector2();
		if(cameraAnims.size == 0) {
			pos = Anim.interpolate(progress, scrollStart, scrollEnd, startPositionX, startPositionY, endPositionX, endPositionY);
			cam.position.set(pos.x + Book.cameraOffset.x, pos.y + Book.cameraOffset.y, 0);
		} else {
			for(Anim a: this.cameraAnims) {
				if(a.updateAnim(progress) != null) {
					pos = new Vector2(a.updateAnim(progress));
					
					if(a.type.equals(Anim.TRANSLATE)) {
						cam.position.set(pos.add(Book.cameraOffset), 0);
						this.currentTransAnim = a;
					} else if(a.type.equals(Anim.ROTATE)) {
						cam.up.set(0, 1, 0);
						//cam.direction.set(0, 0, -1);
						cam.rotate(pos.x);
						this.currentRotAnim = a;
					} else if(a.type.equals(Anim.SCALE)) {
						cam.zoom = 1/pos.x;
						this.currentScaleAnim = a;
					}
				}
				
				if(this.currentTransAnim != null) {
					if(progress > this.currentTransAnim.endScroll) {
						cam.position.set(this.currentTransAnim.endX + Book.cameraOffset.x, this.currentTransAnim.endY + Book.cameraOffset.y, 0);
					} else if(progress < this.currentTransAnim.startScroll) {
						cam.position.set(this.currentTransAnim.startX + Book.cameraOffset.x, this.currentTransAnim.startY + Book.cameraOffset.y, 0);
					}
				}
				
				if(this.currentRotAnim != null) {
					if(progress > this.currentRotAnim.endScroll) {
						cam.up.set(0, 1, 0);
						//cam.direction.set(0, 0, -1);
						cam.rotate(this.currentRotAnim.endX);
					} else if(progress < this.currentRotAnim.startScroll) {
						cam.up.set(0, 1, 0);
						//cam.direction.set(0, 0, -1);
						cam.rotate(this.currentRotAnim.startX);
					}
				}
//				cam.up.set(0, 1, 0);
//				cam.direction.set(0, 0, -1);
				
				if(this.currentScaleAnim != null) {
					if(progress > this.currentScaleAnim.endScroll) {
						cam.zoom = 1/(this.currentScaleAnim.endX);
					} else if(progress < this.currentScaleAnim.startScroll) {
						cam.zoom = 1/(this.currentScaleAnim.startX);
					}
				}
			}
		}
	}
}
