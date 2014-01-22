package tienhoitopeliid.tienhoitopeli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import tienhoitopeliid.tienhoitopeli.Sovelluslogiikka.ReitinLukija;
import tienhoitopeliid.tienhoitopeli.Sovelluslogiikka.Kartta;
import tienhoitopeliid.tienhoitopeli.Sovelluslogiikka.Lumikerros;
import tienhoitopeliid.tienhoitopeli.Sovelluslogiikka.Saaennuste;

/**
 * Hello world!
 *
 */
public class Kayttoliittyma {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);

        Kartta koeKartta = new Kartta();
        koeKartta.LueKartta();
        koeKartta.TulostaKartta();
        koeKartta.TulostaSelite();

        Saaennuste ennuste = new Saaennuste();
        ennuste.LueSaaennuste();
        ennuste.TulostaSaaennuste();

        Lumikerros lumikerros = new Lumikerros(koeKartta, ennuste);
        lumikerros.AlustaLumikerros();
        lumikerros.lisaaLuntaYhdenSekunninSateenVerran();
        lumikerros.TulostaLumikerros();

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
            lopetuskomentoAnnettu=reitinLukija.KirjaaReitti(komento);
        }
        

        

    }
}
