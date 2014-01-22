/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeliid.tienhoitopeli.Sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author virvemaa
 */
public class ReitinLukija {

    private Scanner lukija;
    private ArrayList<ArrayList<Integer>> reitti;
    private Kartta kartta;
    private Lumikerros lumikerros;

    public ReitinLukija(Kartta annettuKartta, Lumikerros annettuLumikerros) {
        this.kartta = annettuKartta;
        this.lumikerros = annettuLumikerros;
        this.lukija = new Scanner(System.in);
        this.reitti = new ArrayList<ArrayList<Integer>>();

    }

    public void KirjaaReitti() {

        this.KirjaaEnsimmainenPiste();
        boolean lopetuskomentoAnnettu = false;
        
        System.out.println("Liiku painamalla seuraavia nappaimia: i=ylos, k=alas, j=vasemmalle, l=oikealle");
        System.out.println("Lopeta liikkuminen painamalla p");

        while (!lopetuskomentoAnnettu) {
            String komento = lukija.nextLine();
            if (komento != null && !komento.isEmpty()) {
                lopetuskomentoAnnettu = this.SuoritaKomentoJosSeLoytyy(komento);
            } else {
                System.out.println("Tuntematon komento");
            }
        }
        System.out.println(reitti);

    }

    public void KirjaaEnsimmainenPiste() {
        boolean ekaPisteEiHyvaksytty = true;
        while (ekaPisteEiHyvaksytty) {
            ArrayList<Integer> reittiPiste = this.KysyEnsimmainenPiste();
            boolean onnistuiko = this.TarkistaPisteJaLisaaReitille(reittiPiste);
            if (onnistuiko) {
                ekaPisteEiHyvaksytty = false;
            } else {
                System.out.println("Piste ei kelpaa. Anna uusiPiste");
            }
        }
    }

    public ArrayList<Integer> KysyEnsimmainenPiste() {
        System.out.println("Milta vaakarivilta aloitetaan (ylin=1)?");
        String aloitusRivi = this.lukija.nextLine();
        System.out.println("Milta pystyRivilta aloitetaan (vasemmalla=1)?");
        String aloitusSarake = this.lukija.nextLine();

        int aRivi = Integer.parseInt(aloitusRivi) - 1;
        int aSarake = Integer.parseInt(aloitusSarake) - 1;
        ArrayList<Integer> reittiPiste = this.LuoUusiKoordinaattiPiste(aRivi, aSarake);
        return reittiPiste;
    }

    public boolean TarkistaOnkoRajojenSisalla(ArrayList<Integer> piste) {
        ArrayList<Integer> kartanKoko = this.kartta.KartanKoko();

        //meneeko pisteen rivi yli kartan rajan
        if (piste.get(0) > kartanKoko.get(0) - 1 || piste.get(0) < 0) {
            return false;
        }
        //meneeko pisteen sarake yli kartan rajan
        if (piste.get(1) > kartanKoko.get(1) - 1 || piste.get(1) < 0) {
            return false;
        }

        return true;
    }

    public boolean TarkistaOnkoPisteKadulla(ArrayList<Integer> piste) {
        HashMap lumikerrosKoordinaateissa = this.lumikerros.GetLumikerrosKoordinaateissa();
        if ((double) lumikerrosKoordinaateissa.get(piste) < 0) {
            return false;
        }

        return true;
    }

    public boolean TarkistaPisteJaLisaaReitille(ArrayList<Integer> uusiPiste) {
        if (this.TarkistaOnkoRajojenSisalla(uusiPiste)) {
            if (this.TarkistaOnkoPisteKadulla(uusiPiste)) {
                reitti.add(uusiPiste);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> LuoUusiKoordinaattiPiste(int rivi, int sarake) {
        ArrayList<Integer> uusiPiste = new ArrayList<Integer>();
        uusiPiste.add(rivi);
        uusiPiste.add(sarake);
        return uusiPiste;
    }

    public boolean SuoritaKomentoJosSeLoytyy(String komento) {
        char kirjain = komento.charAt(0);
        if (kirjain == 'p') {
            return true;
        } else {
            if (kirjain == 'i') {
                this.LiikuYlos();

            } else if (kirjain == 'k') {
                this.LiikuAlas();

            } else if (kirjain == 'j') {
                this.LiikuVasemmalle();

            } else if (kirjain == 'l') {
                this.LiikuOikealle();

            } else {
                System.out.println("Tuntematon komento");

            }
            return false;
        }
    }

    public void LiikuYlos() {
        int rivi = reitti.get(reitti.size() - 1).get(0) - 1;
        int sarake = reitti.get(reitti.size() - 1).get(1);
        ArrayList<Integer> uusiPiste = this.LuoUusiKoordinaattiPiste(rivi, sarake);
        boolean onnistuiko = this.TarkistaPisteJaLisaaReitille(uusiPiste);
    }

    public void LiikuAlas() {
        int rivi = reitti.get(reitti.size() - 1).get(0) + 1;
        int sarake = reitti.get(reitti.size() - 1).get(1);
        ArrayList<Integer> uusiPiste = this.LuoUusiKoordinaattiPiste(rivi, sarake);
        boolean onnistuiko = this.TarkistaPisteJaLisaaReitille(uusiPiste);
    }

    public void LiikuVasemmalle() {
        int rivi = reitti.get(reitti.size() - 1).get(0);
        int sarake = reitti.get(reitti.size() - 1).get(1) - 1;
        ArrayList<Integer> uusiPiste = this.LuoUusiKoordinaattiPiste(rivi, sarake);
        boolean onnistuiko = this.TarkistaPisteJaLisaaReitille(uusiPiste);
    }

    public void LiikuOikealle() {
        int rivi = reitti.get(reitti.size() - 1).get(0);
        int sarake = reitti.get(reitti.size() - 1).get(1) + 1;
        ArrayList<Integer> uusiPiste = this.LuoUusiKoordinaattiPiste(rivi, sarake);
        boolean onnistuiko = this.TarkistaPisteJaLisaaReitille(uusiPiste);
    }
}
