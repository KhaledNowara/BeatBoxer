package SoundPlayer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.sound.midi.*;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class Music {
	Sequencer sequencer ;
	Sequence seq;
	Track track;
	public Music() {
		try {
		    sequencer = MidiSystem.getSequencer();
			sequencer.open();
			 seq = new Sequence(Sequence.PPQ, 4);
			 track = seq.createTrack();
			sequencer.setTempoInBPM(120);
		}catch(Exception e) {e.printStackTrace();}
	}
	public void makeTrack(int[] instruments,ArrayList <JCheckBox> list) {
		int tracklist [] = null;
		seq.deleteTrack(track);
		track= seq.createTrack();
		for (int i =0; i<16 ;i++ ) {
			tracklist = new int[16];
			int key = instruments[i];
			for (int j=0; j<16; j++) {
				JCheckBox jc = (JCheckBox) list.get(j + (16*i));
				if (jc.isSelected())
					tracklist [j] =key;
				else {
					tracklist [j]=0;
				}
			}
			makeTracks(tracklist);
			track.add(makeEvent(176,1,127,0,16));
		}
		track.add(makeEvent(192,9,1,0,15));
		try {
			sequencer.setSequence(seq);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);;
		}catch(Exception e) {e.printStackTrace();}
	}
	private MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
		ShortMessage a = new ShortMessage();
		a.setMessage(comd, chan, one, two);
		event = new MidiEvent(a, tick);
		}catch(Exception e) { }
		return event;}
	
		
	private void makeTracks(int[] list) {
		for (int i = 0; i < 16; i++) {
			int key = list[i];
			if (key != 0) {
				track.add(makeEvent(144,9,key, 100, i));
				track.add(makeEvent(128,9,key, 100, i+1));
		}
		
		}}}
	
	



