package com.mf.wonderland.Book;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class AutoAnim {
	
	public AutoAnim(String ty, float startS, float endS, float l) {
		this.type = ty;
		this.startScroll = startS;
		this.endScroll = endS;
		this.length = l;
		this.frames = new Array<Vector3>();
		this.time = 0;
	}
	
	public String type;
	public float startScroll;
	public float endScroll;
	public float length;
	
	public Array<Vector3> frames;
	
	private float time;
	
	public Vector2 updateAutoAnim(float progress, float delta) {
		
		if(progress >= this.startScroll && progress < this.endScroll) {
			this.time = time + delta;
			//System.out.println(this.time);
			if(this.time > this.length) {
				this.time = this.time - this.length;
			}
			
			for(int i = 0; i < this.frames.size; i++) {
				if(i < this.frames.size - 1) {
					if(this.time >= this.frames.get(i).z && this.time < this.frames.get(i + 1).z) {
//						System.out.println(i + " : " + time + ", val: " + Anim.interpolate(this.time, this.frames.get(i).z, this.frames.get(i + 1).z,
//								this.frames.get(i).x,
//								this.frames.get(i).y,
//								this.frames.get(i + 1).x,
//								this.frames.get(i + 1).y));
						
						return Anim.interpolate(this.time, this.frames.get(i).z, this.frames.get(i + 1).z,
								this.frames.get(i).x,
								this.frames.get(i).y,
								this.frames.get(i + 1).x,
								this.frames.get(i + 1).y);
					}
				} else {
					if(this.time > this.frames.get(i).z && this.time < this.length) {
						
//						System.out.println(i+ " : " + time + ", val: " + Anim.interpolate(this.time, this.frames.get(i).z, this.length,
//								this.frames.get(i).x,
//								this.frames.get(i).y,
//								this.frames.get(0).x,
//								this.frames.get(0).y));
						
						return Anim.interpolate(this.time, this.frames.get(i).z, this.length,
								this.frames.get(i).x,
								this.frames.get(i).y,
								this.frames.get(0).x,
								this.frames.get(0).y);
					}
				}
			}
		}
		this.time = 0;
		return null;
	}
	
	public Anim toAnimData() {

		Anim anim = new Anim(this.type, 
				this.startScroll, this.frames.get(0).x, this.frames.get(0).y,
				this.endScroll, this.frames.get(this.frames.size-1).x, this.frames.get(this.frames.size-1).y);
		
		
		
		return anim;
		
	}
}
