package dev.sb.music.midi;

import dev.sb.music.midi.device.MidiDeviceInfo;
import dev.sb.music.midi.device.MidiSequencer;
import dev.sb.music.midi.melody.MidiMelody;
import dev.sb.music.midi.melody.lib.MusicScaleMelody;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

/**
 * Uses default software sequencer to play melody,
 * each MIDI event can be created manually as a melody.
 */
public class MidiPlayer {

    public static void main(String[] args) {
        try {
            MidiPlayer midiPlayer = new MidiPlayer();
            midiPlayer.play(60, new MusicScaleMelody());
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            System.out.println("MIDI playback exception");
            e.printStackTrace();
        }
    }

    public void play(float tempoInBPM, MidiMelody melody) throws InvalidMidiDataException, MidiUnavailableException {
        Sequencer sequencer = MidiSequencer.getSequencer();
        MidiDeviceInfo.displayInfo(sequencer);

        try {
            sequencer.open();
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }

        sequencer.setTempoInBPM(tempoInBPM);
        sequencer.setSequence(melody.melodySequence());

        // Play sequence
        System.out.println("Enjoy: " + melody.melodyName());
        sequencer.start();
        while (sequencer.isRunning()) {
            sleep();
        }
        sequencer.close();
        System.out.println("Done...");
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
