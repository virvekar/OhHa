/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeliid.tienhoitopeli.Sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author virvemaa
 */
public class Lumikerros {
    private Kartta kartta;
    private Saaennuste ennuste;
    private int sekunti;
    private HashMap lumikerrosKoordinaateissa;
    
    public Lumikerros(Kartta annettuKartta, Saaennuste annettuEnnuste){
        this.kartta=annettuKartta;
        this.ennuste=annettuEnnuste;
        this.sekunti=0;
        this.lumikerrosKoordinaateissa=new HashMap();
    }
    public void AlustaLumikerros(){
        ArrayList<Integer> kartanKoko = this.kartta.KartanKoko();
        ArrayList<String> karttaPohja= this.kartta.GetKarttaPohja();
        int riviPiste;
        int riviNumero=0;
        
        //Kay lapi kaikki rivit
        for(String rivi : karttaPohja){
  
            //Kay lapi rivin jokaisen pisteen
            for(riviPiste=0; riviPiste<kartanKoko.get(0); riviPiste ++){    
            ArrayList<Integer> avainkoordinaatit=new ArrayList<Integer>();
            avainkoordinaatit.add(riviNumero);
            avainkoordinaatit.add(riviPiste);
            
              if(rivi.charAt(riviPiste)=='x'){
                  this.lumikerrosKoordinaateissa.put(avainkoordinaatit,(double) -1.0);
              }else{
                  this.lumikerrosKoordinaateissa.put(avainkoordinaatit, (double) 0.0);
              }
            }
       riviNumero++;
        }
    }
    
    public void TulostaLumikerros(){
      ArrayList<Integer> kartanKoko = this.kartta.KartanKoko();
      String tulosteRivi="";
      for (int i=0; i<kartanKoko.get(1); i++){
          
          for (int j=0; j<kartanKoko.get(0); j++){
              ArrayList<Integer> koordinaatit= new ArrayList<Integer>();
              koordinaatit.add(i);
              koordinaatit.add(j);
              tulosteRivi=tulosteRivi.concat(this.lumikerrosKoordinaateissa.get(koordinaatit)+" ");
          }
          System.out.println(tulosteRivi);
          tulosteRivi="";
      }
      
    }
    public void lisaaLuntaYhdenSekunninSateenVerran(){
        double sekunnissaSataa= ((double) ennuste.GetLumisateenMaara()) / ((double) ennuste.GetEnnusteenPituus());
        ArrayList<Integer> kartanKoko = this.kartta.KartanKoko();
        int rivi=0;
        for (int i=0; i<kartanKoko.get(1); i++){
            
             for (int j=0; j<kartanKoko.get(0); j++){
                ArrayList<Integer> koordinaatit= new ArrayList<Integer>();
                koordinaatit.add(i);
                koordinaatit.add(j);
                double vanhaLumiMaara= (double) this.lumikerrosKoordinaateissa.get(koordinaatit);
                if (vanhaLumiMaara>=0){
                  double uusiLumiMaara=vanhaLumiMaara+sekunnissaSataa;
                  this.lumikerrosKoordinaateissa.put(koordinaatit,uusiLumiMaara);
                }
             }
        }
        
    }
}
