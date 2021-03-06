package com.mf.wonderland.debug;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.mf.wonderland.Book.Anim;
import com.mf.wonderland.Book.AudioCue;
import com.mf.wonderland.Book.Book;
import com.mf.wonderland.Book.Figure;
import com.mf.wonderland.Book.Page;
import com.mf.wonderland.Book.Template.AnimTemplate;
import com.mf.wonderland.Book.Template.AudioTemplate;
import com.mf.wonderland.Book.Template.AutoAnimTemplate;
import com.mf.wonderland.Book.Template.BookTemplate;
import com.mf.wonderland.Book.Template.FigureTemplate;
import com.mf.wonderland.Book.Template.PageTemplate;

public class BookTest {
	
	public static Book jsonBookMaker() {
		//Page1
		FigureTemplate p1bg = new FigureTemplate("littledog_bg", 0, 0, 1, 0, 0, Figure._X);
		FigureTemplate p1dog = new FigureTemplate("littledog_dog", 1290, 200, 1, 0, 2, Figure._X);
		
		AutoAnimTemplate autoAnimDog = new AutoAnimTemplate(Anim.TRANSLATE, 1000, 2560, 4);
		autoAnimDog.frames.add(new Vector3(-200f, 0, 0f));
		autoAnimDog.frames.add(new Vector3(200, 0, 2));
		p1dog.autoAnims.add(autoAnimDog);
		
		PageTemplate p1 = new PageTemplate("page1", 1440f, 0.75f, 2560, 0f, 0f, 2560f, 0f);
		p1.atlas = "images/test/page1.atlas";
		p1.figures.add(p1bg);
		p1.figures.add(p1dog);
		AnimTemplate p2camPreCorrection = new AnimTemplate(Anim.SCALE, 2440, 2560, 1, 0, 1, 0);
		p1.cameraAnims.add(p2camPreCorrection);
		
		AudioTemplate p1DogMusic = new AudioTemplate(AudioCue.MUSIC, "test/Pretty_Little_Dog.mp3", -1, 2600, 1);
		p1DogMusic.frames.add(new Vector2(-1, 0.4f));
		p1DogMusic.frames.add(new Vector2(500, 1));
		p1DogMusic.frames.add(new Vector2(2600, 1));
		p1.audios.add(p1DogMusic);
		
		//Page2
		FigureTemplate p2bg = new FigureTemplate("shogatsu2015_bg", 0, 0, 1, 0, 0, Figure._X);
		FigureTemplate p2matsu = new FigureTemplate("shogatsu2015_matsu", 1426, 60, 1, 0, 0.8f, Figure._X);
		FigureTemplate p2girl = new FigureTemplate("shogatsu2015_girl", 1325, 50, 1, 0, 2, Figure._X);

		AnimTemplate p2camZoomIn = new AnimTemplate(Anim.SCALE, 0, 600, 1, 0, 5, 0);
		AnimTemplate p2Rotate = new AnimTemplate(Anim.ROTATE, 100, 600, 0, 0, 360, 0);
		AnimTemplate p2camMoveIn = new AnimTemplate(Anim.TRANSLATE, 0, 600, 0, 0, 250, 300);
		AnimTemplate p2camPan = new AnimTemplate(Anim.TRANSLATE, 600, 1600, 250, 300, 700, 300);
		AnimTemplate p2camZoomOut = new AnimTemplate(Anim.SCALE, 1600, 2880, 5, 0, 1, 0);
		AnimTemplate p2camMoveOut = new AnimTemplate(Anim.TRANSLATE, 1600, 2880, 700, 300, 0, 0);
		
		
		PageTemplate p2 = new PageTemplate("page2", 1440f, 0.75f, 2880, 0f, 0f, 0f, 0f);
		p2.atlas = "images/test/page2.atlas";
		p2.figures.add(p2bg);
		p2.figures.add(p2matsu);
		p2.figures.add(p2girl);
		
		p2.cameraAnims.add(p2camZoomIn);
		p2.cameraAnims.add(p2Rotate);
		p2.cameraAnims.add(p2camZoomOut);
		p2.cameraAnims.add(p2camMoveIn);
		p2.cameraAnims.add(p2camMoveOut);
		p2.cameraAnims.add(p2camPan);
		
		AudioTemplate p2Japan = new AudioTemplate(AudioCue.MUSIC, "test/Omoigawa.mp3", 0, 2880, 1);
		//p2Japan.frames.add(new Vector2(0, 0));
		p2.audios.add(p2Japan);
		
		//Page3
		FigureTemplate p3bg = new FigureTemplate("dog_bird_bg", 0, -1440, 1, 0, 0, Figure._Y);
		FigureTemplate p3dog = new FigureTemplate("dog_bird_dog", 0, -1440, 1, 0, 0.4f, Figure._Y);
		
		FigureTemplate p3bird = new FigureTemplate("dog_bird_bird", 2500, -1440, 1, 0, 0.4f, Figure._Y);
		AnimTemplate p3birdanim = new AnimTemplate(Anim.TRANSLATE, 1600, 1900, 2560, -1410, 1750, -1410);
		AnimTemplate p3birdanimrot = new AnimTemplate(Anim.ROTATE, 1600, 1900, 0, 0, 360, 0);
		AnimTemplate p3birdanimscale = new AnimTemplate(Anim.SCALE, 1600, 1900, 0.2f, 0.2f, 1, 1);
		p3bird.anims.add(p3birdanim);
		p3bird.anims.add(p3birdanimrot);
		p3bird.anims.add(p3birdanimscale);
		
		AutoAnimTemplate autoAnimBird = new AutoAnimTemplate(Anim.ROTATE, 2301, 2501, 1);
		autoAnimBird.frames.add(new Vector3(-30, 0, 0f));
		autoAnimBird.frames.add(new Vector3(30, 0, 0.5f));
		AutoAnimTemplate autoAnimBirdBounce = new AutoAnimTemplate(Anim.TRANSLATE, 2301, 2501, 1);
		autoAnimBirdBounce.frames.add(new Vector3(50, 400, 0));
		autoAnimBirdBounce.frames.add(new Vector3(0, -70, 0.25f));
		autoAnimBirdBounce.frames.add(new Vector3(-50, 400, 0.5f));
		autoAnimBirdBounce.frames.add(new Vector3(0, -70, 0.75f));
		AutoAnimTemplate autoAnimScale = new AutoAnimTemplate(Anim.SCALE, 2301, 2501, 1);
		autoAnimScale.frames.add(new Vector3(0.5f, 1, 0));
		autoAnimScale.frames.add(new Vector3(1, 0.5f, 0.25f));
		autoAnimScale.frames.add(new Vector3(0.5f, 1, 0.5f));
		autoAnimScale.frames.add(new Vector3(1, 0.5f, 0.75f));
		
		AutoAnimTemplate autoAnimAlpha = new AutoAnimTemplate(Anim.ALPHA, 2301, 2501, 1);
		autoAnimAlpha.frames.add(new Vector3(1f, 0, 0f));
		autoAnimAlpha.frames.add(new Vector3(0f, 0, 0.5f));
		
		p3bird.autoAnims.add(autoAnimBird);
		p3bird.autoAnims.add(autoAnimBirdBounce);
		p3bird.autoAnims.add(autoAnimScale);
		p3bird.autoAnims.add(autoAnimAlpha);
		
		AnimTemplate p3camAnim = new AnimTemplate(Anim.TRANSLATE, 0, 1440, 0, 0, 0, -1440);
		AnimTemplate p2camPostCorrection = new AnimTemplate(Anim.SCALE, 0, 100, 1, 0, 1, 0);
		
		PageTemplate p3 = new PageTemplate("page3", 1440f, 0.75f, 2500f, 0f, 0f, 0f, -1440f);
		p3.atlas = "images/test/page3.atlas";
		p3.figures.add(p3bg);
		p3.figures.add(p3bird);
		p3.figures.add(p3dog);
		p3.cameraAnims.add(p3camAnim);
		p3.cameraAnims.add(p2camPostCorrection);
		
		AudioTemplate p3Whoosh = new AudioTemplate(AudioCue.SOUND, "test/bamboo-swing.wav", 1400, 1600, 1);
		p3.audios.add(p3Whoosh);
		
		BookTemplate book = new BookTemplate();
		
		book.pages.add(p1);
		book.pages.add(p2);
		book.pages.add(p3);
		
		
		
		Json json = new Json();
		json.setOutputType(OutputType.json);
		//System.out.println(json.prettyPrint(book));
		
		//For WRITING books: Only works on desktop/android.
		//NOT HTML
		if(Gdx.app.getType() != ApplicationType.WebGL) {
			FileHandle file = Gdx.files.local("testbook.json");
			file.writeString(json.prettyPrint(book), false);
		}
		
		
		//BookTemplate bookCopy = json.fromJson(BookTemplate.class, Gdx.files.internal("testbook.json"));		
		//Book bookOb = new Book(bookCopy, 100f);
		
		
		//System.out.println(bookOb.totalScrollWidth);
		//System.out.println(json.prettyPrint(bookOb));
		
		return Book.createNewBook("testbook.json", 100f);
	}	
	
