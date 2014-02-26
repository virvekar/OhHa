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
import tienhoitopeli.Sovelluslogiikka.PeliKerranOhjaaja;

/**
 *
 * @author virvemaa
 */
public class SeuraavaTasoKuuntelija implements ActionListener {

    private PeliKerranOhjaaja peliKerranOhjaaja;
    private Piirtoalusta piirtoalusta;
    private JLabel ohjeKentta;
    private AloitusSyotteenKuuntelija aloitusSyotteenKuuntelija;
    private JTextArea ennusteKentta;
    private ReittiValmisKuuntelija reittiValmisKuuntelija;
    private EnnatysListanNayttaja ennatysListanNayttaja;
    private AurausAuto auto;
    private NappaimistonKuuntelija nappaimistonKuuntelija;

    public SeuraavaTasoKuuntelija(PeliKerranOhjaaja ohjaaja, Piirtoalusta piirtoalusta,
            JLabel ohjeKentta, AloitusSyotteenKuuntelija aloitusKuuntelija, JTextArea ennusteKentta,
            ReittiValmisKuuntelija reittiValmisKuuntelija, EnnatysListanNayttaja ennatysListanNayttaja,
            AurausAuto auto, NappaimistonKuuntelija nappaimistonKuuntelija) {
        this.peliKerranOhjaaja = ohjaaja;
        this.piirtoalusta = piirtoalusta;
        this.ohjeKentta = ohjeKentta;
        this.aloitusSyotteenKuuntelija = aloitusKuuntelija;
        this.ennusteKentta = ennusteKentta;
        this.reittiValmisKuuntelija = reittiValmisKuuntelija;
        this.ennatysListanNayttaja = ennatysListanNayttaja;
        this.auto = auto;
        this.nappaimistonKuuntelija = nappaimistonKuuntelija;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (this.peliKerranOhjaaja.getPelaajanNimi().isEmpty()) {
            this.ohjeKentta.setText("Anna nimesi ensin");
        }else if(this.peliKerranOhjaaja.getKentanNumero()==this.peliKerranOhjaaja.getKenttienMaara()){
            this.ohjeKentta.setText("Tama on viimeinen kentta.");
        } else {
            this.peliKerranOhjaaja.MeneSeuraavaanKenttaan();
            this.peliKerranOhjaaja.AlustaTaso();

            this.piirtoalusta.vaihdaKarttaPohja(this.peliKerranOhjaaja.getLumikerrosKoordinaateissa(),
                    this.peliKerranOhjaaja.getLumikerros().GetMerkittavastaLumestaIlmoitettuJoskus(),
                    this.peliKerranOhjaaja.getRivit(), this.peliKerranOhjaaja.getSarakkeet());
            this.ohjeKentta.setText("Milla sekunnilla auraus aloitetaan?");
            this.aloitusSyotteenKuuntelija.TyhjennaAloitusAika();
            this.aloitusSyotteenKuuntelija.TyhjennaAloitusRivi();
            this.aloitusSyotteenKuuntelija.TyhjennaAloitusSarake();
            this.aloitusSyotteenKuuntelija.vaihdaReitinLukija(this.peliKerranOhjaaja.getReitinLukija());
            this.ennusteKentta.setText(this.peliKerranOhjaaja.getEnnuste());
            this.reittiValmisKuuntelija.AlustaPainallukset();
            this.ennatysListanNayttaja.VaihdaEnnatysListanPitaja(this.peliKerranOhjaaja.getEnnatysListanPitaja());
            this.auto.MuutaPaikkaa(-10000, -10000);
            this.auto.MuutaKoko(400 / this.peliKerranOhjaaja.getRivit());
            this.nappaimistonKuuntelija.VaihdaReitinLukija(this.peliKerranOhjaaja.getReitinLukija());
            this.piirtoalusta.repaint();
        }
    }
}
