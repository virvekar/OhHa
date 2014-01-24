/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.Sovelluslogiikka;

/**
 *
 * @author virvemaa
 */
public class TienhoidonSuorittaja {
    private Auraaja auraaja;
    private Saa saa;
    private Lumikerros lumikerros;
    private Kululaskuri kululaskuri;

    
    public TienhoidonSuorittaja(Auraaja annettuAuraaja, Saa annettuSaa, Lumikerros annettuLumikerros){
        this.auraaja=annettuAuraaja;
        this.lumikerros=annettuLumikerros;
        this.saa=annettuSaa;
        this.kululaskuri=new Kululaskuri();

    }
    
    public void SuoritaTienhoito(){
        int sateenKesto=this.saa.getSateenPituus();
        int aurauksenKesto=this.auraaja.AnnaAuraajanReitti().size();
        
        int OperaationKesto=this.PalautaSuurempi(sateenKesto, aurauksenKesto);
        
        for (int aika=1; aika<=OperaationKesto; aika++){
            if(aika<=sateenKesto){
                lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(), saa.getSateenPituus());
            }
            if(aika<=aurauksenKesto){
                auraaja.AuraaSeuraavaPiste();
            }
        }    
    }
    
    public int laskeKulut(){
        kululaskuri.lisaaKolariKulut(lumikerros.laskeRuudutJoissaOnLunta());
        kululaskuri.laskeBensakulu(this.auraaja.AnnaAuraajanReitti().size());
        return kululaskuri.getKulujenMaara();
    }
    public int PalautaSuurempi(int luku1, int luku2){
        if(luku1<luku2){
            return luku2;
        }else{
            return luku1;
        }
    }
}
