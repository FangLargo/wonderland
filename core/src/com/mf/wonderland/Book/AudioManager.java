package com.mf.wonderland.Book;

import com.badlogic.gdx.utils.Array;

public class AudioManager {

	
	
	public Array<MusicReference> musicReferences = new Array<MusicReference>();
	public Array<SoundReference> soundReferences = new Array<SoundReference>();
	public Array<AudioCue> audioCues = new Array<AudioCue>();
	
	public void updateAudioManager(float progress, int page, float delta) {
		for(AudioCue a: this.audioCues) {
			//this.playCue(a, progress, delta);
			
			float vol = a.getVolume(progress, delta);
			int index = this.findReference(a);
			//System.out.println(a.reference + ": " + a.page + ", "  + page);
			
			
			
			if(vol > 0 && a.page == page) {
				
				if(a.type.equals(AudioCue.MUSIC) && a.page == page) {
					this.musicReferences.get(index).music.setVolume(vol);
					this.musicReferences.get(index).music.play();

					this.musicReferences.get(index).checkOut = a;

					//System.out.println(a.reference + ", " + a.page + ", " + page);

				} 
				else if(a.type.equals(AudioCue.MUSIC) && a.page != page) {
					this.musicReferences.get(index).music.stop();
					this.musicReferences.get(index).checkOut = null;
				}
				else if(a.type.equals(AudioCue.SOUND)) {
					
					this.soundReferences.get(index).sound.play(vol);
					
				} 
				
			} else {
				if(a.type.equals(AudioCue.MUSIC)) {
					
					if(this.musicReferences.get(index).checkOut != null 
							&& this.musicReferences.get(index).checkOut.equals(a)) {
						this.musicReferences.get(index).music.stop();
						this.musicReferences.get(index).checkOut = null;
					}

				} else if(a.type.equals(AudioCue.SOUND)) {
					
					//this.soundReferences.get(index).sound.stop();
				}
			}
			
		}
	}
	
	public int findReference(AudioCue cue) {
		//System.out.println(cue.type);
		if(cue.type.equals(AudioCue.MUSIC)) {

			for(int i = 0; i < this.musicReferences.size; i++) {

				if(cue.reference.equals(this.musicReferences.get(i).ref)) {

					return i;
				}
			}
		} else if(cue.type.equals(AudioCue.SOUND)) {
			for(int i = 0; i < this.soundReferences.size; i++) {
				if(cue.reference.equals(this.soundReferences.get(i).ref)) {
					return i;
				}
			}
		}
		
		return -1;
	}
	
	
	//To be deprecated.
	public void playCue(AudioCue cue, float progress, float delta) {
		float vol = cue.getVolume(progress, delta);
		int index = this.findReference(cue);
		//System.out.println(cue.reference + ": " + vol);
		if(vol > 0) {
			
			if(cue.type.equals(AudioCue.MUSIC)) {

				this.musicReferences.get(index).music.setVolume(vol);
				this.musicReferences.get(index).music.play();

			} else if(cue.type.equals(AudioCue.SOUND)) {
				
				this.soundReferences.get(index).sound.play(vol);
				
			}
		} else {
			if(cue.type.equals(AudioCue.MUSIC)) {

				this.musicReferences.get(index).music.stop();

			} else if(cue.type.equals(AudioCue.SOUND)) {
				
				//this.soundReferences.get(index).sound.stop();
			}
		}
	}
}
