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
    private ArrayList<ArrayList<Integer>> lumikerroksenPaksuus;
    
    public Lumikerros(Kartta annettuKartta){
        this.kartta=annettuKartta;
        this.lumikerroksenPaksuus=new ArrayList<ArrayList<Integer>>();
    }
    public void AlustaLumikerros(){
        ArrayList<Integer> kartanKoko = this.kartta.KartanKoko();
        ArrayList<String> karttaPohja= this.kartta.GetKarttaPohja();
        int riviPiste;
        
        //Kay lapi kaikki rivit
        for(String rivi : karttaPohja){
            //Kay lapi rivin jokaisen pisteen
            ArrayList<Integer> lumiRivi= new ArrayList<Integer>();
            
            for(riviPiste=0; riviPiste<kartanKoko.get(0); riviPiste ++){
                lumiRivi.add(0);
            }
            this.lumikerroksenPaksuus.add(lumiRivi);
        }
    }
}
