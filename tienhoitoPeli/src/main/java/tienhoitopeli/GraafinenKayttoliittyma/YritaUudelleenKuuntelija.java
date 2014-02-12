/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.GraafinenKayttoliittyma;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import tienhoitopeli.Sovelluslogiikka.Lumikerros;
import tienhoitopeli.Sovelluslogiikka.ReitinLukija;

/**
 *
 * @author virvemaa
 */
public class YritaUudelleenKuuntelija implements ActionListener{
    private Lumikerros lumikerros;
    private ReitinLukija reitinLukija;
    private AurausAuto auto;
    private AloitusSyotteenKuuntelija aloitusSyotteenKuuntelija;
    private JLabel ohjeKentta;
    private Component piirtoAlusta;
    
    public YritaUudelleenKuuntelija(Lumikerros kerros, ReitinLukija lukija,
            AurausAuto auto, AloitusSyotteenKuuntelija aloitusKuuntelija, 
            JLabel ohjeKentta, Component piirtoAlusta){
        this.lumikerros=kerros;
        this.reitinLukija=lukija;
        this.aloitusSyotteenKuuntelija=aloitusKuuntelija;
        this.auto=auto;
        this.ohjeKentta=ohjeKentta;
        this.piirtoAlusta=piirtoAlusta;
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        this.lumikerros.AlustaLumikerros();
        this.reitinLukija.TyhjennaReitti();
        this.auto.MuutaPaikkaa(-10000, -10000);
        this.aloitusSyotteenKuuntelija.TyhjennaAloitusAika();
        this.aloitusSyotteenKuuntelija.TyhjennaAloitusRivi();
        this.aloitusSyotteenKuuntelija.TyhjennaAloitusSarake();
        this.piirtoAlusta.repaint();
        this.ohjeKentta.setText("Milla sekunnilla auraus aloitetaan?");
    }
}
