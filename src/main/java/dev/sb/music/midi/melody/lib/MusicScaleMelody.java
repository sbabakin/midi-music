package dev.sb.music.midi.melody.lib;

import dev.sb.music.midi.melody.MidiMelody;
import dev.sb.music.midi.melody.MidiSequenceBuilder;
import dev.sb.music.midi.melody.Tones;
import dev.sb.music.midi.melody.Velocity;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

public class MusicScaleMelody implements MidiMelody {
    @Override
    public String melodyName() {
        return "Music scale C-Maj";
    }

    @Override
    public Sequence melodySequence() throws InvalidMidiDataException {
        return MidiSequenceBuilder.builder(Sequence.PPQ, 2, Velocity.F)
            .addNote(0, Tones.C4, 2)
            .addNote(13, Tones.C4, 2)

            .addNote(1, Tones.D4, 2)
            .addNote(12, Tones.D4, 2)

            .addNote(2, Tones.E4, 2)
            .addNote(11, Tones.E4, 2)

            .addNote(3, Tones.F4, 2)
            .addNote(10, Tones.F4, 2)

            .addNote(4, Tones.G4, 2)
            .addNote(9, Tones.G4, 2)

            .addNote(5, Tones.A4, 2)
            .addNote(8, Tones.A4, 2)

            .addNote(6, Tones.B4, 2)
            .addNote(7, Tones.B4, 2)
            .buildSequence();
    }
}
