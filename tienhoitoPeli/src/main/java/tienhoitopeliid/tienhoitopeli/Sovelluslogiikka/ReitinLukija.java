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

    private ArrayList<ArrayList<Integer>> reitti;
    private Kartta kartta;
    private Lumikerros lumikerros;

    public ReitinLukija(Kartta annettuKartta, Lumikerros annettuLumikerros) {
        this.kartta = annettuKartta;
        this.lumikerros = annettuLumikerros;
        this.reitti = new ArrayList<ArrayList<Integer>>();

    }

    public boolean KirjaaEnsimmainenPiste(String aloitusRivi, String aloitusSarake) {
        int aRivi = Integer.parseInt(aloitusRivi) - 1;
        int aSarake = Integer.parseInt(aloitusSarake) - 1;
        ArrayList<Integer> reittiPiste = this.LuoUusiKoordinaattiPiste(aRivi, aSarake);

        boolean onnistuiko = this.TarkistaPisteJaLisaaReitille(reittiPiste);

        return !onnistuiko;

    }

    public boolean KirjaaReitti(String komento) {

        boolean lopetuskomentoAnnettu = false;

        if (komento != null && !komento.isEmpty()) {
            lopetuskomentoAnnettu = this.SuoritaKomentoJosSeLoytyy(komento);
            return lopetuskomentoAnnettu;
        } else {
            System.out.println("Tuntematon komento");
            return false;
        }
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
    public ArrayList<ArrayList<Integer>> GetReitti(){
        return this.reitti;
    }
}