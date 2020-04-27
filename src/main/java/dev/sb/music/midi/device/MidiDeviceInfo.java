package dev.sb.music.midi.device;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;

/** Available MIDI devices discovery */
public class MidiDeviceInfo {

    public static void displayInfo() {
        MidiDevice.Info[] deviceInfo = MidiSystem.getMidiDeviceInfo();

        if (deviceInfo.length == 0) {
            System.out.println("No MIDI devices found");
            return;
        }

        for (MidiDevice.Info info : deviceInfo) {
            try {
                displayDeviceInfo(info);
            } catch (MidiUnavailableException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void displayInfo(Sequencer sequencer) {
        try {
            displayDeviceInfo(sequencer.getDeviceInfo());
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    private static void displayDeviceInfo(MidiDevice.Info info) throws MidiUnavailableException {
        MidiDevice device = MidiSystem.getMidiDevice(info);
        System.out.printf("%-35s\n", resolveDeviceType(device));
        System.out.printf("%20s: %-15s \n", "name", info.getName());
        System.out.printf("%20s: %-15s \n", "desc", info.getDescription());
        System.out.printf("%20s: %-15s \n", "vendor", info.getVendor());
        System.out.printf("%20s: %-15s \n", "version", info.getVersion());
        System.out.printf("%20s: %-15s \n", "MAX receivers", maxToString(device.getMaxReceivers()));
        System.out.printf("%20s: %-15s \n", "MAX transmitters", maxToString(device.getMaxTransmitters()));
        System.out.println("---------------------------------------------");
    }

    private static String resolveDeviceType(MidiDevice device) {
        if (device instanceof Sequencer) {
            return "SEQUENCER";
        } else if (device instanceof Synthesizer) {
            return "SYNTHESIZER";
        }
        return "unknown";
    }

    private static String maxToString(int max) {
        return max == -1 ? "not limited" : String.valueOf(max);
    }
}
