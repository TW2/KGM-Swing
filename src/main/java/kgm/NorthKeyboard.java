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

import kgm.key.Direction;
import kgm.key.Octave;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Naruto
 */
public class NorthKeyboard extends JPanel {
    
    private int octaveNumber = 1;
    private Octave firstOctave = Octave.Octave04;
    private final List<Mini> miniList = new ArrayList<>();
    
    public NorthKeyboard(int width, int height) {
        init(width, height);
    }
    
    public NorthKeyboard(int width, int height, int octaveNumber, Octave firstOctave) {
        this.octaveNumber = octaveNumber;
        this.firstOctave = firstOctave;
        init(width, height);
    }
    
    private void init(int width, int height){
        setSize(width, height);
        setLayout(null);
        
        int compSizeX = width / octaveNumber;
        int compSizeY = height;
        Octave next = firstOctave;
        
        for(int i=0; i<octaveNumber; i++){
            Mini m = new Mini(compSizeX, compSizeY, Direction.North, next);
            m.setLocation(width - compSizeX*(i+1), 0);
            miniList.add(m);
            this.add(m);
            next = Octave.getNext(next);
        }
    }
    
    public List<Mini> getMinis(){
        return miniList;
    }
}
