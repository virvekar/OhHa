/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.Sovelluslogiikka;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Pitaa ylla ennatysten listaa
 *
 * @author virvemaa
 */
public class EnnatysListanPitaja {

    private String tiedostonNimi;
    private int riviLuku2;

    public EnnatysListanPitaja(String annettuTiedosto) {
        this.tiedostonNimi = annettuTiedosto;
        this.riviLuku2 = 1;
    }

    /**
     * Kirjoittaa ennatystenlistan uudestaan uuden ennatyksen kera
     *
     * @param tekija pelaajan nimimerkki
     * @param luku aiheutetut tienhoitoKulut
     */
    public void LisaaEnnatys(String tekija, String luku) {
        TiedostonLukija ennatystenLukija = new TiedostonLukija(this.tiedostonNimi);
        ArrayList<String> vanhaEnnatysLista = ennatystenLukija.LueTiedosto();

        if (vanhaEnnatysLista.isEmpty()) {
            KirjoitaEnsimmainenEnnatys(tekija, luku);
        } else {
            KirjoitaUusiListaLisatenEnnatys(tekija, luku, vanhaEnnatysLista);
        }
    }

    /**
     * Tarkistaa onko string integer
     *
     * @param teksti tarkistettava string
     * @return true, jos string on integer
     */
    public static boolean OnkoInteger(String teksti) {
        try {
            Integer.parseInt(teksti);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Kirjoittaa ennatyksen tyhjaan tiedostoon
     *
     * @param tekija pelaajan nimimerkki
     * @param luku aiheutuneet tienhoitokulut
     */
    public void KirjoitaEnsimmainenEnnatys(String tekija, String luku) {
        try {
            FileWriter kirjoittaja = new FileWriter(this.tiedostonNimi);
            kirjoittaja.write(luku + " " + tekija + "\n");
            kirjoittaja.close();
        } catch (IOException e) {
            System.out.println("ei onnistunut");
        }
    }

    /**
     * Kirjoittaa ennatyslistan uudestaan sijoittaen uuden ennatyksen oikeaan
     * kohtaan.
     *
     * @param tekija pelaajan nimimerkki
     * @param luku aiheutuneet tienhoitokulut
     * @param vanhaEnnatysLista ArrayList<String> jossa vanhat ennatykset
     */
    public void KirjoitaUusiListaLisatenEnnatys(String tekija, String luku, ArrayList<String> vanhaEnnatysLista) {

        int riviLuku = 1;
        int pisteet = Integer.parseInt(luku);
        boolean ennatysLisatty = false;
        try {
            FileWriter kirjoittaja = new FileWriter(this.tiedostonNimi);
            for (String rivi : vanhaEnnatysLista) {
                String[] osat = rivi.split(" ");
                if (osat[0] != null && OnkoInteger(osat[0]) && riviLuku <= 10) {
                    if (Integer.parseInt(osat[0]) > pisteet && !ennatysLisatty) {
                        kirjoittaja.write(luku + " " + tekija + "\n");
                        riviLuku++;
                        ennatysLisatty = true;
                    }
                    if (riviLuku <= 10) {
                        kirjoittaja.write(rivi + "\n");
                        riviLuku++;
                    }
                }
            }
            if (!ennatysLisatty && riviLuku <= 10) {
                kirjoittaja.write(luku + " " + tekija + "\n");
                ennatysLisatty = true;
            }
            kirjoittaja.close();
        } catch (IOException e) {
            System.out.println("ei onnistunut");
        }
    }

    public ArrayList<String> getEnnatysLista() {
        TiedostonLukija ennatystenLukija = new TiedostonLukija(this.tiedostonNimi);
        return ennatystenLukija.LueTiedosto();

    }

  //  public boolean LisaaValiin(ArrayList<String> vanhaEnnatysLista, boolean ennatysLisatty, int pisteet, String tekija, String luku, FileWriter kirjoittaja) {
  //      for (String rivi : vanhaEnnatysLista) {
  //          String[] osat = rivi.split(" ");
  //          if (osat[0] != null && OnkoInteger(osat[0]) && riviLuku2 <= 10) {
  //              if (Integer.parseInt(osat[0]) > pisteet && !ennatysLisatty) {
  //                  try {
  //                      kirjoittaja.write(luku + " " + tekija + "\n");
  //                      riviLuku2++;
  //                      ennatysLisatty = true;
  //                  } catch (IOException e) {
  //                      System.out.println("ei onnistunut");
  //                  }
  //              }
  //              if (riviLuku2 <= 10) {
  //                  try {
  //                      kirjoittaja.write(rivi + "\n");
  //                      riviLuku2++;
  //                  } catch (IOException e) {
  //                      System.out.println("ei onnistunut");
  //                  }

  //              }
  //          }
  //      }
  //      return ennatysLisatty;
  //  }
}
