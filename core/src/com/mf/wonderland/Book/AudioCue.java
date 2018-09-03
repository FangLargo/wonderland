package com.mf.wonderland.Book;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class AudioCue {
	
	public String type;
	public int page;
	public String reference;
	public float start;
	public float end;
	
	public Array<Vector2> frames = new Array<Vector2>();
	public float maxVolume;
	
	private float ease = 0.5f;
	private float currentVolume;
	private boolean played = false;
	
	public float getVolume(float progress, float delta) {
		if(this.type.equals(AudioCue.SOUND)) {
			if(played == false) {
				if(progress > this.start && progress < this.end) {
					played = true;
					return this.maxVolume;
				} else {
					return -1;
				}
			} else {
				if(progress < this.start) {
					played = false;
				}
			}
		} else if(this.type.equals(AudioCue.MUSIC)) {
			if(this.frames.size > 0) {
				//IF THERE ARE FRAMES, CHECK WHICH FRAME IS RELEVANT
				for(int i = 0; i < this.frames.size; i++) {
					if(i != 0 && i != this.frames.size - 1) {
						//System.out.println(i);
						if(progress > this.frames.get(i).x && progress < this.frames.get(i + 1).x) {
							this.currentVolume = Anim.interpolate(progress, this.frames.get(i).x, this.frames.get(i + 1).x, 
									this.frames.get(i).y, 0f, 
									this.frames.get(i + 1).y, 0f).x;
							return this.currentVolume;
						}
					} else if(i == 0) {
						if(progress > this.frames.get(i).x && progress < this.frames.get(i + 1).x) {
							this.currentVolume = Anim.interpolate(progress, this.frames.get(i).x, this.frames.get(i + 1).x, 
									this.frames.get(i).y, 0f, 
									this.frames.get(i + 1).y, 0f).x;
							return this.currentVolume;
						} else if(progress < this.frames.get(i).x) {
							this.currentVolume = Anim.interpolate(progress, 0, this.frames.get(i).x, 
									0, 0f, 
									this.frames.get(i).y, 0f).x;
							return this.currentVolume;
						}
					} else if(i == this.frames.size - 1) {
						if(progress > this.frames.get(i).x) {
							this.currentVolume = Anim.interpolate(progress, this.frames.get(i).x, this.end, 
									this.frames.get(i).y, 0f, 
									0, 0f).x;
							return this.currentVolume;
						}
					}
				}
			} else {
				// IF NO FRAMES, THEN FADE IN AND OUT
				if(progress > this.start && progress < this.end) {
					
					this.currentVolume = this.currentVolume + delta*this.ease;
					if(this.currentVolume > this.maxVolume) {
						this.currentVolume = this.maxVolume;
					}
					//System.out.println(this.currentVolume);
					return this.currentVolume;
				} else {
					if(this.currentVolume != 0) {
						this.currentVolume = this.currentVolume - delta*this.ease;
						if(this.currentVolume < 0) {
							this.currentVolume = 0;
						}
						return this.currentVolume;
					}
				}
				
				return 0;
			}
		}
		
		
		return -1;
	}
	
	public static String MUSIC = "music";
	public static String SOUND = "sound";
}
