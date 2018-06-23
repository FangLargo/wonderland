package com.mf.wonderland.Book;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
	public void updateFigures(float progress, Vector2 pos) {
		for(Figure f: figures) {
			f.updateFigureAnim(progress);
			f.updateFigureParallax(pos);
		}
	}
	
	/**
	 * Updates camera position and scale/rotation according to progress.
	 * @param cam
	 * @param progress
	 */
	public void updateCamera(OrthographicCamera cam, float progress) {
		// TODO Auto-generated method stub
		if(cameraAnims.size == 0) {
			Vector2 pos = new Vector2();
			pos = Anim.interpolate(progress, scrollStart, scrollEnd, startPositionX, startPositionY, endPositionX, endPositionY);
			cam.position.set(pos.x + Book.cameraOffset.x, pos.y + Book.cameraOffset.y, 0);
		}
	}
}
