/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.GraafinenKayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author virvemaa
 */
public class Piirtoalusta extends JPanel{
    private HashMap lumikerrosKoordinaateissa;
    private int rivit;
    private int sarakkeet;
    
    public Piirtoalusta(HashMap kerros, int rivit, int sarakkeet){
        this.lumikerrosKoordinaateissa=kerros;
        this.rivit=rivit;
        this.sarakkeet=sarakkeet;
        super.setBackground(Color.WHITE);
    }
    
    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Ruudukko ruudukko=new Ruudukko(this.lumikerrosKoordinaateissa,this.rivit,this.sarakkeet);
        
        ruudukko.Piirra(graphics);
    }
}
