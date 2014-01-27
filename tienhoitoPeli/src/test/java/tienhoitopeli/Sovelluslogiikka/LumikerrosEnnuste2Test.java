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
public class LumikerrosEnnuste2Test {
    private Kartta kartta;
    private Lumikerros lumikerros;
    private Saaennuste ennuste;
    private Saa saa;
    private ArrayList<Integer> koordinaatit;
    
    public LumikerrosEnnuste2Test() {
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
        kartta = new Kartta("src/main/java/tienhoitopeli/kartat/kartta1.txt");
        kartta.LueKartta();
        ennuste = new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste2.txt");
        ennuste.LueSaaennuste();
        saa=new Saa(ennuste);
        lumikerros = new Lumikerros(kartta);
        lumikerros.AlustaLumikerros();
        koordinaatit=new ArrayList<Integer>();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void LisaaLuntaYhdenSekunninSateenVerranKadulleLisaaOikeanMaaran(){
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(),saa.getSateenPituus());
        HashMap kerros=lumikerros.GetLumikerrosKoordinaateissa();
        koordinaatit.add(0);
        koordinaatit.add(1);
        double kerrosKoordinaateissa=(double) kerros.get(koordinaatit);
        boolean vastaus=kerrosKoordinaateissa>1.99 && kerrosKoordinaateissa<2.01;
        assertEquals(vastaus,true);
    }
}
