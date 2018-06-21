package com.mf.wonderland.Book.Template;

import com.badlogic.gdx.utils.Array;
import com.mf.wonderland.Book.Page;

public class BookTemplate {
	public BookTemplate(Array<PageTemplate> ps) {
		this.pages = ps;
	}
	
	public Array<PageTemplate> pages = new Array<PageTemplate>();
}
