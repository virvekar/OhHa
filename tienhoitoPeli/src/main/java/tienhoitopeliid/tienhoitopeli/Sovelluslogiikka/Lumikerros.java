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
public class Lumikerros {
    private Kartta kartta;
    private Saaennuste ennuste;
    private int sekunti;
    private ArrayList<ArrayList<Double>> lumikerroksenPaksuus;
    
    public Lumikerros(Kartta annettuKartta, Saaennuste annettuEnnuste){
        this.kartta=annettuKartta;
        this.ennuste=annettuEnnuste;
        this.sekunti=0;
        this.lumikerroksenPaksuus=new ArrayList<ArrayList<Double>>();
    }
    public void AlustaLumikerros(){
        ArrayList<Integer> kartanKoko = this.kartta.KartanKoko();
        ArrayList<String> karttaPohja= this.kartta.GetKarttaPohja();
        int riviPiste;
        
        //Kay lapi kaikki rivit
        for(String rivi : karttaPohja){
            
            ArrayList<Double> lumiRivi= new ArrayList<Double>();
            //Kay lapi rivin jokaisen pisteen
            for(riviPiste=0; riviPiste<kartanKoko.get(0); riviPiste ++){
                if(rivi.charAt(riviPiste)=='o'){
                    lumiRivi.add(0.0);
                }else{
                    lumiRivi.add(-1.0);
                }
                
            }
            this.lumikerroksenPaksuus.add(lumiRivi);
        }
    }
    public void TulostaLumikerros(){
        for (ArrayList<Double> rivi : this.lumikerroksenPaksuus){
            System.out.println(rivi);
        }
    }
    public void lisaaLuntaYhdenSekunninSateenVerran(){
        double sekunnissaSataa= ((double) ennuste.GetLumisateenMaara()) / ((double) ennuste.GetEnnusteenPituus());
       
        int rivi=0;
        
        for(ArrayList<Double> lumirivi : this.lumikerroksenPaksuus){
            
            ArrayList<Double> uusiLumiRivi =new ArrayList<Double>();
            for(double lumenMaara : lumirivi){
                if (lumenMaara < 0.0){
                    uusiLumiRivi.add(-1.0);
                } else{
                    double uusiLumiMaara;
                    uusiLumiMaara=lumenMaara+sekunnissaSataa;
                    uusiLumiRivi.add(uusiLumiMaara);
                }
                this.lumikerroksenPaksuus.set(rivi, uusiLumiRivi);
                
            }
            rivi++;
        }
    }
}
