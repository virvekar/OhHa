/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.Sovelluslogiikka;

import java.util.ArrayList;

/**
 *
 * @author virvemaa
 */
public class Saaennuste {
    private int ennusteenPituus;
    private String ennusteTiedosto;
    private int lumisateenMaara;
    
    public Saaennuste(String tiedostonNimi){
        this.ennusteTiedosto=tiedostonNimi;
    }
    
    public void LueSaaennuste(){
        TiedostonLukija saaennusteenLukija=new TiedostonLukija(this.ennusteTiedosto);
        ArrayList<String> ennuste=saaennusteenLukija.LueTiedosto();
       if(ennuste.isEmpty()){
            System.out.println("Ohjelmaa suoritettaessa tapahtui virhe. Ennustetta ei loytynyt.");
            System.exit(0);
        }
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
