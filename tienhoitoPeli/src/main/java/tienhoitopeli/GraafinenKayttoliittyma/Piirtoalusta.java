/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.GraafinenKayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *JPanel johon piirretaan kartan mukainen ruudukko ja aurausauto
 * @author virvemaa
 */
public class Piirtoalusta extends JPanel{
    private HashMap lumikerrosKoordinaateissa;
    private AurausAuto auto;
    private int rivit;
    private int sarakkeet;
    private Ruudukko ruudukko;
    
    public Piirtoalusta(HashMap kerros, int rivit, int sarakkeet,AurausAuto auto){
        this.lumikerrosKoordinaateissa=kerros;
        this.rivit=rivit;
        this.sarakkeet=sarakkeet;
        ruudukko=new Ruudukko(this.lumikerrosKoordinaateissa,this.rivit,this.sarakkeet);
        this.auto=auto;
        super.setBackground(Color.WHITE);
    }
    
    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        ruudukko.Piirra(graphics);
        auto.Piirra(graphics);
    }
}
