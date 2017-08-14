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
package kgm.key;

/**
 *
 * @author Naruto
 */
public enum Octave {
    OctaveX1(-1,0),
    Octave00(0,12),
    Octave01(1,24),
    Octave02(2,36),
    Octave03(3,48),
    Octave04(4,60),
    Octave05(5,72),
    Octave06(6,84),
    Octave07(7,96),
    Octave08(8,108),
    Octave09(9,120);    

    int realResource;
    int internalRef;

    Octave(int realResource, int internalRef) {
        this.realResource = realResource;
        this.internalRef = internalRef;
    }
    
    public int getRealResource(){
        return internalRef;
    }
    
    public int getInternalReference(){
        return internalRef;
    }
    
    public static Octave getNext(Octave first){
        if(first == OctaveX1) { return Octave00; }
        if(first == Octave00) { return Octave01; }
        if(first == Octave01) { return Octave02; }
        if(first == Octave02) { return Octave03; }
        if(first == Octave03) { return Octave04; }
        if(first == Octave04) { return Octave05; }
        if(first == Octave05) { return Octave06; }
        if(first == Octave06) { return Octave07; }
        if(first == Octave07) { return Octave08; }
        if(first == Octave08) { return Octave09; }
        return first;
    }
}
