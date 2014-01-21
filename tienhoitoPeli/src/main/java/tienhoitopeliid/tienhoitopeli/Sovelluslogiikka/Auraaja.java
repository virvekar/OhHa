/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeliid.tienhoitopeli.Sovelluslogiikka;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author virvemaa
 */
public class Auraaja {
    private Scanner lukija;
    private ArrayList<ArrayList<Integer>> reitti;
    private Kartta kartta;
    
    public Auraaja(Kartta annettuKartta){
       this.kartta=annettuKartta;
       this.lukija=new Scanner(System.in);
       this.reitti= new ArrayList<ArrayList<Integer>>();
               
    }
    
    public void KirjaaReitti(){
        System.out.println("Milta vaakarivilta aloitetaan (ylin=1)?");
        String aloitusRivi=this.lukija.nextLine();
        System.out.println("Milta pystyRivilta aloitetaan (vasemmalla=1)?");
        String aloitusSarake=this.lukija.nextLine();
        
        ArrayList<Integer> reittiPiste=new ArrayList<Integer>();
        int aRivi=Integer.parseInt(aloitusRivi);
        int aSarake=Integer.parseInt(aloitusSarake);
        reittiPiste.add(aRivi);
        reittiPiste.add(aSarake);
        reitti.add(reittiPiste);
        
        System.out.println("Liiku painamalla seuraavia nappaimia: i=ylos, k=alas, j=vasemmalle, l=oikealle");
        System.out.println("Lopeta liikkuminen painamalla p");
        
        char kirjain='h';
        while(kirjain!='p'){
            kirjain=lukija.nextLine().charAt(0);
            if(kirjain=='i'){
                int rivi=reitti.get(reitti.size()-1).get(0)+1;
                int sarake=reitti.get(reitti.size()-1).get(1);
                ArrayList<Integer> uusiPiste=new ArrayList<Integer>();
                uusiPiste.add(rivi);
                uusiPiste.add(sarake);
                reitti.add(uusiPiste);
            }
            if(kirjain=='k'){
                int rivi=reitti.get(reitti.size()-1).get(0)-1;
                int sarake=reitti.get(reitti.size()-1).get(1);
                ArrayList<Integer> uusiPiste=new ArrayList<Integer>();
                uusiPiste.add(rivi);
                uusiPiste.add(sarake);
                reitti.add(uusiPiste);
            }
            if(kirjain=='j'){
               int rivi=reitti.get(reitti.size()-1).get(0);
                int sarake=reitti.get(reitti.size()-1).get(1)-1;
                ArrayList<Integer> uusiPiste=new ArrayList<Integer>();
                uusiPiste.add(rivi);
                uusiPiste.add(sarake);
                reitti.add(uusiPiste);
            }
            if(kirjain=='l'){
                int rivi=reitti.get(reitti.size()-1).get(0);
                int sarake=reitti.get(reitti.size()-1).get(1)+1;
                ArrayList<Integer> uusiPiste=new ArrayList<Integer>();
                uusiPiste.add(rivi);
                uusiPiste.add(sarake);
                reitti.add(uusiPiste);
            }
        }
        System.out.println(reitti);
        
    }
}
