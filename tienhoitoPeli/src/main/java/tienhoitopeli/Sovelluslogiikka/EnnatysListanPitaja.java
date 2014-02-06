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

    public EnnatysListanPitaja() {
        this.tiedostonNimi = "src/main/java/tienhoitopeli/ennatyslistat/ennatyslista1.txt";
    }

    public void LisaaEnnatys(String tekija, String luku) {
        TiedostonLukija ennatystenLukija = new TiedostonLukija(this.tiedostonNimi);
        ArrayList<String> vanhaEnnatysLista = ennatystenLukija.LueTiedosto();
        vanhaEnnatysLista.add(luku + " " + tekija);
        Collections.sort(vanhaEnnatysLista);
        Collections.reverse(vanhaEnnatysLista);

        try {
            FileWriter kirjoittaja = new FileWriter(this.tiedostonNimi);
            for (String rivi : vanhaEnnatysLista) {
                kirjoittaja.write(rivi + "\n");
            }
            kirjoittaja.close();
        } catch (IOException e) {
            System.out.println("ei onnistunut");
        }

    }

}
