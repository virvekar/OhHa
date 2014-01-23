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
public class Kartta {
    
    private String karttaTiedostonNimi;
    private ArrayList<String> karttaPohja;
    
    public Kartta(String tiedostonNimi){
        
        this.karttaTiedostonNimi=tiedostonNimi;
        this.karttaPohja= new ArrayList<String>();
    }
    
    public void LueKartta(){
        // Lukee karttapohjan tiedostosta ja tallentaa sen riveittain ArrayListiin.
        TiedostonLukija kartanLukija=new TiedostonLukija(this.karttaTiedostonNimi);
        this.karttaPohja=kartanLukija.LueTiedosto();
        if(this.karttaPohja.isEmpty()){
            System.out.println("Ohjelmaa suoritettaessa tapahtui virhe. Karttaa ei loytynyt.");
            System.exit(0);
        }
       
    }
    public ArrayList<String> GetKarttaPohja(){
        return this.karttaPohja;
    }
   
    public void TulostaKartta(){
        for(String rivi : this.karttaPohja){
            System.out.println(rivi);
        }
    }
    public void TulostaSelite(){
        System.out.println("\n lumeton katu=o, luminen katu=s, rakennus=x \n");
    }
    public ArrayList<Integer> KartanKoko(){
        //Kartan muoto oletetaan suorakaiteeksi
        
        ArrayList<Integer> koko = new ArrayList<Integer>();
        int leveys=this.karttaPohja.get(0).length();
        int korkeus=this.karttaPohja.size();
        
        koko.add(leveys);
        koko.add(korkeus);
        return koko;
    }
}
