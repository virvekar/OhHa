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

/**
 * Hello world!
 *
 */
public class Kayttoliittyma {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);

        Kartta koeKartta = new Kartta("src/main/java/tienhoitopeli/kartat/kartta1.txt");
        koeKartta.LueKartta();
        koeKartta.TulostaKartta();
        koeKartta.TulostaSelite();

        Saaennuste ennuste = new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste2.txt");
        ennuste.LueSaaennuste();
        ennuste.TulostaSaaennuste();
        System.out.println("Aurausajoneuvo auraa yhden ruudun yhdessa sekunnissa");
        
        Saa saa=new Saa(ennuste);

        Lumikerros lumikerros = new Lumikerros(koeKartta);
        lumikerros.AlustaLumikerros();

        ReitinLukija reitinLukija = new ReitinLukija(koeKartta, lumikerros);

        boolean ekaPisteEiHyvaksytty = true;
        while (ekaPisteEiHyvaksytty) {
            System.out.println("Milta vaakarivilta aloitetaan (ylin=1)?");
            String aloitusRivi = lukija.nextLine();
            System.out.println("Milta pystyRivilta aloitetaan (vasemmalla=1)?");
            String aloitusSarake = lukija.nextLine();
            
           ekaPisteEiHyvaksytty= reitinLukija.KirjaaEnsimmainenPiste(aloitusRivi, aloitusSarake);
           if(ekaPisteEiHyvaksytty){
               System.out.println("Piste ei kelpaa. Anna uusiPiste");
           }
        }

        System.out.println("Liiku painamalla seuraavia nappaimia: i=ylos, k=alas, j=vasemmalle, l=oikealle");
        System.out.println("Lopeta liikkuminen painamalla p");
        boolean lopetuskomentoAnnettu = false;

        while (!lopetuskomentoAnnettu) {
            String komento = lukija.nextLine();
            lopetuskomentoAnnettu=reitinLukija.KirjaaReittiPisteJosKomentoKelpaa(komento);
        }
        
        Auraaja auraaja=new Auraaja(lumikerros,reitinLukija);
        TienhoidonSuorittaja tienhoidonSuorittaja=new TienhoidonSuorittaja(auraaja,saa,lumikerros);
        tienhoidonSuorittaja.SuoritaTienhoito();
        System.out.println("Kuluja tuli "+tienhoidonSuorittaja.laskeKulut()+" euroa");
        lumikerros.TulostaLumikerros();
    }
}
