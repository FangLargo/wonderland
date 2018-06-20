package com.mf.wonderland;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class ScrollHandler implements GestureListener {
	
	public float dx = 0;
	//Timer timer;
	float speed = 0;

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// Weird glitch/quirk makes velocity unnecessarily fast. 50% speed feels better. 
		speed = velocityX/2;
		
		Timer.schedule(new Task() {
			public void run() {
				if(java.lang.Math.abs(speed)*0.05f > 0.5f) {
					//0.8 is deceleration of the book. 0.05 update means about 20x per second. 
					speed = speed*0.8f;
					dx = speed*0.05f;
					System.out.println("Deccel: " + speed + " : " + dx);
				} else {
					System.out.println("Killed timer: " + speed + " : " + dx);
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
		// TODO Auto-generated method stub
		//System.out.println("Panning");

		dx = deltaX;
		//speed = 0.001f;
		return true;
	}
	
	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
//		if(speed == 0) {
//			dx = 0;
//		}
		return false;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
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