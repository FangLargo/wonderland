package com.mf.wonderland.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.mf.wonderland.Book.Anim;
import com.mf.wonderland.Book.Book;
import com.mf.wonderland.Book.Figure;
import com.mf.wonderland.Book.Page;
import com.mf.wonderland.Book.Template.FigureTemplate;
import com.mf.wonderland.Book.Template.PageTemplate;

public class BookTest {
	public static Book bookTest() {
		Book testing = new Book();
		Page newPage = new Page();
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/page1.atlas"));
		Figure bg = new Figure(atlas, "littledog_bg", 1080f, 0, 0, 0);
		Figure dog = new Figure(atlas, "littledog_dog", 1080f, 0, 0, 0);
		
		newPage.figures.add(bg);
		newPage.figures.add(dog);
		//newPage.cameraAnims.add(new Anim(Anim.AnimType.TRANSLATE, 0, 0, 0, 100, 177, 0));
		
		Page newPage2 = new Page();
		newPage2.offsetX = newPage.endPositionX;
		newPage2.offsetY = newPage.endPositionY;
		
		TextureAtlas atlas2 = new TextureAtlas(Gdx.files.internal("images/page2.atlas"));
		Figure bg2 = new Figure(atlas2, "shogatsu2015_bg", 1080f, 177, 0, 0);
		//Figure dog = new Figure(atlas, "littledog_dog", 1080f, 0, 0, 0);
		newPage2.figures.add(bg2);
		
		Page newPage3 = new Page();
		
		testing.pages.add(newPage);
		testing.pages.add(newPage2);
		
		return testing;
		//Yes, we're testing.
	}
	
	public static void xmlBookMaker() {
		//Page1
		FigureTemplate p1bg = new FigureTemplate("littledog_bg", 0, 0, 0);
		FigureTemplate p1dog = new FigureTemplate("littledog_dog", 0, 0, 0);
		
		//PageTemplate p1 = new PageTemplate(1080f, 1920f, 0f, 0f, 1920f, 0f, new Array<String>, new Array<FigureTemplate>, new Array<AnimTemplate>);
		
		//Page2
		
		//Page3
	}
	
	public static void nullCheck() {
		Object x = null;
		float p = 5;
		
		if(p == 5f) {
			System.out.println("p is 5");
		}
		
		if(x == null) {
			System.out.println("x is null");
		}
	}
}