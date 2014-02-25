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
import javax.swing.JTextArea;
import tienhoitopeli.Sovelluslogiikka.Lumikerros;
import tienhoitopeli.Sovelluslogiikka.PeliKerranOhjaaja;
import tienhoitopeli.Sovelluslogiikka.ReitinLukija;

/**
 *
 * @author virvemaa
 */
public class YritaUudelleenKuuntelija implements ActionListener {

    private PeliKerranOhjaaja peliKerranOhjaaja;
    private AurausAuto auto;
    private AloitusSyotteenKuuntelija aloitusSyotteenKuuntelija;
    private JLabel ohjeKentta;
    private Component piirtoAlusta;
    private JTextArea ennusteKentta;
    private ReittiValmisKuuntelija reittiValmisKuuntelija;

    public YritaUudelleenKuuntelija(PeliKerranOhjaaja peliKerranOhjaaja,
            AurausAuto auto, AloitusSyotteenKuuntelija aloitusKuuntelija,
            JLabel ohjeKentta, Component piirtoAlusta, JTextArea ennusteKentta,
            ReittiValmisKuuntelija reittiValmisKuuntelija) {
        this.peliKerranOhjaaja = peliKerranOhjaaja;
        this.aloitusSyotteenKuuntelija = aloitusKuuntelija;
        this.auto = auto;
        this.ohjeKentta = ohjeKentta;
        this.piirtoAlusta = piirtoAlusta;
        this.ennusteKentta = ennusteKentta;
        this.reittiValmisKuuntelija = reittiValmisKuuntelija;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!this.peliKerranOhjaaja.getPelaajanNimi().isEmpty()) {
            this.peliKerranOhjaaja.getLumikerros().AlustaLumikerros();
            this.peliKerranOhjaaja.getReitinLukija().TyhjennaReitti();
            this.auto.MuutaPaikkaa(-10000, -10000);
            this.aloitusSyotteenKuuntelija.TyhjennaAloitusAika();
            this.aloitusSyotteenKuuntelija.TyhjennaAloitusRivi();
            this.aloitusSyotteenKuuntelija.TyhjennaAloitusSarake();
            this.piirtoAlusta.repaint();
            this.ohjeKentta.setText("Milla sekunnilla auraus aloitetaan?");
            this.ennusteKentta.setText(this.peliKerranOhjaaja.getEnnuste());
            this.reittiValmisKuuntelija.AlustaPainallukset();
        }

    }
}
