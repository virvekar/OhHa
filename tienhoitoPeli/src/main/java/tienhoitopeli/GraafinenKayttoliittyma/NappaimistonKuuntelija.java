/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.GraafinenKayttoliittyma;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author virvemaa
 */
public class NappaimistonKuuntelija implements KeyListener{

    private AurausAuto auto;
    private Component piirtoAlusta;

    public NappaimistonKuuntelija(AurausAuto aurausAuto, Component piirtoAlusta) {
        this.auto = aurausAuto;
        this.piirtoAlusta=piirtoAlusta;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        System.out.println("alas");
        if(e.getKeyCode()== KeyEvent.VK_LEFT){
            this.auto.Siirry(-this.auto.getYKoko(), 0);
        } else if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            this.auto.Siirry(this.auto.getYKoko(),0);
        } else if(e.getKeyCode()==KeyEvent.VK_UP){
            this.auto.Siirry(0,-this.auto.getYKoko());
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            this.auto.Siirry(0,this.auto.getYKoko());
            System.out.println("alas");
        }
        this.piirtoAlusta.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e){
        
    }
    @Override
    public void keyTyped(KeyEvent ke){
        
    }
}
