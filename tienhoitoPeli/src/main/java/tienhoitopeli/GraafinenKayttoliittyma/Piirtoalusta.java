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

    private AurausAuto auto;

    private Ruudukko ruudukko;
    
    public Piirtoalusta(HashMap kerros, HashMap merkittavastiLunta,int rivit, int sarakkeet,AurausAuto auto){
 
        ruudukko=new Ruudukko(kerros,merkittavastiLunta,rivit,sarakkeet);
        this.auto=auto;
        super.setBackground(Color.WHITE);
    }
    
    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        ruudukko.Piirra(graphics);
        auto.Piirra(graphics);
    }
    
    public void vaihdaKarttaPohja(HashMap kerros, HashMap merkittavastiLunta,int rivit, int sarakkeet){
        this.ruudukko=new Ruudukko(kerros,merkittavastiLunta,rivit,sarakkeet);
    }
}
