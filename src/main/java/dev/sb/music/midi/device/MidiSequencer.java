package dev.sb.music.midi.device;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class MidiSequencer {
    private static final String DEFAULT_SEQUENCER = "default";
    private static Sequencer sequencer;

    public static Sequencer getSequencer() throws MidiUnavailableException {
        return getInstance(DEFAULT_SEQUENCER);
    }

    public static Sequencer getInstance(String name) throws MidiUnavailableException {
        System.out.printf("Chosen sequencer: %s\n", name);
        if (sequencer == null) {
            System.setProperty("javax.sound.midi.Sequence", name);
            sequencer = MidiSystem.getSequencer();
        }
        return sequencer;

    }
}
