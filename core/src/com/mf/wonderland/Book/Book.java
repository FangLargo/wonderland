package com.mf.wonderland.Book;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Book {
	public Book() {
		
	}
	
	float totalScrollWidth;
	
	public void updateCamera(OrthographicCamera cam, float progress) {
		float totalProgress = 0;
		
		for(int i = 0; i < this.pages.size; i++) {
			if(progress >= totalProgress && progress <= totalProgress + pages.get(i).scrollWidth) {
				pages.get(i).updateCamera(cam, progress - totalProgress);
			}
		}
	}
	
	public void renderBook(float progress, SpriteBatch sb) {
		float totalProgress = 0;
		//int chapter = 0;
		for(int i = 0; i < this.pages.size; i++) {
			if(progress >= totalProgress && progress < totalProgress + pages.get(i).scrollWidth) {
				//chapter = i;
				pages.get(i).updateFigures(progress - totalProgress);
				pages.get(i).renderSprites(sb);
				if(i + 1 < pages.size) {
					pages.get(i + 1).updateFigures(progress - totalProgress);
					pages.get(i + 1).renderSprites(sb);
				}
			}
			totalProgress = totalProgress + pages.get(i).scrollWidth;
		}
	}
	
	public Array<Page> pages = new Array<Page>();
}
