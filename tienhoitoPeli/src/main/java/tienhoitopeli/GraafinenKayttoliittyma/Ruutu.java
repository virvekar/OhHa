/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.GraafinenKayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author virvemaa
 */
public class Ruutu {
    private int x;
    private int y;
    private Color color;
    
    public Ruutu(int x, int y, Color color){
        this.x=x;
        this.y=y;
        this.color=color;
    }
    
    public void Piirra(Graphics graphics){
        graphics.setColor(this.color);
        graphics.fillRect(this.x,this.y,10,10);
    }
}
