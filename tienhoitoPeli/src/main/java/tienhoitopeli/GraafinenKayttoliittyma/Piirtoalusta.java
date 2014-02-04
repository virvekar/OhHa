/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.GraafinenKayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author virvemaa
 */
public class Piirtoalusta extends JPanel{
    
    public Piirtoalusta(){
        super.setBackground(Color.WHITE);
    }
    
    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        
        Ruutu ruutu=new Ruutu(0,0,Color.BLACK);
        ruutu.Piirra(graphics);
    }
}
