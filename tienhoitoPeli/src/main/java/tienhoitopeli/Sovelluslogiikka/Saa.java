/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.Sovelluslogiikka;

/**
 *Olio, jonka tehtavana on luoda todellinen saa ennusteen perusteella
 * @author Virve Karsisto
 * 
 */
public class Saa {
    private Saaennuste ennuste;
    private int sateenPituus;
    private int sateenMaara;
    
    /**
     * 
     * @param annettuEnnuste Saaennuste,joka annetaan konstruktorille
     */
    public Saa(Saaennuste annettuEnnuste){
        this.ennuste=annettuEnnuste;
        this.sateenMaara=ennuste.GetLumisateenMaara();
        this.sateenPituus=ennuste.GetEnnusteenPituus();
    }
    
    public void ArvoSateenPituus(){
        this.sateenPituus=this.ennuste.GetEnnusteenPituus();
    }
    public void ArvoSateenMaara(){
        this.sateenMaara=this.ennuste.GetLumisateenMaara();
    }
    public int getSateenPituus(){
        return this.sateenPituus;
    }
    public int getSateenMaara(){
        return this.sateenMaara;
    }
    
}
