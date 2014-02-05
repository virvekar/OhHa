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

    private Ruutu ruutu;
    private Component piirtoAlusta;

    public NappaimistonKuuntelija(Ruutu ruutu, Component piirtoAlusta) {
        this.ruutu = ruutu;
        this.piirtoAlusta=piirtoAlusta;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        
    }
    @Override
    public void keyReleased(KeyEvent e){
        
    }
    @Override
    public void keyTyped(KeyEvent ke){
        
    }
}
