package tienhoitopeli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import tienhoitopeli.Sovelluslogiikka.Auraaja;
import tienhoitopeli.Sovelluslogiikka.ReitinLukija;
import tienhoitopeli.Sovelluslogiikka.Kartta;
import tienhoitopeli.Sovelluslogiikka.Lumikerros;
import tienhoitopeli.Sovelluslogiikka.Saa;
import tienhoitopeli.Sovelluslogiikka.Saaennuste;
import tienhoitopeli.Sovelluslogiikka.TienhoidonSuorittaja;
 import javax.swing.SwingUtilities;
import tienhoitopeli.GraafinenKayttoliittyma.GraafinenKayttoliittyma;
import tienhoitopeli.Sovelluslogiikka.EnnatysListanPitaja;
import tienhoitopeli.Sovelluslogiikka.PeliKerranOhjaaja;

/**
 * Hello world!
 *
 */
public class Kayttoliittyma {

    public static void main(String[] args) {
        
        PeliKerranOhjaaja ohjaaja=new PeliKerranOhjaaja();

        GraafinenKayttoliittyma graafinenKayttoliittyma=new GraafinenKayttoliittyma(ohjaaja);
        
        
        Saaennuste uusiEnnuste = new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste2.txt");
        uusiEnnuste.LueSaaennuste();
        Saa uusiSaa = new Saa(uusiEnnuste);
        for(int i=1; i<10; i++){
            uusiSaa.ArvoSateenMaara(5);
        }
        
        
 
      //  SwingUtilities.invokeLater(graafinenKayttoliittyma);

       
       
    }
}
