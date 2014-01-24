/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.Sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author virvemaa
 */
public class LumikerrosTest {
    private Kartta kartta;
    
    public LumikerrosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kartta = new Kartta("src/main/java/tienhoitopeli/kartat/kartta1.txt");
        kartta.LueKartta();
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void LumikerroksestaTuleeOikeanKokoinen() {
        Saaennuste ennuste = new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste1.txt");
        ennuste.LueSaaennuste();
        Saa saa=new Saa(ennuste);
        Lumikerros lumikerros = new Lumikerros(kartta);
        lumikerros.AlustaLumikerros();
        HashMap kerros=lumikerros.GetLumikerrosKoordinaateissa();
        assertEquals(5*5,kerros.size());
    }
    @Test
    public void LumikerrosRakennuksenPaallaOnNegatiivinen(){
        Saaennuste ennuste = new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste1.txt");
        ennuste.LueSaaennuste();
        Saa saa=new Saa(ennuste);
        Lumikerros lumikerros = new Lumikerros(kartta);
        lumikerros.AlustaLumikerros();
        HashMap kerros=lumikerros.GetLumikerrosKoordinaateissa();
        ArrayList<Integer> koordinaatit=new ArrayList<Integer>();
        koordinaatit.add(0);
        koordinaatit.add(0);
        double kerrosKoordinaateissa=(double) kerros.get(koordinaatit);
        boolean vastaus=kerrosKoordinaateissa<0;
        assertEquals(vastaus,true);
    }
    @Test
    public void LumikerrosKadullaOnAluksiNolla(){
        Saaennuste ennuste = new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste1.txt");
        ennuste.LueSaaennuste();
        Saa saa=new Saa(ennuste);
        Lumikerros lumikerros = new Lumikerros(kartta);
        lumikerros.AlustaLumikerros();
        HashMap kerros=lumikerros.GetLumikerrosKoordinaateissa();
        ArrayList<Integer> koordinaatit=new ArrayList<Integer>();
        koordinaatit.add(0);
        koordinaatit.add(1);
        double kerrosKoordinaateissa=(double) kerros.get(koordinaatit);
        boolean vastaus=kerrosKoordinaateissa>-0.01 && kerrosKoordinaateissa<0.01;
        assertEquals(vastaus,true);
    }
    @Test
    public void LisaaLuntaYhdenSekunninSateenVerranKadulleLisaaLunta(){
        Saaennuste ennuste = new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste1.txt");
        ennuste.LueSaaennuste();
       Saa saa=new Saa(ennuste);
        Lumikerros lumikerros = new Lumikerros(kartta);
        lumikerros.AlustaLumikerros();
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(),saa.getSateenPituus());
        HashMap kerros=lumikerros.GetLumikerrosKoordinaateissa();
        ArrayList<Integer> koordinaatit=new ArrayList<Integer>();
        koordinaatit.add(0);
        koordinaatit.add(1);
        double kerrosKoordinaateissa=(double) kerros.get(koordinaatit);
        boolean vastaus=kerrosKoordinaateissa>0.01;
        assertEquals(vastaus,true);
    }
    public void LisaaLuntaYhdenSekunninSateenVerranKadulleEiLisaaRakennuksenPaalle(){
        Saaennuste ennuste = new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste1.txt");
        ennuste.LueSaaennuste();
       Saa saa=new Saa(ennuste);
        Lumikerros lumikerros = new Lumikerros(kartta);
        lumikerros.AlustaLumikerros();
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(),saa.getSateenPituus());
        HashMap kerros=lumikerros.GetLumikerrosKoordinaateissa();
        ArrayList<Integer> koordinaatit=new ArrayList<Integer>();
        koordinaatit.add(0);
        koordinaatit.add(0);
        double kerrosKoordinaateissa=(double) kerros.get(koordinaatit);
        boolean vastaus=kerrosKoordinaateissa<-0.01;
        assertEquals(vastaus,true);
    }
    @Test
    public void LisaaLuntaYhdenSekunninSateenVerranKadulleLisaaOikeanMaaran(){
        Saaennuste ennuste = new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste2.txt");
        ennuste.LueSaaennuste();
        Saa saa=new Saa(ennuste);
        Lumikerros lumikerros = new Lumikerros(kartta);
        lumikerros.AlustaLumikerros();
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(),saa.getSateenPituus());
        HashMap kerros=lumikerros.GetLumikerrosKoordinaateissa();
        ArrayList<Integer> koordinaatit=new ArrayList<Integer>();
        koordinaatit.add(0);
        koordinaatit.add(1);
        double kerrosKoordinaateissa=(double) kerros.get(koordinaatit);
        boolean vastaus=kerrosKoordinaateissa>1.99 && kerrosKoordinaateissa<2.01;
        assertEquals(vastaus,true);
    }
}
