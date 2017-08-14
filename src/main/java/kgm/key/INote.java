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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Naruto
 */
public interface INote {
    
    public Keys getKey();
    public void setKey(Keys key);    
    public int getVelocity();
    public void setVelocity(int velocity);
    
    public double getParentWidth();
    public void setParentWidth(double parentWidth);
    public double getParentHeight();
    public void setParentHeight(double parentHeight);
    
    public void setMousePoint(Point mousePoint);
    
    public Color getInactiveColor();
    public void setInactiveColor(Color inactiveColor);
    public Color getActiveColor();
    public void setActiveColor(Color activeColor);
    public Color getBorderColor();
    public void setBorderColor(Color borderColor);
    public Color getPressedColor();
    public void setPressedColor(Color borderColor);
    
    public void draw(Graphics2D g2d);
}
