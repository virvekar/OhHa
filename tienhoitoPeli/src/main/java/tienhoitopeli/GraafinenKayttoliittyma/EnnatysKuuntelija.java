/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.GraafinenKayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author virvemaa
 */
public class EnnatysKuuntelija implements ActionListener{
    private EnnatysListanNayttaja nayttaja;
    
    public EnnatysKuuntelija(EnnatysListanNayttaja ennatysListanNayttaja){
        this.nayttaja=ennatysListanNayttaja;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        SwingUtilities.invokeLater(nayttaja);
    }
}
