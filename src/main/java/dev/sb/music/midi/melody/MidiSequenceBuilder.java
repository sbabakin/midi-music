package dev.sb.music.midi.melody;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MidiSequenceBuilder {
    private Sequence sequence;
    private Track track;
    private int baseVelocity;

    private MidiSequenceBuilder(float timingType, int resolution, int baseVelocity) throws InvalidMidiDataException {
        this.baseVelocity = baseVelocity;
        this.sequence = new Sequence(timingType, resolution);
        this.track = sequence.createTrack();
    }

    public static MidiSequenceBuilder builder(float timingType, int resolution, int baseVelocity) throws InvalidMidiDataException {
        return new MidiSequenceBuilder(timingType, resolution, baseVelocity);
    }

    public Sequence buildSequence() {
        return sequence;
    }

    public MidiSequenceBuilder addNote(int atTick, int tone, int length) throws InvalidMidiDataException {
        addNote(atTick, tone, length, baseVelocity);
        return this;
    }

    public MidiSequenceBuilder addNote(int atTick, int tone, int length, int velocity) throws InvalidMidiDataException {
        ShortMessage on = new ShortMessage();
        on.setMessage(ShortMessage.NOTE_ON, 0, tone, velocity);
        track.add(new MidiEvent(on, atTick));

        ShortMessage off = new ShortMessage();
        off.setMessage(ShortMessage.NOTE_OFF, 0, tone, 0);
        track.add(new MidiEvent(off, atTick + length - 1));

        return this;
    }
}
