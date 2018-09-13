package com.mf.wonderland;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;

public class WebInputHandler{

	public float dx = 0;
	
	float speed = 0;
	float damp = 0.65f;
	
	float startX = 0;

	InputAdapter inputAdapter = new InputAdapter() {
		@Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            // TODO Auto-generated method stub
			startX = screenX;
			dx = screenX - startX;
			startX = screenX;
			
            return false;
        }
		
		public boolean touchDragged (int screenX, int screenY, int pointer) {
			dx = screenX - startX;
			startX = screenX;
			
			return false;
		}
		
		@Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            // TODO Auto-generated method stub
            dx = 0;
            return false;
        }
	};

}
