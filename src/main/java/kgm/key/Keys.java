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
public enum Keys {
    Do("Do", "C", 0, true),
    Re("Ré", "D", 1, true),
    Mi("Mi", "E", 2, true),
    Fa("Fa", "F", 3, true),
    Sol("Sol", "G", 4, true),
    La("La", "A", 5, true),
    Si("Si", "B", 6, true),
    DoDiese("Do diese", "C#", 0, false),
    ReDiese("Ré diese", "D#", 1, false),
    FaDiese("Fa diese", "F#", 3, false),
    SolDiese("Sol diese", "G#", 4, false),
    LaDiese("La diese", "A#", 5, false);
    
    String name;
    String englishName;
    int position;
    boolean white;
    
    Keys(String name, String englishName, int position, boolean white){
        this.name = name;
        this.englishName = englishName;
        this.position = position;
        this.white = white;
    }
    
    public String getName(){
        return name;
    }
    
    public String getEnglishName(){
        return englishName;
    }
    
    public int getPosition(){
        return position;
    }

    public boolean isWhite() {
        return white;
    }
}
