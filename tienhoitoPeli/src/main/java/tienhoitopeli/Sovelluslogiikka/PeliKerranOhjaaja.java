/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.Sovelluslogiikka;

/**
 *Sailyttaa tietoa eri tasoista ja pelaajasta
 * @author virvemaa
 */
public class PeliKerranOhjaaja {
    private String pelaaja;
    private int kentanNumero;
    
    public PeliKerranOhjaaja(){
        this.kentanNumero=1;
        this.pelaaja="";
    }
    public boolean setPelaajanNimi(String nimi){
        if(nimi.isEmpty()){
            return false;
        }else{
            this.pelaaja=nimi;
            return true;
        }
        
    }
    public String getPelaajanNimi(){
        return this.pelaaja;
    }
}
