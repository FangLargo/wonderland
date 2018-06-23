package com.mf.wonderland.Book;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mf.wonderland.Book.Template.BookTemplate;

public class Book {
	public Book() {
		
	}
	
	public Book(BookTemplate template, float viewH) {
		this.viewHeight = viewH;
		
		for(int i = 0; i < template.pages.size; i++) {
			this.pages.add(new Page());
			
			this.pages.get(i).stageHeight = template.pages.get(i).stageHeight;
			this.pages.get(i).scale = template.pages.get(i).stageHeight/this.viewHeight;
			float scale = this.pages.get(i).scale;
			/**
			 * Offset include:
			 * scrollWidth -> scrollStart and scrollEnd
			 * startPositionX and Y is + previous page's endPos
			 * Offset is (X, Y, scroll)
			 */
			Vector3 offset = new Vector3(0, 0, 0);
			
			// If NOT the first page, then set offset. Otherwise, stays 0, 0, 0
			if(i > 0) {
				offset.set(this.pages.get(i-1).endPositionX, this.pages.get(i-1).endPositionY, this.pages.get(i-1).scrollEnd);
			}
			
			this.pages.get(i).scrollStart = offset.z;
			this.pages.get(i).scrollEnd = template.pages.get(i).scrollWidth/scale + offset.z;
			
			this.pages.get(i).startPositionX = offset.x;
			this.pages.get(i).startPositionY = offset.y;
			this.pages.get(i).endPositionX = template.pages.get(i).endPositionX/scale + offset.x;
			this.pages.get(i).endPositionY = template.pages.get(i).endPositionY/scale + offset.y;
			//System.out.println(offset.x + ": " + this.pages.get(i).startPositionX + ", " + this.pages.get(i).endPositionX);
			
			TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(template.pages.get(i).atlas));
			
			//Including all figures.
			//Figure creation method handles scaling.
			for(int j = 0; j < template.pages.get(i).figures.size; j++) {
				this.pages.get(i).figures.add(new Figure(atlas, 
						template.pages.get(i).figures.get(j).regionName, 
						scale,
						template.pages.get(i).figureScale,
						template.pages.get(i).figures.get(j).startX + offset.x*scale,
						template.pages.get(i).figures.get(j).startY + offset.y*scale,
						template.pages.get(i).figures.get(j).startScale,
						template.pages.get(i).figures.get(j).startRotation,
						template.pages.get(i).figures.get(j).parallaxDist,
						template.pages.get(i).figures.get(j).parallaxMode
						));
				
				// Including all anim in figure
				for(int k = 0; k < template.pages.get(i).figures.get(j).anims.size; k++) {
					this.pages.get(i).figures.get(j).anims.add(new Anim(
							template.pages.get(i).figures.get(j).anims.get(k).type,
							template.pages.get(i).figures.get(j).anims.get(k).startScroll/scale + offset.z,
							template.pages.get(i).figures.get(j).anims.get(k).startX,
							template.pages.get(i).figures.get(j).anims.get(k).startY,
							template.pages.get(i).figures.get(j).anims.get(k).endScroll/scale + offset.z,
							template.pages.get(i).figures.get(j).anims.get(k).endX,
							template.pages.get(i).figures.get(j).anims.get(k).endY));
					
					if(template.pages.get(i).figures.get(j).anims.get(k).type.equals(Anim.TRANSLATE)) {
						this.pages.get(i).figures.get(j).anims.get(k).startX = 
								template.pages.get(i).figures.get(j).anims.get(k).startX/scale + offset.x;
						this.pages.get(i).figures.get(j).anims.get(k).startY =
								template.pages.get(i).figures.get(j).anims.get(k).startY/scale + offset.y;
						
						this.pages.get(i).figures.get(j).anims.get(k).endX = 
								template.pages.get(i).figures.get(j).anims.get(k).endX/scale + offset.x;
						this.pages.get(i).figures.get(j).anims.get(k).endY = 
								template.pages.get(i).figures.get(j).anims.get(k).endY/scale + offset.y;
						//System.out.println(this.pages.get(i).figures.get(j).anims.get(k).endX);
					}
				}
			}
			
			//Include all camera anims
			for(int k = 0; k < template.pages.get(i).cameraAnims.size; k++) {
				this.pages.get(i).cameraAnims.add(new Anim(
						template.pages.get(i).cameraAnims.get(k).type,
						template.pages.get(i).cameraAnims.get(k).startScroll/scale + offset.z,
						template.pages.get(i).cameraAnims.get(k).startX,
						template.pages.get(i).cameraAnims.get(k).startY,
						template.pages.get(i).cameraAnims.get(k).endScroll/scale + offset.z,
						template.pages.get(i).cameraAnims.get(k).endX,
						template.pages.get(i).cameraAnims.get(k).endY));
				
				//System.out.println(this.pages.get(i).cameraAnims.get(k).startScroll + " -> " + this.pages.get(i).cameraAnims.get(k).endScroll);
				
				if(template.pages.get(i).cameraAnims.get(k).type.equals(Anim.TRANSLATE)) {
					this.pages.get(i).cameraAnims.get(k).startX = 
							template.pages.get(i).cameraAnims.get(k).startX/scale + offset.x;
					this.pages.get(i).cameraAnims.get(k).startY =
							template.pages.get(i).cameraAnims.get(k).startY/scale + offset.y;
					
					this.pages.get(i).cameraAnims.get(k).endX = 
							template.pages.get(i).cameraAnims.get(k).endX/scale + offset.x;
					this.pages.get(i).cameraAnims.get(k).endY = 
							template.pages.get(i).cameraAnims.get(k).endY/scale + offset.y;
				}
			}
			
			boolean translateExists = false;
			for(Anim a: this.pages.get(i).cameraAnims) {
				if(a.type.equals(Anim.TRANSLATE)) {
					translateExists = true;
				}
			}
			
			if(!translateExists) {
				this.pages.get(i).cameraAnims.add(new Anim(
						Anim.TRANSLATE,
						this.pages.get(i).scrollStart,
						this.pages.get(i).startPositionX,
						this.pages.get(i).startPositionY,
						this.pages.get(i).scrollEnd,
						this.pages.get(i).endPositionX,
						this.pages.get(i).endPositionY));
			}
		}
		
