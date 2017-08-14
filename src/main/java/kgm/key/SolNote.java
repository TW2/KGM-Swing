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

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Naruto
 */
public class SolNote extends ANote {

    public SolNote(int parentWidth, int parentHeight, Direction direction, Octave octave) {
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
        this.direction = direction;
        
        this.octave = octave;
        
        key = Keys.Sol;
        
        init();
    }
    
    private void init(){
        
        Rectangle2D r1, r2;
        Area a1, a2;
        
        switch(direction){
            case South:
                configure();
                r1 = new Rectangle2D.Double(x + width *1/3, 0, width - width *2/3, blackKeySizeY); a1 = new Area(r1);
                r2 = new Rectangle2D.Double(x, blackKeySizeY, width, whiteKeySizeY - blackKeySizeY); a2 = new Area(r2);
                a1.add(a2);
                shapeOfNote = a1;
                break;
            case North:
                configure(direction);
                double xa = parentWidth - whiteKeySizeX - x;
                r1 = new Rectangle2D.Double(xa, y, whiteKeySizeX, whiteKeySizeY - blackKeySizeY); a1 = new Area(r1);
                r2 = new Rectangle2D.Double(xa + width *1/3, whiteKeySizeY - blackKeySizeY, width - width *2/3,  height - parentHeight/6); a2 = new Area(r2);
                a1.add(a2);
                shapeOfNote = a1;
                break;
            case East:
                configure(direction);
                r1 = new Rectangle2D.Double(x, y, whiteKeySizeX - blackKeySizeX, whiteKeySizeY); a1 = new Area(r1);
                r2 = new Rectangle2D.Double(whiteKeySizeX - blackKeySizeX, y + height *1/3, blackKeySizeX + parentWidth/6, height  - height *2/3); a2 = new Area(r2);
                a1.add(a2);
                shapeOfNote = a1;
                break;
            case West:
                configure(direction);
                double ya = parentHeight - whiteKeySizeY - y;
                r1 = new Rectangle2D.Double(0, ya + height * 1/3, blackKeySizeX, whiteKeySizeY - height * 2/3); a1 = new Area(r1);
                r2 = new Rectangle2D.Double(blackKeySizeX, ya, whiteKeySizeX - blackKeySizeX, whiteKeySizeY); a2 = new Area(r2);
                a1.add(a2);
                shapeOfNote = a1;
                break;
        }
    }
}
