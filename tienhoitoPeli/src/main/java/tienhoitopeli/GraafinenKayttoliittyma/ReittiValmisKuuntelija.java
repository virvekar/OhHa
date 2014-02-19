/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.GraafinenKayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import tienhoitopeli.Sovelluslogiikka.Auraaja;
import tienhoitopeli.Sovelluslogiikka.EnnatysListanPitaja;
import tienhoitopeli.Sovelluslogiikka.Lumikerros;
import tienhoitopeli.Sovelluslogiikka.PeliKerranOhjaaja;
import tienhoitopeli.Sovelluslogiikka.ReitinLukija;
import tienhoitopeli.Sovelluslogiikka.Saa;
import tienhoitopeli.Sovelluslogiikka.TienhoidonSuorittaja;

/**
 *Kuuntelija joka on liitetty Reitti valmis nappiin
 * Nappia painettaessa suoritetaan tienhoito ja lasketaan kulut.
 * @author virvemaa
 */
public class ReittiValmisKuuntelija implements ActionListener {

    private PeliKerranOhjaaja peliKerranOhjaaja;
    private JTextArea tuloskentta;
    private int painallukset;

    public ReittiValmisKuuntelija( JTextArea tuloskentta,PeliKerranOhjaaja ohjaaja) {
        this.peliKerranOhjaaja=ohjaaja;
        
        
        this.tuloskentta = tuloskentta;
        this.painallukset=0;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.peliKerranOhjaaja.getReitinLukija().GetReitti().size() > 0 && this.painallukset==0) {
            Auraaja auraaja = new Auraaja(this.peliKerranOhjaaja.getLumikerros(), this.peliKerranOhjaaja.getReitinLukija());
            TienhoidonSuorittaja tienhoidonSuorittaja = new TienhoidonSuorittaja(auraaja, 
                this.peliKerranOhjaaja.getSaa(), this.peliKerranOhjaaja.getLumikerros(),this.peliKerranOhjaaja.getReitinLukija());
            tienhoidonSuorittaja.SuoritaTienhoito();
            int kulut=tienhoidonSuorittaja.laskeKulutLumenMaaranMukaan();
            this.tuloskentta.setText("Kuluja tuli " + kulut + " euroa");
            String pelaaja=this.peliKerranOhjaaja.getPelaajanNimi();
            this.peliKerranOhjaaja.getEnnatysListanPitaja().LisaaEnnatys(pelaaja, ""+kulut);
            this.painallukset++;
            
            

        }
    }
    
    public void AlustaPainallukset(){
        this.painallukset=0;
    }
    
   

}
