/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.Sovelluslogiikka;


import java.util.ArrayList;

/**
 * 
 * Olio, jonka tehtavana on sailyttaa karttatietoa ja jakaa sita eteenpain.
 * 
 * @author Virve Karsisto
 */
public class Kartta {
    
    private String karttaTiedostonNimi;
    private ArrayList<String> karttaPohja;
    
    /**
     * 
     * @param tiedostonNimi Tiedostopolku, josta kartta loytyy.
     * 
     * karttaPohjaan tallennetaan tiedosto. Kukin ArrayListin listapaikat sisaltavat 
     * kukin yhden tiedoston rivin.
     */
    
    public Kartta(String tiedostonNimi){
        
        this.karttaTiedostonNimi=tiedostonNimi;
        this.karttaPohja= new ArrayList<String>();
    }
    
    /**
     * Luo TiedostonLukija olion ja delegoi sille tiedoston lukemisen.
     * Mikali tiedostoa ei loydy, ohjelma lopetetaan.
     */
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
   
    
    
    /**
     * Antaa kartan koon.
     * 
     * @return ArrayList<Integer> ,jossa ensimmainen luku on kartan leveys ja
     * toinen kartan korkeus.
     */
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
