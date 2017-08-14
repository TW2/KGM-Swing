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
package kgm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import kgm.key.ANote;
import kgm.key.Direction;
import kgm.key.DoDiese;
import kgm.key.DoNote;
import kgm.key.FaDiese;
import kgm.key.FaNote;
import kgm.key.LaDiese;
import kgm.key.LaNote;
import kgm.key.MiNote;
import kgm.key.Octave;
import kgm.key.ReDiese;
import kgm.key.ReNote;
import kgm.key.SiNote;
import kgm.key.SolDiese;
import kgm.key.SolNote;
import kgm.midi.Midi;

/**
 *
 * @author Naruto
 */
public class Mini extends JPanel {
    
    private int sizeX = 800;
    private int sizeY = 600;
    
    private final List<ANote> notes = new ArrayList<>();
    
    private Color backColor = Color.gray;
    
    //Start midi
    Midi midi = new Midi();

    public Mini(int sizeX, int sizeY, Direction direction, Octave octave) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        
        init(direction, octave);
    }
    
    private void init(Direction direction, Octave octave){
        setDoubleBuffered(true);
        setSize(sizeX, sizeY);
        
        notes.add(new DoNote(sizeX, sizeY, direction, octave));
        notes.add(new ReNote(sizeX, sizeY, direction, octave));
        notes.add(new MiNote(sizeX, sizeY, direction, octave));
        notes.add(new FaNote(sizeX, sizeY, direction, octave));
        notes.add(new SolNote(sizeX, sizeY, direction, octave));
        notes.add(new LaNote(sizeX, sizeY, direction, octave));
        notes.add(new SiNote(sizeX, sizeY, direction, octave));
////        
        notes.add(new DoDiese(sizeX, sizeY, direction, octave));
        notes.add(new ReDiese(sizeX, sizeY, direction, octave));
        notes.add(new FaDiese(sizeX, sizeY, direction, octave));
        notes.add(new SolDiese(sizeX, sizeY, direction, octave));
        notes.add(new LaDiese(sizeX, sizeY, direction, octave));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                _mousePressed(e);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                _mouseReleased(e);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                _mouseExited(e);
                repaint();
            }
            
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                _mouseMoved(e);
                repaint();
            }
        });
        
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        //Remplit entièrement la zone
        g.setColor(backColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        for(ANote note : notes){
            note.draw((Graphics2D)g);
        }
    }
    
    public void _mousePressed(MouseEvent e){
        for(ANote note : notes){
            if(note.getShape().contains(e.getPoint())){
                note.setMousePressed();
            }
        }
    }
    
    public void _mouseReleased(MouseEvent e) {
        for(ANote note : notes){
            if(note.getShape().contains(e.getPoint())){
                note.setMouseReleased();
            }
        }
    }
    
    public void _mouseMoved(MouseEvent e) {
        for(ANote note : notes){
            note.setMouseExited();
            if(note.getShape().contains(e.getPoint())){
                note.setMouseEntered();
            }
        }
    }
    
    public void _mouseExited(MouseEvent e) {
        for(ANote note : notes){
            note.setMouseExited();
        }
    }
    
    public void changeMidiInstruments(int instrument){
        Midi.changeInstrument(instrument);
    }
    
    public List<ANote> getNotes(){
        return notes;
    }

    public void setBackColor(Color backColor) {
        this.backColor = backColor;
    }

    public Color getBackColor() {
        return backColor;
    }
}