	public static Book templateMaker() {		
		//Page3
		FigureTemplate p3bg = new FigureTemplate("dog_bird_bg", -1, -1, 1, 0.1f, 0, Figure._X);
		
		FigureTemplate p3bird = new FigureTemplate("dog_bird_bird", 1250f, 50f, 1, 0.1f, 0f, Figure._Y);
		AnimTemplate p3birdanim = new AnimTemplate(Anim.TRANSLATE, 1, 1900, 1250f, 50f, 1250f, -1410);
		p3bird.anims.add(p3birdanim);
		
		AutoAnimTemplate autoAnimBird = new AutoAnimTemplate(Anim.ROTATE, -1, 2501, 1);
		autoAnimBird.frames.add(new Vector3(-30, 1, 0.001f));
		autoAnimBird.frames.add(new Vector3(30, 1, 0.5f));
		
		p3bird.autoAnims.add(autoAnimBird);
		
		AnimTemplate p3camAnim = new AnimTemplate(Anim.TRANSLATE, -1, 1900, 1, 1, 1, -1440);
		
		PageTemplate p3 = new PageTemplate("page3", 1440f, 0.75f, 1900f, 1f, 1f, 1f, -1440f);
		p3.atlas = "images/test/page3.atlas";
		p3.figures.add(p3bg);
		//p3.figures.add(p3bird);
		p3.cameraAnims.add(p3camAnim);
		
		AudioTemplate p3Whoosh = new AudioTemplate(AudioCue.SOUND, "test/bamboo-swing.wav", 1400, 1600, 1);
		p3.audios.add(p3Whoosh);
		
		//____________________
		
		FigureTemplate p1bg = new FigureTemplate("dog_bird_bg", 0, -1440, 1, 0, 0, Figure._X);
		p3.figures.add(p1bg);
		p3.figures.add(p3bird);
		
		AudioTemplate p1DogMusic = new AudioTemplate(AudioCue.MUSIC, "test/Pretty_Little_Dog.mp3", -1, 1900f, 1);
		p1DogMusic.frames.add(new Vector2(-1, 0.4f));
		p1DogMusic.frames.add(new Vector2(500, 1));
		p1DogMusic.frames.add(new Vector2(1900f, 1));
		p3.audios.add(p1DogMusic);
		
		BookTemplate book = new BookTemplate();
		
		book.pages.add(p3);
		
		Json json = new Json();
		json.setOutputType(OutputType.json);
		//System.out.println(json.prettyPrint(book));
		
		//For WRITING books: Only works on desktop/android.
		//NOT HTML
		if(Gdx.app.getType() != ApplicationType.WebGL) {
			FileHandle file = Gdx.files.local("template.json");
			file.writeString(json.prettyPrint(book), false);
		}
		
		return new Book(book, 100f);
		
		
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
		
	public static Book bookTest() {
		Book testing = new Book();
		Page newPage = new Page();
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/page1.atlas"));
		Figure bg = new Figure(atlas, "littledog_bg", 1080f, 0.75f, 0, 0, 0, 0, 0, 0);
		Figure dog = new Figure(atlas, "littledog_dog", 1080f, 0.75f, 0, 0, 0, 0, 0, 0);
		
		newPage.figures.add(bg);
		newPage.figures.add(dog);
		//newPage.cameraAnims.add(new Anim(Anim.AnimType.TRANSLATE, 0, 0, 0, 100, 177, 0));
		
		Page newPage2 = new Page();
		//newPage2.offsetX = newPage.endPositionX;
		//newPage2.offsetY = newPage.endPositionY;
		
		TextureAtlas atlas2 = new TextureAtlas(Gdx.files.internal("images/page2.atlas"));
		Figure bg2 = new Figure(atlas2, "shogatsu2015_bg", 1080f, 0.75f, 177, 0, 0, 0, 0, 0);
		//Figure dog = new Figure(atlas, "littledog_dog", 1080f, 0, 0, 0);
		newPage2.figures.add(bg2);
		
		Page newPage3 = new Page();
		
		testing.pages.add(newPage);
		testing.pages.add(newPage2);
		
		return testing;
		//Yes, we're testing.
	}
	
}