		this.totalScrollWidth = this.pages.get(this.pages.size - 1).scrollEnd;
	}
	
	public float totalScrollWidth;
	float viewHeight;
	public Array<Page> pages = new Array<Page>();
	
	public static Vector2 cameraOffset = new Vector2(88.89f, 50f);
	public static float viewH = 100f;
	
	/**
	 * Updates position of camera according to how much has been scrolled. 
	 * @param cam The camera to be updated.
	 * @param progress How much has been scrolled so far.
	 */
	public void updateCamera(OrthographicCamera cam, float progress) {
		for(int i = 0; i < this.pages.size; i++) {
			if(progress >= this.pages.get(i).scrollStart && progress < pages.get(i).scrollEnd) {
				pages.get(i).updateCamera(cam, progress);
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
	public void renderBook(float progress, SpriteBatch sb, OrthographicCamera cam) {
		//camPos is (posX, posY, zoom)
		Vector3 camPos = new Vector3(cam.position.x, cam.position.y, cam.zoom);
		
		for(int i = 0; i < this.pages.size; i++) {
			if(progress >= this.pages.get(i).scrollStart && progress < this.pages.get(i).scrollEnd) {
				//If not first page, check if previous page is in view
				if(i > 0) {
					if(Book.absoluteDifference(cam.position.x, this.pages.get(i-1).endPositionX) < Book.cameraOffset.x*2 
							|| Book.absoluteDifference(cam.position.y, this.pages.get(i-1).endPositionY) < Book.cameraOffset.y*2) {
						pages.get(i - 1).updateFigures(progress, camPos);
						pages.get(i - 1).renderSprites(sb);
						
					}
				}	
				
				//chapter = i;
				pages.get(i).updateFigures(progress, camPos);
				pages.get(i).renderSprites(sb);
				
				//If not last page, then check if next page is in view, and render.
				if(i + 1 < pages.size) {
					if(Book.absoluteDifference(camPos.x, this.pages.get(i).endPositionX) < Book.cameraOffset.x*2 
							|| Book.absoluteDifference(camPos.y, this.pages.get(i).endPositionY) < Book.cameraOffset.y*2) {
						pages.get(i + 1).updateFigures(progress, camPos);
						pages.get(i + 1).renderSprites(sb);
					}
				}				
			}
		}
	}
	
	private static float absoluteDifference(float x, float y) {
		if(x >= y) {
			return x - y;
		} else {
			return y - x;
		}
	}

}
