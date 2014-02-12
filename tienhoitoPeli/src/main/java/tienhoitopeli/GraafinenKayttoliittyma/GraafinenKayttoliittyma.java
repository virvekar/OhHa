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
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import tienhoitopeli.Sovelluslogiikka.EnnatysListanPitaja;
import tienhoitopeli.Sovelluslogiikka.Lumikerros;
import tienhoitopeli.Sovelluslogiikka.PeliKerranOhjaaja;
import tienhoitopeli.Sovelluslogiikka.ReitinLukija;
import tienhoitopeli.Sovelluslogiikka.Saa;

/**
 * Luo graafisen kayttoliityman, jossa pelia voidaan pelata.
 *
 * @author virvemaa
 */
public class GraafinenKayttoliittyma implements Runnable {

    private JFrame frame;
    private HashMap lumikerrosKoordinaateissa;
    private int rivit;
    private int sarakkeet;
    private String ennuste;
    private ReitinLukija reitinLukija;
    private Lumikerros lumikerros;
    private Saa saa;
    private EnnatysListanPitaja ennatysListanPitaja;
    private PeliKerranOhjaaja peliKerranOhjaaja;
    private JLabel ohjeKentta;
    private JTextField riviKentta;
    private JButton reittiValmis;
    private Piirtoalusta piirtoalusta;
    private JTextArea alaTekstiLaatikko;
    private JLabel tulosKentta;

    public GraafinenKayttoliittyma(HashMap kerros, int rivit, int sarakkeet,
            String ennuste, ReitinLukija reitinLukija, Lumikerros lumikerros, 
            Saa saa, EnnatysListanPitaja pitaja, PeliKerranOhjaaja peliKerranOhjaaja) {
        this.lumikerrosKoordinaateissa = kerros;
        this.rivit = rivit;
        this.sarakkeet = sarakkeet;
        this.ennuste = ennuste;
        this.reitinLukija = reitinLukija;
        this.saa = saa;
        this.lumikerros = lumikerros;
        this.ennatysListanPitaja = pitaja;
        this.peliKerranOhjaaja=peliKerranOhjaaja;
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

        AurausAuto auto = new AurausAuto(-1000, -1000, 400 / this.rivit);
        this.piirtoalusta = new Piirtoalusta(this.lumikerrosKoordinaateissa, this.rivit, this.sarakkeet, auto);
        c.ipady = 400;
        c.ipadx = 1000;
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 3;
        container.add(piirtoalusta, c);

        AloitusSyotteenKuuntelija aloitusKuuntelija = new AloitusSyotteenKuuntelija(riviKentta, this.reitinLukija, ohjeKentta, auto, piirtoalusta, peliKerranOhjaaja);
        riviKentta.addActionListener(aloitusKuuntelija);

        riviKentta.addKeyListener(new NappaimistonKuuntelija(auto, piirtoalusta, reitinLukija));

        alaTekstiLaatikko = new JTextArea(this.ennuste + "\n Aurausajoneuvo liikkuu yhden ruudun yhdessa sekunnissa");
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
        JButton yritaUudestaan=new JButton("Yrita uudelleen");
        JButton seuraavaTaso = new JButton("Seuraava taso");

        EnnatysListanNayttaja ennatysListanNayttaja = new EnnatysListanNayttaja(this.ennatysListanPitaja);
        EnnatysKuuntelija ennatysKuuntelija = new EnnatysKuuntelija(ennatysListanNayttaja);
        naytaEnnatykset.addActionListener(ennatysKuuntelija);
        
        YritaUudelleenKuuntelija yritaUudelleenKuuntelija=new YritaUudelleenKuuntelija(lumikerros,reitinLukija,auto,aloitusKuuntelija,ohjeKentta,this.piirtoalusta);
        yritaUudestaan.addActionListener(yritaUudelleenKuuntelija);

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

        ReittiValmisKuuntelija nappiKuuntelija = new ReittiValmisKuuntelija(lumikerros, reitinLukija, saa, alaTekstiLaatikko,ennatysListanPitaja,peliKerranOhjaaja);
        reittiValmis.addActionListener(nappiKuuntelija);

    }

    public JFrame getFrame() {
        return frame;
    }

    public void AsetaEnnuste(String ennuste) {
        this.alaTekstiLaatikko.setText(ennuste);
    }

}
