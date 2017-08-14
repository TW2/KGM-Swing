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
import java.awt.Shape;
import kgm.midi.Midi;

/**
 *
 * @author Naruto
 */
public abstract class ANote implements INote {
    
    protected Keys key = Keys.Do;
    protected Octave octave = Octave.Octave04;
    protected int velocity = 100;
    
    protected double width = 30;
    protected double height = 70;    
    protected double x = 0;
    protected double y = 0;
    
    protected double parentWidth = 300;
    protected double parentHeight = 200;
    
    protected Point mousePoint = new Point(-1, -1);
    protected boolean mouseIn = false;
    protected boolean mousePressed = false;
    
    protected Color inactiveColor = Color.white;
    protected Color activeColor = Color.lightGray;
    protected Color borderColor = Color.black;
    protected Color pressedColor = Color.red;
    
    protected double whiteKeySizeX;
    protected double blackKeySizeX;
    
    protected double whiteKeySizeY;
    protected double blackKeySizeY;
    
    protected Shape shapeOfNote;
    
    protected Direction direction = Direction.South;

    public ANote() {
        
    }
    
    public void configure(){
        configure(direction);
    }
    
    public void configure(Direction direction){
        this.direction = direction;
        
        switch(direction){
            case South:
                whiteKeySizeX = parentWidth/7;
                blackKeySizeX = whiteKeySizeX *2/3;        
                whiteKeySizeY = parentHeight-parentHeight/6;
                blackKeySizeY = whiteKeySizeY *2/3;

                width = whiteKeySizeX;
                height = whiteKeySizeY;

                x = key.position * whiteKeySizeX;
                y = 0;
                break;                
            case North:
                whiteKeySizeX = parentWidth/7;
                blackKeySizeX = whiteKeySizeX *2/3;        
                whiteKeySizeY = parentHeight-parentHeight/6;
                blackKeySizeY = whiteKeySizeY *2/3;

                width = whiteKeySizeX;
                height = whiteKeySizeY + parentHeight/6;

                x = key.position * whiteKeySizeX;
                y = parentHeight/6;
                break;
            case East:
                whiteKeySizeY = parentHeight/7;
                blackKeySizeY = whiteKeySizeY *2/3;
                whiteKeySizeX = parentWidth-parentWidth/6;
                blackKeySizeX = whiteKeySizeX *2/3;                

                width = whiteKeySizeX;
                height = whiteKeySizeY;

                x = parentWidth/6;
                y = key.position * whiteKeySizeY;
                break;
            case West:
                whiteKeySizeY = parentHeight/7;
                blackKeySizeY = whiteKeySizeY *2/3;
                whiteKeySizeX = parentWidth-parentWidth/6;
                blackKeySizeX = whiteKeySizeX *2/3;                

                width = whiteKeySizeX;
                height = whiteKeySizeY;

                x = 0;
                y = key.position * whiteKeySizeY;
                break;
        }
        
    }

    @Override
    public void draw(Graphics2D g2d) {
        if(shapeOfNote != null){
            if(mouseIn == true){
                if(key.isWhite()){
                    g2d.setColor(activeColor);
                }else{
                    g2d.setColor(Color.orange);
                }
                g2d.fill(shapeOfNote);
            }else if(mouseIn == false){
                if(key.isWhite()){
                    g2d.setColor(inactiveColor);
                }else{
                    g2d.setColor(Color.black);
                }
                g2d.fill(shapeOfNote);
            }                
            if(mousePressed == true){
                if(key.isWhite()){
                    g2d.setColor(pressedColor);
                }else{
                    g2d.setColor(Color.blue);
                }
                g2d.fill(shapeOfNote);
            }
            if(key.isWhite()){
                g2d.setColor(borderColor);
            }else{
                g2d.setColor(borderColor);
            }            
            g2d.draw(shapeOfNote);
        }
    }
    
    
    
    public void setMouseEntered(){
        mouseIn = true;
    }
    
    public void setMouseExited(){
        mouseIn = false;
    }
    
    public void setMousePressed(){
        mousePressed = true;
        Midi.noteOn(key, octave, velocity);
    }
    
    public void setMouseReleased(){
        mousePressed = false;
        Midi.noteOff(key, octave);
    }
    
    public Shape getShape(){
        return shapeOfNote;
    }

    @Override
    public void setKey(Keys key) {
        this.key = key;
    }

    @Override
    public Keys getKey() {
        return key;
    }

    @Override
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public int getVelocity() {
        return velocity;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setParentWidth(double parentWidth) {
        this.parentWidth = parentWidth;
    }

    @Override
    public double getParentWidth() {
        return parentWidth;
    }

    @Override
    public void setParentHeight(double parentHeight) {
        this.parentHeight = parentHeight;
    }

    @Override
    public double getParentHeight() {
        return parentHeight;
    }

    @Override
    public void setMousePoint(Point mousePoint) {
        this.mousePoint = mousePoint;
    }
    
    @Override
    public void setActiveColor(Color activeColor) {
        this.activeColor = activeColor;
    }

    @Override
    public Color getActiveColor() {
        return activeColor;
    }

    @Override
    public void setInactiveColor(Color inactiveColor) {
        this.inactiveColor = inactiveColor;
    }

    @Override
    public Color getInactiveColor() {
        return inactiveColor;
    }

    @Override
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public Color getBorderColor() {
        return borderColor;
    }
    
    @Override
    public void setPressedColor(Color pressedColor) {
        this.pressedColor = pressedColor;
    }

    @Override
    public Color getPressedColor() {
        return pressedColor;
    }

    public Direction getDirection() {
        return direction;
    }
    
}
