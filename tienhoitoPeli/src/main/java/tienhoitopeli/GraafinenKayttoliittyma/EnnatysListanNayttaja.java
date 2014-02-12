/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.GraafinenKayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import tienhoitopeli.Sovelluslogiikka.EnnatysListanPitaja;

/**
 *
 * @author virvemaa
 */
public class EnnatysListanNayttaja implements Runnable{
    private EnnatysListanPitaja pitaja;
    private JFrame frame;
    
    public EnnatysListanNayttaja(EnnatysListanPitaja pitaja){
        this.pitaja=pitaja;
    }
    
    @Override
    public void run(){
        frame=new JFrame("EnnatysLista");
        frame.setPreferredSize(new Dimension(500,500));
        
      //  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    public void luoKomponentit(Container container){
        
        JTextArea teksti=new JTextArea(this.pitaja.getEnnatysListaTekstina());
        container.add(teksti);
    }
}
