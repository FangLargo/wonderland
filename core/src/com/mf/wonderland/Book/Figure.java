package com.mf.wonderland.Book;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Figure {
	
	public Figure(TextureAtlas atlas, String regionName, float stageScale, float imageScale, float X, float Y, float startScale, float Rot, float parallax) {		
		AtlasRegion region = atlas.findRegion(regionName);
		this.figureSprite = atlas.createSprite(regionName);

		float xT = X/stageScale;
		float yT = Y/stageScale;
		this.figureSprite.setBounds(0, 0, region.getRegionWidth()/stageScale/imageScale, region.getRegionHeight()/stageScale/imageScale);

		this.figureSprite.setPosition(xT, yT);
		
//		this.figureSprite.setOriginCenter();
//		this.figureSprite.setRotation(Rot);

		this.parallaxDist = parallax;
		
		//System.out.println("name: " + regionName + " : di: " + this.figureSprite.getWidth() + ", " +  this.figureSprite.getHeight());
		//System.out.println(stageScale + " : " + imageScale);
	}
	
	public Sprite figureSprite;
	public float scale;
	public float parallaxDist;
	
	public Array<Anim> anims = new Array<Anim>();
	
	public void updateFigureAnim(float progress) {
		if(anims.size > 0) {
			for(Anim a: anims) {
				if(a.updateAnim(progress) != null) {
					Vector2 val = new Vector2(a.updateAnim(progress));
					
					if(a.type.equals(Anim.TRANSLATE)) {
						this.figureSprite.setPosition(val.x, val.y);
					} else if(a.type.equals(Anim.ROTATE)) {
						this.figureSprite.setOriginCenter();
						this.figureSprite.setRotation(val.x);
					}
					
				}
			}
		}
	}
	
	public void updateFigureParallax(float progress, OrthographicCamera camera) {
		
	}
}