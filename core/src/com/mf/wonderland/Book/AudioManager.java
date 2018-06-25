package com.mf.wonderland.Book;

import com.badlogic.gdx.utils.Array;

public class AudioManager {

	
	
	public Array<MusicReference> musicReferences = new Array<MusicReference>();
	public Array<SoundReference> soundReferences = new Array<SoundReference>();
	public Array<AudioCue> audioCues = new Array<AudioCue>();
	
	public void updateAudioManager(float progress, int page, float delta) {
		for(AudioCue a: this.audioCues) {

			this.playCue(a, progress, delta);
		}
	}
	
	public int findReference(AudioCue cue) {
		//System.out.println(cue.type);
		if(cue.type == AudioCue.MUSIC) {

			for(int i = 0; i < this.musicReferences.size; i++) {

				if(cue.reference.equals(this.musicReferences.get(i).ref)) {

					return i;
				}
			}
		} else if(cue.type == AudioCue.SOUND) {
			for(int i = 0; i < this.soundReferences.size; i++) {
				if(cue.reference.equals(this.soundReferences.get(i).ref)) {
					return i;
				}
			}
		}
		
		return -1;
	}
	
	public void playCue(AudioCue cue, float progress, float delta) {
		float vol = cue.getVolume(progress, delta);
		int index = this.findReference(cue);
		if(vol > 0) {

			if(cue.type == AudioCue.MUSIC) {

				this.musicReferences.get(index).music.setVolume(vol);
				this.musicReferences.get(index).music.play();

			} else if(cue.type == AudioCue.SOUND) {
				
				this.soundReferences.get(index).sound.play(vol);
			}
		} else {
			if(cue.type == AudioCue.MUSIC) {

				this.musicReferences.get(index).music.stop();

			} else if(cue.type == AudioCue.SOUND) {
				
				//this.soundReferences.get(index).sound.play(vol);
			}
		}
	}
}
