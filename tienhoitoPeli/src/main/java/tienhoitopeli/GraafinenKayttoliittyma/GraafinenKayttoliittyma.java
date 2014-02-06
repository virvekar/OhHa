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
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import tienhoitopeli.Sovelluslogiikka.Lumikerros;
import tienhoitopeli.Sovelluslogiikka.ReitinLukija;
import tienhoitopeli.Sovelluslogiikka.Saa;

/**
 *Luo graafisen kayttoliityman, jossa pelia voidaan pelata.
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
    private JLabel ohjeKentta;
    private JTextField riviKentta;
    private JButton reittiValmis;
    private Piirtoalusta piirtoalusta;
    private JTextArea ennusteTeksti;
    private JLabel tulosKentta;
    
    
    public GraafinenKayttoliittyma(HashMap kerros, int rivit, int sarakkeet,String ennuste,ReitinLukija reitinLukija, Lumikerros lumikerros, Saa saa){
        this.lumikerrosKoordinaateissa=kerros;
        this.rivit=rivit;
        this.sarakkeet=sarakkeet;
        this.ennuste=ennuste;
        this.reitinLukija=reitinLukija;
        this.saa=saa;
        this.lumikerros=lumikerros;
    }
    
    
    @Override
    public void run(){
        frame=new JFrame("Tienhoitopeli");
        frame.setPreferredSize(new Dimension(1000,600));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
        
    }
    
    private void luoKomponentit(Container container){
        
        Font font =new Font("Arial",Font.BOLD,16);
        
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        ohjeKentta=new JLabel("Milla sekunnilla auraus aloitetaan?");
        ohjeKentta.setFont(font);
        ohjeKentta.setBackground(Color.LIGHT_GRAY);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.ipady=102; //kentan korkeus
        c.ipadx=300; //kentan leveys
        c.gridwidth=1; //montako cellia levea
        c.gridx=0; //monesko cell vasemmalta
        c.gridy=0; //monesko cell oikealta
        c.weightx=0.5;
        container.add(ohjeKentta,c);
        
        riviKentta=new JTextField();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.ipady=102; //kentan korkeus
        c.ipadx=50; //kentan leveys
        c.gridwidth=1; //montako cellia levea
        c.gridx=1; //monesko cell vasemmalta
        c.gridy=0; //monesko cell oikealta
        c.weightx=0.5;
        
        
        container.add(riviKentta,c);
        
        
        reittiValmis=new JButton("Reitti valmis");
        c.ipady=100;
      //  c.ipadx=100;
        c.gridy=0;
        c.gridx=2;
        c.gridwidth=1;
        c.fill = GridBagConstraints.HORIZONTAL;
        reittiValmis.setBackground(Color.YELLOW);
       c.weightx=0.5;
        container.add(reittiValmis,c);
        
        
        
        AurausAuto auto=new AurausAuto(-1000,-1000,400/this.rivit);
        this.piirtoalusta=new Piirtoalusta(this.lumikerrosKoordinaateissa,this.rivit,this.sarakkeet,auto);
        c.ipady=400;
        c.ipadx=1000;
        c.gridy=1;
        c.gridx=0;
        c.gridwidth=3;
        container.add(piirtoalusta,c);
        

        AloitusSyotteenKuuntelija aloitusKuuntelija=new AloitusSyotteenKuuntelija(riviKentta,this.reitinLukija,ohjeKentta,auto, piirtoalusta);
        riviKentta.addActionListener(aloitusKuuntelija);
        
        riviKentta.addKeyListener(new NappaimistonKuuntelija(auto,piirtoalusta,reitinLukija));
        
        ennusteTeksti=new JTextArea(this.ennuste+"\n Aurausajoneuvo liikkuu yhden ruudun yhdessa sekunnissa");
        c.ipady=100;
        c.ipadx=700;
        c.gridy=2;
        c.gridx=0;
        c.gridwidth=2;
        c.fill = GridBagConstraints.HORIZONTAL;
        ennusteTeksti.setBackground(Color.WHITE);
        ennusteTeksti.setFont(font);
        container.add(ennusteTeksti,c);
        
        tulosKentta=new JLabel("kuluja tuli x euroa");
        tulosKentta.setFont(font);
        tulosKentta.setBackground(Color.GREEN);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.ipady=100; //kentan korkeus
        c.ipadx=200; //kentan leveys
        c.gridwidth=1; //montako cellia levea
        c.gridx=2; //monesko cell vasemmalta
        c.gridy=2; //monesko cell oikealta
        container.add(tulosKentta,c);  
        
        ReittiValmisKuuntelija nappiKuuntelija=new ReittiValmisKuuntelija(lumikerros,reitinLukija,saa,tulosKentta);
        reittiValmis.addActionListener(nappiKuuntelija);
        
    }
    public JFrame getFrame(){
        return frame;
    }
    public void AsetaEnnuste(String ennuste){
        this.ennusteTeksti.setText(ennuste);
    }
    
    
}
