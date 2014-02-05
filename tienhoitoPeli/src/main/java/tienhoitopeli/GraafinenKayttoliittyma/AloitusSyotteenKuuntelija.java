/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.GraafinenKayttoliittyma;

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

    public AloitusSyotteenKuuntelija(JTextField kentta, ReitinLukija annettuReitinLukija, JLabel ohjeKentta) {
        this.syoteKentta = kentta;
        this.reitinLukija = annettuReitinLukija;
        this.ohjeKentta = ohjeKentta;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (aloitusRivi == null) {
            this.aloitusRivi = this.syoteKentta.getText();
            this.syoteKentta.setText("");
            this.ohjeKentta.setText("Anna aloitus sarake.");
        } else if (aloitusSarake == null) {
            this.aloitusSarake = this.syoteKentta.getText();
            System.out.println(aloitusRivi + aloitusSarake);
            boolean onnistuiko = this.reitinLukija.KirjaaEnsimmainenPiste(aloitusRivi, aloitusSarake);
            if (onnistuiko) {
                this.syoteKentta.setText("");
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