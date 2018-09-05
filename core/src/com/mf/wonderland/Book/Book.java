package com.mf.wonderland.Book;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.mf.wonderland.Book.Template.AudioTemplate;
import com.mf.wonderland.Book.Template.AutoAnimTemplate;
import com.mf.wonderland.Book.Template.BookTemplate;

public class Book {
	public Book() {
		
	}
	
	public static Book createNewBook(String title, float pageWidth) {
		Json json = new Json();
		BookTemplate bookCopy = json.fromJson(BookTemplate.class, Gdx.files.internal(title));
		Book bookOb = new Book(bookCopy, pageWidth);
		
		return bookOb;
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
			//Offset becomes last positions X and Y and scrollEnd
			
			this.pages.get(i).scrollStart = offset.z;
			this.pages.get(i).scrollEnd = template.pages.get(i).scrollWidth/scale + offset.z;
			
			this.pages.get(i).startPositionX = offset.x;
			this.pages.get(i).startPositionY = offset.y;
			this.pages.get(i).endPositionX = template.pages.get(i).endPositionX/scale + offset.x;
			this.pages.get(i).endPositionY = template.pages.get(i).endPositionY/scale + offset.y;
			//
			
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
				
				//Include all AutoAnim in figure
				for(AutoAnimTemplate a: template.pages.get(i).figures.get(j).autoAnims) {
					AutoAnim tempAutoAnim = new AutoAnim(
							a.type,
							a.startScroll/scale + offset.z,
							a.endScroll/scale + offset.z,
							a.length);
					if(!a.type.equals(Anim.TRANSLATE)) {
						tempAutoAnim.frames = a.frames;
					} else {
						for(int k = 0; k < a.frames.size; k++) {
							Vector3 val = new Vector3(
									a.frames.get(k).x/scale, 
									a.frames.get(k).y/scale,
									a.frames.get(k).z);
							tempAutoAnim.frames.add(val);
						}
					}
					
					this.pages.get(i).figures.get(j).autoAnims.add(tempAutoAnim);
					//System.out.println("Adding Auto");
				}
				
				//Include all audio
				for(AudioTemplate a: template.pages.get(i).audios) {
					boolean exists = false;
					
					//Check if sound already exists in record.
					if(a.type.equals(AudioCue.MUSIC)) {
						for(MusicReference m: audioManager.musicReferences) {
							if(m.ref.equals(a.name)) {
								exists = true;
							}
						}
						
						if(!exists) {
							MusicReference temp = new MusicReference();
							temp.ref = a.name;
							temp.music = Gdx.audio.newMusic(Gdx.files.internal("audio/music/" + a.name));
							temp.music.setLooping(true);
							this.audioManager.musicReferences.add(temp);
							
							//System.out.println("addingMusic");
						}
					} else if(a.type.equals(AudioCue.SOUND)) {
						for(SoundReference s: audioManager.soundReferences) {
							if(s.ref.equals(a.name)) {
								exists = true;
							}
						}
						
						if(!exists) {
							SoundReference temp = new SoundReference();
							temp.ref = a.name;
							temp.sound = Gdx.audio.newSound(Gdx.files.internal("audio/sound/" + a.name));
							
							this.audioManager.soundReferences.add(temp);
							//System.out.println("addingSound");
						}
					}
					
					//Add the Cue
					AudioCue tempCue = new AudioCue();
					tempCue.type = a.type;
					tempCue.page = i;
					tempCue.reference = a.name;
					tempCue.start = a.start/scale + offset.z;
					tempCue.end = a.end/scale + offset.z;
					tempCue.maxVolume = a.maxVolume;
					
					//tempCue.frames.add(new Vector2(0, 0));
					
					if(a.frames.size >= 1) {
						//tempCue.frames.add(new Vector2(0, 0));
						for(Vector2 v: a.frames) {
							tempCue.frames.add(new Vector2(v.x/scale, v.y));
						}
						//tempCue.frames.add(new Vector2(a.end, 0));
					} 
//					else {
//						float interval = (a.end - a.start)*0.1f;
//						Vector2 v1 = new Vector2(interval/scale, a.maxVolume);
//						Vector2 v2 = new Vector2((a.end - interval)/scale, a.maxVolume);
//						tempCue.frames.add(v1);
//						tempCue.frames.add(v2);
//						tempCue.frames.add(new Vector2(a.end/scale, 0));
//					}
					
					this.audioManager.audioCues.add(tempCue);
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
	
//	public Array<MusicReference> musicReferences = new Array<MusicReference>();
//	public Array<SoundReference> soundReferences = new Array<SoundReference>();
	AudioManager audioManager = new AudioManager();
	
	public static Vector2 cameraOffset = new Vector2(88.89f, 50f);
	public static float viewH = 100f;

	private int currentPage = 0;
	
	/**
	 * Updates audio when called.
	 * @param progress
	 */
	public void updateBook(float progress, OrthographicCamera cam, SpriteBatch sb, float delta) {
		//this.updateCamera(cam, progress);
		//this.renderBook(progress, sb, cam, delta);
		audioManager.updateAudioManager(progress, currentPage, delta);
	}
	
	/**
	 * Updates position of camera according to how much has been scrolled. 
	 * @param cam The camera to be updated.
	 * @param progress How much has been scrolled so far.
	 */
	public void updateCamera(OrthographicCamera cam, float progress) {
		for(int i = 0; i < this.pages.size; i++) {
			if(progress >= this.pages.get(i).scrollStart && progress < pages.get(i).scrollEnd) {
				pages.get(i).updateCamera(cam, progress);
				currentPage = i;
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
	public void renderBook(float progress, SpriteBatch sb, OrthographicCamera cam, float delta) {
		//camPos is (posX, posY, zoom)
		Vector3 camPos = new Vector3(cam.position.x, cam.position.y, cam.zoom);
		
		for(int i = 0; i < this.pages.size; i++) {
			if(progress >= this.pages.get(i).scrollStart && progress < this.pages.get(i).scrollEnd) {
				//If not first page, check if previous page is in view
				if(i > 0) {
					if(Book.absoluteDifference(cam.position.x, this.pages.get(i-1).endPositionX) < Book.cameraOffset.x*2 
							|| Book.absoluteDifference(cam.position.y, this.pages.get(i-1).endPositionY) < Book.cameraOffset.y*2) {
						pages.get(i - 1).updateFigures(progress, camPos, delta);
						pages.get(i - 1).renderSprites(sb);
						
					}
				}	
				
				//chapter = i;
				pages.get(i).updateFigures(progress, camPos, delta);
				pages.get(i).renderSprites(sb);
				
				//If not last page, then check if next page is in view, and render.
				if(i + 1 < pages.size) {
					if(Book.absoluteDifference(camPos.x, this.pages.get(i).endPositionX) < Book.cameraOffset.x*2 
							|| Book.absoluteDifference(camPos.y, this.pages.get(i).endPositionY) < Book.cameraOffset.y*2) {
						pages.get(i + 1).updateFigures(progress, camPos, delta);
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
