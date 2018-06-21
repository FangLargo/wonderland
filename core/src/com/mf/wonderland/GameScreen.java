package com.mf.wonderland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.PerformanceCounter;
import com.mf.wonderland.Book.Book;
import com.mf.wonderland.debug.BookTest;

public class GameScreen implements Screen {
	
	final Wonderland game;
	
	OrthographicCamera camera;
	float screenHeight = 100;
    float pixelsPerUnit = Gdx.graphics.getHeight()/screenHeight;
    float screenWidth = Gdx.graphics.getWidth()/pixelsPerUnit;
    
    public float progress = 0;
    
    //Creates new instance of ScrollHandler
    ScrollHandler scrollHandler = new ScrollHandler();
    
    
    //For debug
    SpriteBatch debugBatch = new SpriteBatch();
    BitmapFont font = new BitmapFont();
    Texture img;
    Sprite sprite;
    
    TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/page1.atlas"));
    AtlasRegion region = atlas.findRegion("littledog_bg");
    float spriteScale = screenHeight/region.getRegionHeight();
    Book testBook;
    PerformanceCounter count = new PerformanceCounter("counter");
	
	public GameScreen(final Wonderland gam) {
		this.game = gam;
		
		camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        camera.position.set(0, 0, 0);
		
        Gdx.input.setInputProcessor(new GestureDetector(scrollHandler));
        
        //debugs
        //img = new Texture("badlogic.jpg");
        //sprite = new Sprite(img, 0, 0, 256, 256);
        //sprite = atlas.createSprite("littledog_bg");
        //sprite.setBounds(0, 0, region.getRegionWidth()*spriteScale, screenHeight);
        //sprite.setPosition(0, 0);
        
        testBook = BookTest.bookTest();

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
		
		//camera.position.set(progress + screenWidth/2, screenHeight/2, 0);
		
		count.start();
		
		if(progress < 0) {
			progress = 0;
		} else {
			testBook.updateCamera(camera, progress);
		}
		
		
		camera.update();

		
		
		game.batch.setProjectionMatrix(camera.combined); //or your matrix to draw GAME WORLD, not UI
		
	    game.batch.begin();
	    //sprite.draw(game.batch);
	    testBook.renderBook(progress, game.batch);
	    //testBook.pages.get(0).figures.get(0).figureSprite.draw(game.batch);
	    
	    game.batch.end();
	    
	    //Render debug
	    debugBatch.begin();
	    font.draw(debugBatch, progress + " ", 0, 10);
	    debugBatch.end();
	    
	    count.stop();
	    //log.log();
	    count.tick();
	}

	FPSLogger log = new FPSLogger();
	
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