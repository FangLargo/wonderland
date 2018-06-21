package com.mf.wonderland.debug;

import java.io.StringWriter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.XmlWriter;
import com.mf.wonderland.Book.Anim;
import com.mf.wonderland.Book.Book;
import com.mf.wonderland.Book.Figure;
import com.mf.wonderland.Book.Page;
import com.mf.wonderland.Book.Template.BookTemplate;
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
	
	public static void jsonBookMaker() {
		//Page1
		FigureTemplate p1bg = new FigureTemplate("littledog_bg", 0, 0, 1, 0);
		FigureTemplate p1dog = new FigureTemplate("littledog_dog", 1290, 200, 1, 0);
		
		PageTemplate p1 = new PageTemplate("page1", 1440f, 2560, 0f, 0f, 1920f, 0f);
		p1.atlas = "images/page1.atlas";
		//p1.atlases.add("images/page1.atlas");
		p1.figures.add(p1bg);
		p1.figures.add(p1dog);
		
		//PageTemplate p1 = new PageTemplate(1080f, 1920f, 0f, 0f, 1920f, 0f, new Array<String>, new Array<FigureTemplate>, new Array<AnimTemplate>);
		
		//Page2
		FigureTemplate p2bg = new FigureTemplate("shogatsu2015_bg", 0, 0, 1, 0);
		FigureTemplate p2matsu = new FigureTemplate("shogatsu2015_matsu", 1626, 380, 1, 0);
		FigureTemplate p2girl = new FigureTemplate("shogatsu2015_girl", 1425, 120, 1, 0);
		
		PageTemplate p2 = new PageTemplate("page2", 1440f, 2560f, 0f, 0f, 1920f, 0f);
		p2.atlas = "images/page2.atlas";
		p2.figures.add(p2bg);
		p2.figures.add(p2matsu);
		p2.figures.add(p2girl);
		
		//Page3
		//TODO : Add page 3
		
		BookTemplate book = new BookTemplate();
		
		book.pages.add(p1);
		book.pages.add(p2);
		
		Json json = new Json();
		System.out.println(json.prettyPrint(book));
		
		//FileHandle file = Gdx.files.local("book.json");
		//file.writeString(json.prettyPrint(book), false);
		
		BookTemplate bookCopy = json.fromJson(BookTemplate.class, Gdx.files.local("book.json"));
		System.out.println(bookCopy.pages.get(1).figures.get(2).regionName);
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