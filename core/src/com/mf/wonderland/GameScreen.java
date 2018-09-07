package com.mf.wonderland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import com.mf.wonderland.Book.Book;
import com.mf.wonderland.Book.Page;
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
    
    String bookName = "book.json";
    float pageWidth = 100f;
    Book book;

	public GameScreen(final Wonderland gam) {
		this.game = gam;
		
		camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        camera.position.set(0, 0, 0);
		
        Gdx.input.setInputProcessor(new GestureDetector(scrollHandler));
        
        //debugs        
        //book = BookTest.jsonBookMaker();
        //book = BookTest.templateMaker();
        
        //Open Book:
        book = Book.createNewBook(bookName, pageWidth);
        
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
		
		if(progress < 0) {
			progress = 0f;
		} else if (progress >= book.totalScrollWidth) {
			progress = book.totalScrollWidth - 0.01f;
		}
			
		book.updateCamera(camera, progress);
		book.updateBook(progress, camera, game.batch, delta);

		camera.update();
		
		game.batch.setProjectionMatrix(camera.combined); //or your matrix to draw GAME WORLD, not UI
	    game.batch.begin();

	    //Send in game.batch, Book pulls out each sprite that needs rendering and renders.
	    book.renderBook(progress, game.batch, camera, delta);
	    //testBook.pages.get(0).figures.get(0).figureSprite.draw(game.batch);

	    game.batch.end();
	    
	    //Render debug
	    debugBatch.begin();
	    font.draw(debugBatch, camera.zoom + " ", 0, 60);
	    font.draw(debugBatch, book.uncorrectedProgress + " ", 0, 40);
	    font.draw(debugBatch, camera.position.x + ", " + camera.position.y, 0, 20);
	    debugBatch.end();
	    
	    //log.log();
	    //System.out.println(progress);
	    //System.out.println(camera.zoom);

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