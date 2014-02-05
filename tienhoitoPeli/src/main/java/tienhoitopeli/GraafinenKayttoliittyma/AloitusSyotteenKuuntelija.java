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
import javax.swing.JTextField;
import tienhoitopeli.Sovelluslogiikka.ReitinLukija;

/**
 *
 * @author virvemaa
 */
public class AloitusSyotteenKuuntelija implements ActionListener {

    private JTextField syoteKentta;
    private ReitinLukija reitinLukija;
    private String aloitusRivi;
    private String aloitusSarake;
    private JLabel ohjeKentta;
    private AurausAuto auto;
    private Component piirtoalusta;

    public AloitusSyotteenKuuntelija(JTextField kentta, ReitinLukija annettuReitinLukija, JLabel ohjeKentta,AurausAuto auto,Component piirtoalusta) {
        this.syoteKentta = kentta;
        this.reitinLukija = annettuReitinLukija;
        this.ohjeKentta = ohjeKentta;
        this.piirtoalusta=piirtoalusta;
        this.auto=auto;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (aloitusRivi == null) {
            this.aloitusRivi = this.syoteKentta.getText();
            this.syoteKentta.setText("");
            this.ohjeKentta.setText("Anna aloitus sarake.");
        } else if (aloitusSarake == null) {
            this.aloitusSarake = this.syoteKentta.getText();
            boolean onkoVaarin = this.reitinLukija.KirjaaEnsimmainenPiste(aloitusRivi, aloitusSarake);
            if (!onkoVaarin) {
                this.syoteKentta.setText("");
                this.auto.MuutaPaikkaa((Integer.parseInt(aloitusSarake)-1)*auto.getYKoko(), (Integer.parseInt(aloitusRivi)-1)*auto.getYKoko());
                this.piirtoalusta.repaint();
            } else {
                this.aloitusRivi = null;
                this.aloitusSarake = null;
                this.ohjeKentta.setText("Virheellinen syote. Anna aloitusRivi.");
                this.syoteKentta.setText("");
            }

        } else {
            this.syoteKentta.setText("Aloitusruutu on jo valittu.");
        }
    }
}
