package com.mf.wonderland.Book;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Page {
	public Page() {
		scale = 1f;
		scrollWidth = 100f;
		startPositionX = 0;
		startPositionY = 0;
		endPositionX = 177;
		endPositionY = 0;
		
		//cameraAnims.add(new Anim(Anim.AnimType.TRANSLATE, 0, 0, 0, 0, 0, 0));
	}
	
	public float scale;
	public float scrollWidth;
	
	public float startPositionX;
	public float startPositionY;
	public float endPositionX;
	public float endPositionY;
	
	public float offsetX;
	public float offsetY;
	
	//public Array<TextureAtlas> atlases = new Array<TextureAtlas>();
	public String atlas;
	public Array<Figure> figures = new Array<Figure>();
	
	public Array<Anim> cameraAnims = new Array<Anim>();

	public void renderSprites(SpriteBatch sb) {
		//Array<Sprite> arr = new Array<Sprite>();
		
		for(Figure f: figures) {
			//arr.add(f.figureSprite);
			f.figureSprite.draw(sb);
		}
		
		//return arr;
	}
	
	public void updateFigures(float progress) {
		for(Figure f: figures) {
			f.updateFigureAnim(progress);
		}
	}
	
	public void updateCamera(OrthographicCamera cam, float progress) {
		// TODO Auto-generated method stub
		if(cameraAnims.size == 0) {
			Vector2 pos = new Vector2();
			pos = Anim.interpolate(progress, 0, scrollWidth, startPositionX, startPositionY, endPositionX, endPositionY);
			cam.position.set(pos.x + cam.viewportWidth/2, pos.y + cam.viewportHeight/2, 0);
		}
	}
}
