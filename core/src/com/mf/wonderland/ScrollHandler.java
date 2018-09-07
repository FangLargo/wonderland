package com.mf.wonderland;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class ScrollHandler implements GestureListener {
	
	public float dx = 0;
	//Timer timer;
	float speed = 0;
	float damp = 0.65f;

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// Weird glitch/quirk makes velocity unnecessarily fast. 50% speed feels better. 
		speed = velocityX/2;
		
		//Timer is scheduled to repeat every 0.05s until Timer.instance().clear() and is cancelled.
		Timer.schedule(new Task() {
			public void run() {
				if(java.lang.Math.abs(speed)*0.05f > 0.5f) {
					//0.8 is deceleration of the book. 0.05 update means about 20x per second. 
					speed = speed*damp;
					dx = speed*0.05f;
					//System.out.println("Deccel: " + speed + " : " + dx);
				} else {
					//System.out.println("Killed timer: " + speed + " : " + dx);
					dx = 0;
					speed = 0;
					Timer.instance().clear();
				}
			}
		}, 0.05f, 0.05f);
		
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		//dx = deltaX;
		
		if(java.lang.Math.abs(deltaX) != 1f) {
			dx = deltaX;
			//System.out.println("Panning");
		} else {
			dx = 0;
		}
		return true;
	}
	
	@Override
	public boolean panStop(float x, float y, int pointer, int button) {		
		//System.out.println("Panning stopped.");
		return false;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		//System.out.println("Touch down");
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		//System.out.println("Tapped");
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		//System.out.println("Long pressed");
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

	@Override
	public void pinchStop() {
		
	}

	
}