/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.Sovelluslogiikka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *Olio, jonka tehtavana on lukea sille annettu tiedosto ja palauttaa se
 * ArrayListina, jonka kukin paikka sisaltaa yhden tiedoston rivin.
 * @author Virve Karsisto
 */
public class TiedostonLukija {
    private File tiedosto;
    private ArrayList<String> luettuTiedosto;
    
    public TiedostonLukija(String tiedostonNimi){
        this.tiedosto= new File(tiedostonNimi);
        this.luettuTiedosto=new ArrayList<String>();
    }
    
    public ArrayList<String> LueTiedosto(){
        // Lukee karttapohjan tiedostosta ja tallentaa sen riveittain ArrayListiin.
        try{
        FileInputStream syoteVirta = new FileInputStream(this.tiedosto);
        InputStreamReader syoteVirranLukija =new InputStreamReader(syoteVirta);
        BufferedReader puskuroija = new BufferedReader(syoteVirranLukija);
        while (puskuroija.ready()) {
            String rivi=puskuroija.readLine();
            this.luettuTiedosto.add(rivi);
        }
        }
        catch (IOException e) { System.out.println(e);}
        
        return this.luettuTiedosto;
    }
}
