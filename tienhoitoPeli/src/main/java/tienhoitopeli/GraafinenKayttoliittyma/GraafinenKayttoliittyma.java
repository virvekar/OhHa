/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.GraafinenKayttoliittyma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import tienhoitopeli.Sovelluslogiikka.EnnatysListanPitaja;
import tienhoitopeli.Sovelluslogiikka.Kartta;
import tienhoitopeli.Sovelluslogiikka.Lumikerros;
import tienhoitopeli.Sovelluslogiikka.PeliKerranOhjaaja;
import tienhoitopeli.Sovelluslogiikka.ReitinLukija;
import tienhoitopeli.Sovelluslogiikka.Saa;
import tienhoitopeli.Sovelluslogiikka.Saaennuste;

/**
 * Luo graafisen kayttoliityman, jossa pelia voidaan pelata.
 *
 * @author virvemaa
 */
public class GraafinenKayttoliittyma implements Runnable {

    private JFrame frame;
    private PeliKerranOhjaaja peliKerranOhjaaja;
    private JLabel ohjeKentta;
    private JTextField riviKentta;
    private JButton reittiValmis;
    private Piirtoalusta piirtoalusta;
    private JTextArea alaTekstiLaatikko;
    private JLabel tulosKentta;

    public GraafinenKayttoliittyma(PeliKerranOhjaaja peliKerranOhjaaja) {

        this.peliKerranOhjaaja = peliKerranOhjaaja;
        this.peliKerranOhjaaja.AlustaTaso();
    }

    @Override
    public void run() {
        frame = new JFrame("Tienhoitopeli");
        frame.setPreferredSize(new Dimension(1000, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

    }

    private void luoKomponentit(Container container) {

        Font font = new Font("Arial", Font.BOLD, 16);

        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        ohjeKentta = new JLabel("Kerro nimesi");
        ohjeKentta.setFont(font);
        ohjeKentta.setBackground(Color.LIGHT_GRAY);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 102; //kentan korkeus
        c.ipadx = 300; //kentan leveys
        c.gridwidth = 1; //montako cellia levea
        c.gridx = 0; //monesko cell vasemmalta
        c.gridy = 0; //monesko cell oikealta
        c.weightx = 0.5;
        container.add(ohjeKentta, c);

        JPanel ylaKentat = new JPanel(new GridLayout(1, 2));

        riviKentta = new JTextField();
        ylaKentat.add(riviKentta);

        reittiValmis = new JButton("Reitti valmis");

        ylaKentat.add(reittiValmis);
        reittiValmis.setBackground(Color.YELLOW);
        c.ipady = 100;
        //  c.ipadx=100;
        c.gridy = 0;
        c.gridx = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.weightx = 0.5;
        container.add(ylaKentat, c);

        AurausAuto auto = new AurausAuto(-1000, -1000, 400 / this.peliKerranOhjaaja.getRivit());
        this.piirtoalusta = new Piirtoalusta(this.peliKerranOhjaaja.getLumikerrosKoordinaateissa(),
                this.peliKerranOhjaaja.getRivit(), this.peliKerranOhjaaja.getSarakkeet(), auto);
        c.ipady = 400;
        c.ipadx = 1000;
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 3;
        container.add(piirtoalusta, c);

        AloitusSyotteenKuuntelija aloitusKuuntelija = new AloitusSyotteenKuuntelija(riviKentta, this.peliKerranOhjaaja.getReitinLukija(), ohjeKentta, auto, piirtoalusta, peliKerranOhjaaja);
        riviKentta.addActionListener(aloitusKuuntelija);

        NappaimistonKuuntelija nappaimistonKuuntelija=new NappaimistonKuuntelija(auto, piirtoalusta, this.peliKerranOhjaaja.getReitinLukija());
        riviKentta.addKeyListener(nappaimistonKuuntelija);

        alaTekstiLaatikko = new JTextArea(this.peliKerranOhjaaja.getEnnuste() + "\n Aurausajoneuvo liikkuu yhden ruudun yhdessa sekunnissa");
        c.ipady = 100;
        c.ipadx = 400;
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 2;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        alaTekstiLaatikko.setBackground(Color.WHITE);
        alaTekstiLaatikko.setFont(font);
        container.add(alaTekstiLaatikko, c);

        JPanel alaNapit = new JPanel(new GridLayout(1, 3));
        JButton naytaEnnatykset = new JButton("Ennatyslista");
        JButton yritaUudestaan = new JButton("Yrita uudelleen");
        JButton seuraavaTaso = new JButton("Seuraava taso");

        EnnatysListanNayttaja ennatysListanNayttaja = new EnnatysListanNayttaja(this.peliKerranOhjaaja.getEnnatysListanPitaja());
        EnnatysKuuntelija ennatysKuuntelija = new EnnatysKuuntelija(ennatysListanNayttaja);
        naytaEnnatykset.addActionListener(ennatysKuuntelija);

        

        alaNapit.add(naytaEnnatykset);
        alaNapit.add(yritaUudestaan);
        alaNapit.add(seuraavaTaso);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 100; //kentan korkeus
        c.ipadx = 400; //kentan leveys
        c.gridwidth = 1; //montako cellia levea
        c.gridx = 2; //monesko cell vasemmalta
        c.gridy = 2; //monesko cell oikealta
        c.weightx = 0.5;
        container.add(alaNapit, c);

        ReittiValmisKuuntelija nappiKuuntelija = new ReittiValmisKuuntelija( alaTekstiLaatikko, peliKerranOhjaaja);
        reittiValmis.addActionListener(nappiKuuntelija);

        YritaUudelleenKuuntelija yritaUudelleenKuuntelija = new YritaUudelleenKuuntelija(this.peliKerranOhjaaja, auto, aloitusKuuntelija, ohjeKentta, this.piirtoalusta, this.alaTekstiLaatikko,nappiKuuntelija);
        yritaUudestaan.addActionListener(yritaUudelleenKuuntelija);
        
        SeuraavaTasoKuuntelija seuraavaTasoKuuntelija = new SeuraavaTasoKuuntelija(this.peliKerranOhjaaja,
                this.piirtoalusta, this.ohjeKentta, aloitusKuuntelija, this.alaTekstiLaatikko, 
                nappiKuuntelija, ennatysListanNayttaja,auto, nappaimistonKuuntelija);
        
        seuraavaTaso.addActionListener(seuraavaTasoKuuntelija);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void AsetaEnnuste(String ennuste) {
        this.alaTekstiLaatikko.setText(ennuste);
    }

}
