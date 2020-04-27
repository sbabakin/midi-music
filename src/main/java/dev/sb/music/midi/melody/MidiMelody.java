package dev.sb.music.midi.melody;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

public interface MidiMelody {

    String melodyName();

    Sequence melodySequence() throws InvalidMidiDataException;
}
