package com.mf.wonderland.Book;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Book {
	public Book() {
		
	}
	
	float totalScrollWidth;
	float viewHeight;
	float viewWidth;
	
	/**
	 * Updates position of camera according to how much has been scrolled. 
	 * @param cam The camera to be updated.
	 * @param progress How much has been scrolled so far.
	 */
	public void updateCamera(OrthographicCamera cam, float progress) {
		float totalProgress = 0;
		
		for(int i = 0; i < this.pages.size; i++) {
			if(progress >= totalProgress && progress <= totalProgress + pages.get(i).scrollWidth) {
				pages.get(i).updateCamera(cam, progress - totalProgress);
			}
		}
	}
	
	/**
	 * Checks each page, finds the right one, and then renders each sprite in it. 
	 * TODO: Add function to render previous or next chapter if in view.
	 * -Next page is rendered if progress + scaledwidth is beyond page limit.
	 * @param progress Amount scrolled so far.
	 * @param sb Spritebatch the sprites will be rendered on.
	 */
	public void renderBook(float progress, SpriteBatch sb) {
		float totalProgress = 0;
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
