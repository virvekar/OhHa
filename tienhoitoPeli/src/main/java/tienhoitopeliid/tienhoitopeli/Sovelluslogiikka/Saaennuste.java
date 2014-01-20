/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeliid.tienhoitopeli.Sovelluslogiikka;

import java.util.ArrayList;

/**
 *
 * @author virvemaa
 */
public class Saaennuste {
    private int ennusteenPituus;
    private String ennusteTiedosto;
    private int lumisateenMaara;
    
    public Saaennuste(){
        this.ennusteTiedosto="/home/virvemaa/OhHa/tienhoitoPeli/src/main/java/tienhoitopeliid/tienhoitopeli/saaennusteet/saaennuste1.txt";
    }
    
    public void LueSaaennuste(){
        TiedostonLukija saaennusteenLukija=new TiedostonLukija(this.ennusteTiedosto);
        ArrayList<String> ennuste=saaennusteenLukija.LueTiedosto();
        this.ennusteenPituus=Integer.parseInt(ennuste.get(0));
        this.lumisateenMaara=Integer.parseInt(ennuste.get(1));
      
    }
    public int GetEnnusteenPituus(){
        return this.ennusteenPituus;
    }
    public int GetLumisateenMaara(){
        return this.lumisateenMaara;
    }
    
    public void TulostaSaaennuste(){

        System.out.println("Seuraavan "+this.ennusteenPituus+ " sekunnin aikana tiedossa "+this.lumisateenMaara+ " cm lunta.");
    }
    
}
