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
public class EnnatysListanPitajaTest {
    
    private String tiedostonNimi;
    private EnnatysListanPitaja ennatysListanPitaja;
    
    public EnnatysListanPitajaTest() {
        this.tiedostonNimi="src/main/java/tienhoitopeli/ennatyslistat/ennatyslistaTesti.txt";
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
            kirjoittaja.write("300 Eemeli\n500 Liisa\n1000 Olli");
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
     public void LisaaEnnatysLisaaPienimmanEnsimmaiseksi() {
         this.ennatysListanPitaja.LisaaEnnatys("Teemu", "100");
         ArrayList<String> lista=this.ennatysListanPitaja.getEnnatysLista();
         assertEquals(lista.get(0),"100 Teemu");
     }
     @Test
     public void LisaaEnnatysLisaaSuurimmanViimeiseksi() {
         this.ennatysListanPitaja.LisaaEnnatys("Teemu", "1100");
         ArrayList<String> lista=this.ennatysListanPitaja.getEnnatysLista();
         assertEquals(lista.get(3),"1100 Teemu");
     }
     @Test
     public void LisaaEnnatysLisaaOikeinValiin() {
         this.ennatysListanPitaja.LisaaEnnatys("Teemu", "800");
         ArrayList<String> lista=this.ennatysListanPitaja.getEnnatysLista();
         assertEquals(lista.get(2),"800 Teemu");
     }
     
     @Test
     public void LisaaEnnatysLisaaOikeinTyhjaanTiedostoon(){
         String tyhjanTiedostonNimi="src/main/java/tienhoitopeli/ennatyslistat/ennatyslistaTesti2.txt";
         try {
            FileWriter kirjoittaja = new FileWriter(tyhjanTiedostonNimi);
            kirjoittaja.write("");
            kirjoittaja.close();
        }catch(IOException e) {
            System.out.println("ei onnistunut");
        }
         ennatysListanPitaja= new EnnatysListanPitaja(tyhjanTiedostonNimi);
         this.ennatysListanPitaja.LisaaEnnatys("Teemu", "800");
         ArrayList<String> lista=this.ennatysListanPitaja.getEnnatysLista();
         assertEquals(lista.get(0),"800 Teemu");
     }
}
