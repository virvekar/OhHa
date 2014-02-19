/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.Sovelluslogiikka;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
public class EnnatysListanPitaja2Test {
    private String tiedostonNimi;
    private EnnatysListanPitaja ennatysListanPitaja;
    
    public EnnatysListanPitaja2Test() {
        this.tiedostonNimi="src/main/java/tienhoitopeli/ennatyslistat/ennatyslistaTesti3.txt";
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            FileWriter kirjoittaja = new FileWriter(this.tiedostonNimi);
            kirjoittaja.write("100 Eemeli\n200 Liisa\n300 Olli\n400 Ville\n500 Ville"
                    + "\n600 Eemeli\n700 Liisa\n800 Liisa\n900 Olli");
            kirjoittaja.close();
        }catch(IOException e) {
            System.out.println("ei onnistunut");
        }
        ennatysListanPitaja= new EnnatysListanPitaja(this.tiedostonNimi);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void EnnatysListanKokoEiYlitaKymmenta() {
         this.ennatysListanPitaja.LisaaEnnatys("Teemu", "100");
         this.ennatysListanPitaja.LisaaEnnatys("Jaakko", "1");
         ArrayList<String> lista=this.ennatysListanPitaja.getEnnatysLista();
         assertEquals(lista.size(),10); 
     }
     
     @Test
     public void EnnatysListaVikaOikeinJosKokoKymmenenJaEnnatysSuurempiKuinVika() {
         this.ennatysListanPitaja.LisaaEnnatys("Teemu", "1100");
         this.ennatysListanPitaja.LisaaEnnatys("Jaakko", "1200");
         ArrayList<String> lista=this.ennatysListanPitaja.getEnnatysLista();
         assertEquals(lista.get(9),"1100 Teemu"); 
     }
}
