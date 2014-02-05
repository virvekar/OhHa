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
    private int koko;
    
    public Ruutu(int x, int y, int koko,Color color){
        this.x=x;
        this.y=y;
        this.color=color;
        this.koko=koko;
    }
    
    public void Siirry(int xSiirtyma, int ySiirtyma){
        this.x+=xSiirtyma;
        this.y+=ySiirtyma;
    }
    
    public void Piirra(Graphics graphics){
        graphics.setColor(this.color);
        graphics.fillRect(this.x,this.y,koko,koko);
    }
}
