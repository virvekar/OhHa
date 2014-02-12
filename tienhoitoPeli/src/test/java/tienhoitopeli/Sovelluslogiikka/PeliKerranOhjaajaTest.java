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
public class PeliKerranOhjaajaTest {

    private PeliKerranOhjaaja peliKerranOhjaaja;

    public PeliKerranOhjaajaTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.peliKerranOhjaaja = new PeliKerranOhjaaja();
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void SetPelaajanNimiTallentaaNimen() {
        this.peliKerranOhjaaja.setPelaajanNimi("Pekka");
        assertEquals("Pekka",this.peliKerranOhjaaja.getPelaajanNimi());
    }
    
    @Test
    public void SetPelaajanNimiEiTallennaTyhjaa(){
        this.peliKerranOhjaaja.setPelaajanNimi("");
        assertTrue(this.peliKerranOhjaaja.getPelaajanNimi().isEmpty());
    }
    
    @Test
    public void getKartanPolkuAntaaOikeanPolunEkallaTasolla(){
        String polku="src/main/java/tienhoitopeli/kartat/kartta1.txt";
        assertEquals(polku,this.peliKerranOhjaaja.getKartanPolku());
    }
    @Test
    public void getKartanPolkuAntaaOikeanPolunTokallaTasolla(){
        String polku="src/main/java/tienhoitopeli/kartat/kartta2.txt";
        this.peliKerranOhjaaja.MeneSeuraavaanKenttaan();
        assertEquals(polku,this.peliKerranOhjaaja.getKartanPolku());
    }
    @Test
    public void getSaaennusteenPolkuAntaaOikeanPolunEkallaTasolla(){
        String polku="src/main/java/tienhoitopeli/saaennusteet/saaennuste1.txt";
        assertEquals(polku,this.peliKerranOhjaaja.getSaaennusteenPolku());
    }
    @Test
    public void getSaaennusteenPolkuAntaaOikeanPolunTokallaTasolla(){
        String polku="src/main/java/tienhoitopeli/saaennusteet/saaennuste2.txt";
        this.peliKerranOhjaaja.MeneSeuraavaanKenttaan();
        assertEquals(polku,this.peliKerranOhjaaja.getSaaennusteenPolku());
    }
    @Test
    public void getEnnatysListanPolkuAntaaOikeanPolunEkallaTasolla(){
        String polku="src/main/java/tienhoitopeli/ennatyslistat/ennatyslista1.txt";
        assertEquals(polku,this.peliKerranOhjaaja.getEnnatysListanPolku());
    }
    @Test
    public void getEnnatysListanPolkuAntaaOikeanPolunTokallaTasolla(){
        String polku="src/main/java/tienhoitopeli/ennatyslistat/ennatyslista2.txt";
        this.peliKerranOhjaaja.MeneSeuraavaanKenttaan();
        assertEquals(polku,this.peliKerranOhjaaja.getEnnatysListanPolku());
    }
    
    @Test
    public void AlustaTasoAlustaaLumikerroksenKoordinaateissaOikeinEkallaKerralla(){
        this.peliKerranOhjaaja.AlustaTaso();
        HashMap lumikerrosKoordinaateissa=this.peliKerranOhjaaja.getLumikerrosKoordinaateissa();
        ArrayList<Integer> koordinaatit=new ArrayList<Integer>();
        koordinaatit.add(0);
        koordinaatit.add(1);
        assertTrue((double) lumikerrosKoordinaateissa.get(koordinaatit)>-0.001 && (double) lumikerrosKoordinaateissa.get(koordinaatit)<0.001);
    }
    
    @Test
    public void AlustaTasoAlustaaLumikerroksenKoordinaateissaOikeinTokallaKerralla(){
        this.peliKerranOhjaaja.AlustaTaso();
        this.peliKerranOhjaaja.MeneSeuraavaanKenttaan();
        this.peliKerranOhjaaja.AlustaTaso();
        HashMap lumikerrosKoordinaateissa=this.peliKerranOhjaaja.getLumikerrosKoordinaateissa();
        ArrayList<Integer> koordinaatit=new ArrayList<Integer>();
        koordinaatit.add(0);
        koordinaatit.add(1);
        assertTrue((double) lumikerrosKoordinaateissa.get(koordinaatit)>-1.001 && (double) lumikerrosKoordinaateissa.get(koordinaatit)<1.001);
    }
    
    @Test
    public void AlustaTasoAlustaaRivitOikeinEkallaKerralla(){
        this.peliKerranOhjaaja.AlustaTaso();
        this.peliKerranOhjaaja.MeneSeuraavaanKenttaan();
        assertEquals(5,this.peliKerranOhjaaja.getRivit());
        
    }
    @Test
    public void AlustaTasoAlustaaRivitOikeinTokallaKerralla(){
        this.peliKerranOhjaaja.AlustaTaso();
        this.peliKerranOhjaaja.MeneSeuraavaanKenttaan();
        this.peliKerranOhjaaja.AlustaTaso();
        assertEquals(4,this.peliKerranOhjaaja.getRivit());
        
    }
    
     @Test
    public void AlustaTasoAlustaaSarakkeetOikeinEkallaKerralla(){
        this.peliKerranOhjaaja.AlustaTaso();
        this.peliKerranOhjaaja.MeneSeuraavaanKenttaan();
        assertEquals(5,this.peliKerranOhjaaja.getSarakkeet());
        
    }
    @Test
    public void AlustaTasoAlustaaSarakkeetOikeinTokallaKerralla(){
        this.peliKerranOhjaaja.AlustaTaso();
        this.peliKerranOhjaaja.MeneSeuraavaanKenttaan();
        this.peliKerranOhjaaja.AlustaTaso();
        assertEquals(10,this.peliKerranOhjaaja.getSarakkeet());
        
    }
    
    @Test
    public void AlustaTasoAlustaaEnnusteenOikeinEkallaKerralla(){
        this.peliKerranOhjaaja.AlustaTaso();
        this.peliKerranOhjaaja.MeneSeuraavaanKenttaan();
        String ennuste="Seuraavan 1 sekunnin aikana tiedossa 10 cm lunta.";
        assertEquals(ennuste,this.peliKerranOhjaaja.getEnnuste());
    }
    
    @Test
    public void AlustaTasoAlustaaEnnusteenOikeinTokallaKerralla(){
        this.peliKerranOhjaaja.AlustaTaso();
        this.peliKerranOhjaaja.MeneSeuraavaanKenttaan();
        this.peliKerranOhjaaja.AlustaTaso();
        String ennuste="Seuraavan 5 sekunnin aikana tiedossa 10 cm lunta.";
        assertEquals(ennuste,this.peliKerranOhjaaja.getEnnuste());
    }
}
