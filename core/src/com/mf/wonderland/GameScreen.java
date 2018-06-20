package com.mf.wonderland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;

public class GameScreen implements Screen {
	
	final Wonderland game;
	
	OrthographicCamera camera;
	float screenWidth = 100;
    float pixelsPerUnit = Gdx.graphics.getWidth()/screenWidth;
    float screenHeight = Gdx.graphics.getHeight()/pixelsPerUnit;
    
    public float progress = 0;
    
    //Creates new instance of ScrollHandler
    ScrollHandler scrollHandler = new ScrollHandler();
    
    
    //For debug
    SpriteBatch debugBatch = new SpriteBatch();
    BitmapFont font = new BitmapFont();
    Texture img;
    Sprite sprite;
	
	public GameScreen(final Wonderland gam) {
		this.game = gam;
		
		camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, Gdx.graphics.getHeight()/pixelsPerUnit);
        camera.position.set(0, 0, 0);
		
        Gdx.input.setInputProcessor(new GestureDetector(scrollHandler));
        
        //debugs
        img = new Texture("badlogic.jpg");
        sprite = new Sprite(img, 0, 0, 256, 256);
        sprite.setBounds(0, 0, 100, 100);
        sprite.setPosition(0, 0);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		progress = progress - scrollHandler.dx/pixelsPerUnit;
		//progress = scrollHandler.dx/pixelsPerUnit;
		
		camera.position.set(progress + screenWidth/2, screenHeight/2, 0);
		camera.update();

		
		
		game.batch.setProjectionMatrix(camera.combined); //or your matrix to draw GAME WORLD, not UI
	    game.batch.begin();
	    sprite.draw(game.batch);
	    game.batch.end();
	    
	    //Render debug
	    debugBatch.begin();
	    font.draw(debugBatch, progress + " ", 0, 10);
	    debugBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}