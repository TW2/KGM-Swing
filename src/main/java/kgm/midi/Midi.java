/*
 * Copyright (C) 2017 TW2
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package kgm.midi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Patch;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import kgm.key.Keys;
import kgm.key.Octave;

/**
 *
 * @author Naruto
 */
public class Midi {
    
    private Synthesizer synthesizer;
    private Soundbank soundBank;
    private static Instrument[] instruments;
    private static MidiChannel[] channels;

    public Midi() {
        init();
    }
    
    private void init(){
        try {
            // initialisation midi : on déclare et on ouvre un synthétiseur
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            
            // utilisation de la banque de sons par défault
            soundBank = synthesizer.getDefaultSoundbank();
            if (soundBank == null) {
                try {
                    soundBank = MidiSystem.getSoundbank(getClass().getResource("kgm/midi/soundbank-deluxe.gm"));
                }catch (IOException | InvalidMidiDataException ex) {
                    System.err.println("Error(s) "+ex.getMessage());
                }
            }
            
            // obtention d'une liste des instruments contenus dans cette banque de sons
            instruments = soundBank.getInstruments();
            
            // obtention des canaux MIDI contrôlés par ce synthétiseur
            channels = synthesizer.getChannels();
            
            synthesizer.loadAllInstruments(soundBank);
            changeInstrument(50);
            
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(Midi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void changeInstrument(int j) {
        Instrument i = instruments[j];
        Patch p = i.getPatch();
        channels[1].programChange(p.getBank(), p.getProgram());
    }
    
    public static void noteOn(Keys key, Octave octave, int velocity){
        int note = getNoteWithOctave(key, octave);
        if(note != -1){
            channels[1].noteOn(note, velocity);//Volume = velocity
        }
        
    }
    
    public static void noteOff(Keys key, Octave octave){
        int note = getNoteWithOctave(key, octave);
        if(note != -1){
            channels[1].noteOff(note);
        }
    }
    
    private static int getNoteWithOctave(Keys key, Octave octave){
        int note = 48;
        
        if(key == Keys.Do){ note = 0 + octave.getInternalReference(); }
        if(key == Keys.DoDiese){ note = 1 + octave.getInternalReference(); }
        if(key == Keys.Re){ note = 2 + octave.getInternalReference(); }
        if(key == Keys.ReDiese){ note = 3 + octave.getInternalReference(); }        
        if(key == Keys.Mi){ note = 4 + octave.getInternalReference(); }        
        
        if(key == Keys.Fa){ note = 5 + octave.getInternalReference(); }
        if(key == Keys.FaDiese){ note = 6 + octave.getInternalReference(); }
        if(key == Keys.Sol){ note = 7 + octave.getInternalReference(); }
        if(key == Keys.SolDiese){ note = 8 + octave.getInternalReference(); }
        if(key == Keys.La){ note = 9 + octave.getInternalReference(); }
        if(key == Keys.LaDiese){ note = 10 + octave.getInternalReference(); }
        if(key == Keys.Si){ note = 11 + octave.getInternalReference(); }
        
        return note > 127 ? -1 : note;
    }
}
