package com.mf.wonderland.Book;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Figure {
	
	public Figure(TextureAtlas atlas, String regionName, float scale, float X, float Y, float Rot) {
		AtlasRegion region = atlas.findRegion(regionName);
		this.figureSprite = atlas.createSprite(regionName);
		//float spriteScale = scale/region.getRegionHeight();
		System.out.println(region.getRegionWidth() + " : " + region.getRegionHeight());
		this.figureSprite.setBounds(X, Y, X + 100*region.getRegionWidth()/scale, Y + 100*region.getRegionHeight()/scale);
	}
	
	public Sprite figureSprite;
	public float startX;
	public float startY;
	//public float startScale;
	public float startRotation;
	
	public Array<Anim> anims = new Array<Anim>();
	
	public void updateFigureAnim(float progress) {
		if(anims.size > 0) {
			for(Anim a: anims) {
				if(a.updateAnim(progress) != null) {
					Vector2 pos = new Vector2(a.updateAnim(progress));
					this.figureSprite.setPosition(pos.x, pos.y);
				}
			}
		}
	}
}