package tienhoitopeli.Sovelluslogiikka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tienhoitopeli.Sovelluslogiikka.Kartta;
import tienhoitopeli.Sovelluslogiikka.Lumikerros;
import tienhoitopeli.Sovelluslogiikka.ReitinLukija;
import tienhoitopeli.Sovelluslogiikka.Saaennuste;

/**
 *
 * @author virvemaa
 */
public class ReitinLukijaTest {

    Kartta kartta;
    Saaennuste ennuste;
    Lumikerros lumikerros;
    ReitinLukija reitinLukija;
    Saa saa;

    public ReitinLukijaTest() {
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
        ennuste = new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste1.txt");
        ennuste.LueSaaennuste();
        saa= new Saa(ennuste);
        lumikerros = new Lumikerros(kartta);
        lumikerros.AlustaLumikerros();
        reitinLukija = new ReitinLukija(kartta, lumikerros);
        

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void NegatiivinenVaakariviEiRajojenSisalla() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(-1, 0);
        boolean vastaus = this.reitinLukija.TarkistaOnkoRajojenSisalla(piste);
        assertEquals(false, vastaus);
    }

    @Test
    public void NegatiivinenPystyriviEiRajojenSisalla() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(0, -1);
        boolean vastaus = this.reitinLukija.TarkistaOnkoRajojenSisalla(piste);
        assertEquals(false, vastaus);
    }

    @Test
    public void LiianSuuriVaakaRiviEiRajojenSisalla() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(5, 3);
        boolean vastaus = this.reitinLukija.TarkistaOnkoRajojenSisalla(piste);
        assertEquals(false, vastaus);
    }

    @Test
    public void LiianSuuriPystyRiviEiRajojenSisalla() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(3, 5);
        boolean vastaus = this.reitinLukija.TarkistaOnkoRajojenSisalla(piste);
        assertEquals(false, vastaus);
    }

    @Test
    public void OikeainPuoleisinRiviRajojenSisalla() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(4, 2);
        boolean vastaus = this.reitinLukija.TarkistaOnkoRajojenSisalla(piste);
        assertEquals(true, vastaus);
    }

    @Test
    public void AlinRiviRajojenSisalla() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(2, 4);
        boolean vastaus = this.reitinLukija.TarkistaOnkoRajojenSisalla(piste);
        assertEquals(true, vastaus);
    }

    @Test
    public void RakennusPisteEiKelpaaReitille() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(0, 0);
        boolean vastaus = this.reitinLukija.TarkistaOnkoPisteKadulla(piste);
        assertEquals(false, vastaus);
    }

    @Test
    public void KatuPisteKelpaaReitille() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(0, 1);
        boolean vastaus = this.reitinLukija.TarkistaOnkoPisteKadulla(piste);
        assertEquals(true, vastaus);
    }

    @Test
    public void KunnonPisteLisataanReitille() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(0, 1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        ArrayList<Integer> reittiPiste = reitti.get(0);
        assertEquals(piste, reittiPiste);
    }

    @Test
    public void HuonoaPistettaEiLisataReitille() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(0, 0);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();

        assertEquals(0, reitti.size());
    }

    @Test
    public void LuoUusiKoordinaattiToimii() {
        int x = 1;
        int y = 2;
        ArrayList<Integer> piste1 = this.reitinLukija.LuoUusiKoordinaattiPiste(x, y);
        ArrayList<Integer> piste2=this.LuoReittiKoordinaattiPiste(x, y);
        assertEquals(piste2, piste1);

    }

    @Test
    public void LiikutaYlosLiikuttaaYlos() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(1, 1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuYlos();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        ArrayList<Integer> uusiPiste = new ArrayList<Integer>();
        uusiPiste.add(0);
        uusiPiste.add(1);
        assertEquals(uusiPiste, reitti.get(1));
    }

    @Test
    public void LiikutaYlosEiLiikutaYlosJosYlapuolellaRakennus() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(2, 0);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuYlos();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();

        assertEquals(1, reitti.size());
    }

    @Test
    public void LiikutaYlosEiLiikutaYlosJosMeneeRajanYli() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(0, 1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuYlos();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();

        assertEquals(1, reitti.size());
    }

    @Test
    public void LiikutaAlasLiikuttaaAlas() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(1, 1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuAlas();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        ArrayList<Integer> uusiPiste = new ArrayList<Integer>();
        uusiPiste.add(2);
        uusiPiste.add(1);
        assertEquals(uusiPiste, reitti.get(1));
    }

    @Test
    public void LiikutaAlasEiLiikutaAlasJosAlapuolellaRakennus() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(2, 0);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuAlas();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();

        assertEquals(1, reitti.size());
    }

    @Test
    public void LiikutaAlasEiLiikutaAlasJosMeneeRajanYli() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(4, 1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuAlas();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();

        assertEquals(1, reitti.size());
    }

    @Test
    public void LiikutaVasemmalleLiikuttaaVasemmalle() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(2, 1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuVasemmalle();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        ArrayList<Integer> uusiPiste = new ArrayList<Integer>();
        uusiPiste.add(2);
        uusiPiste.add(0);
        assertEquals(uusiPiste, reitti.get(1));
    }

    @Test
    public void LiikutaVasemmalleEiLiikutaVasemmalleJosVasemmallapuolellaRakennus() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(1, 1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuVasemmalle();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();

        assertEquals(1, reitti.size());
    }

    @Test
    public void LiikutaVasemmalleEiLiikutaVasemmalleJosMeneeRajanYli() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(2, 0);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuVasemmalle();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();

        assertEquals(1, reitti.size());
    }

    @Test
    public void LiikutaOikealleLiikuttaaOikealle() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(2, 1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuOikealle();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        ArrayList<Integer> uusiPiste = new ArrayList<Integer>();
        uusiPiste.add(2);
        uusiPiste.add(2);
        assertEquals(uusiPiste, reitti.get(1));
    }

    @Test
    public void LiikutaOikealleEiLiikutaOikealleJosOikeallapuolellaRakennus() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(1, 1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuOikealle();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();

        assertEquals(1, reitti.size());
    }

    @Test
    public void LiikutaOikealleEiLiikutaOikealleJosMeneeRajanYli() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(2, 4);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuOikealle();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();

        assertEquals(1, reitti.size());
    }

    @Test
    public void SuoritaKomentoToimiiSyotteelleYlos() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(4, 1);
        boolean onnistuiko1 = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        boolean onnistuiko2 = this.reitinLukija.SuoritaKomentoJosSeLoytyy("i");
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        ArrayList<Integer> uusiPiste=this.LuoReittiKoordinaattiPiste(3, 1);
        assertEquals(uusiPiste, reitti.get(1));

    }

    @Test
    public void SuoritaKomentoToimiiSyotteelleAlas() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(3, 1);
        boolean onnistuiko1 = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        boolean onnistuiko2 = this.reitinLukija.SuoritaKomentoJosSeLoytyy("k");
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        ArrayList<Integer> uusiPiste=this.LuoReittiKoordinaattiPiste(4, 1);
        assertEquals(uusiPiste, reitti.get(1));

    }

    @Test
    public void SuoritaKomentoToimiiSyotteelleVasemmalle() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(4, 1);
        boolean onnistuiko1 = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        boolean onnistuiko2 = this.reitinLukija.SuoritaKomentoJosSeLoytyy("j");
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        ArrayList<Integer> uusiPiste=this.LuoReittiKoordinaattiPiste(4, 0);
        assertEquals(uusiPiste, reitti.get(1));

    }

    @Test
    public void SuoritaKomentoToimiiSyotteelleOikealle() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(4, 1);
        boolean onnistuiko1 = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        boolean onnistuiko2 = this.reitinLukija.SuoritaKomentoJosSeLoytyy("l");
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        ArrayList<Integer> uusiPiste=this.LuoReittiKoordinaattiPiste(4, 2);
        assertEquals(uusiPiste, reitti.get(1));
    }

    @Test
    public void SuoritaKomentoPalauttaaTrueSyotteellePysahdy() {
        boolean onnistuiko = this.reitinLukija.SuoritaKomentoJosSeLoytyy("p");
        assertEquals(true, onnistuiko);
    }

    @Test
    public void SuoritaKomentoEiLisaaPistettaSyotteellePysahdy() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(1, 1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.SuoritaKomentoJosSeLoytyy("p");
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        assertEquals(1, reitti.size());
    }

    @Test
    public void SuoritaKomentoEiLisaaPistettaTuntemattomalleSyotteelle() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(1, 1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.SuoritaKomentoJosSeLoytyy("e");
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        assertEquals(1, reitti.size());
    }

    @Test
    public void SuoritaKomentoEiLisaaPistettaTyhjalleSyotteelle() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(1, 1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.SuoritaKomentoJosSeLoytyy("");
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        assertEquals(1, reitti.size());
    }

    @Test
    public void SuoritaKomentoEiLisaaPistettaNullSyotteelle() {
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(1, 1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.SuoritaKomentoJosSeLoytyy(null);
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        assertEquals(1, reitti.size());
    }
    @Test
    public void KirjaaEnsimmainenPisteKirjaaPisteen(){
        boolean onnistuiko=this.reitinLukija.KirjaaEnsimmainenPiste("2", "2");
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(1, 1);
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        assertEquals(piste,reitti.get(0));
    }
     @Test
    public void KirjaaEnsimmainenPisteEiKirjaaRakennusPistetta(){
        boolean onnistuiko=this.reitinLukija.KirjaaEnsimmainenPiste("1", "1");
 
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        assertEquals(0,reitti.size());
    }
    @Test
    public void KirjaaEnsimmainenPisteEiKirjaaRajojenUlkopuolistaPistetta(){
        boolean onnistuiko=this.reitinLukija.KirjaaEnsimmainenPiste("-1", "-1");
 
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        assertEquals(0,reitti.size());
    }

    @Test
    public void KirjaaEnsimmainePisteEiToimiTyhjallaKomennolla() {
        boolean onnistuiko = this.reitinLukija.KirjaaEnsimmainenPiste("", "");
        assertEquals(false, onnistuiko);
    }
    @Test
    public void KirjaaEnsimmainePisteEiToimiNullKomennolla() {
        boolean onnistuiko = this.reitinLukija.KirjaaEnsimmainenPiste(null, null);
        assertEquals(false, onnistuiko);
    }
    @Test
    public void KirjaaEnsimmainePisteEiToimiEiNumeroKomennolla() {
        boolean onnistuiko = this.reitinLukija.KirjaaEnsimmainenPiste("numero", "numero");
        assertEquals(false, onnistuiko);
    }
    @Test
    public void KirjaaReittiKirjaaReittiPisteen(){
        this.reitinLukija.KirjaaEnsimmainenPiste("2", "2");
        boolean onnistuiko = this.reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        ArrayList<Integer> piste=this.LuoReittiKoordinaattiPiste(2, 1);
        assertEquals(piste, reitti.get(1));
    }
    @Test
    public void KirjaaReittiEiKirjaaHuonoaReittiPistettaHuonollaKomennolla(){
        this.reitinLukija.KirjaaEnsimmainenPiste("2", "2");
        boolean onnistuiko = this.reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("j");
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        assertEquals(1, reitti.size());
    }
    @Test
    public void KirjaaReittiPalauttaaFalseJosAnnetaanLopetusKomento(){
        this.reitinLukija.KirjaaEnsimmainenPiste("2", "2");
        boolean onnistuiko = this.reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("p");
        assertEquals(true,onnistuiko);
    }
    @Test
    public void KirjaaReittiEiKirjaaHuonoaReittiPistettaTyhjallaKomennolla(){
        this.reitinLukija.KirjaaEnsimmainenPiste("2", "2");
        boolean onnistuiko = this.reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("");
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        assertEquals(1, reitti.size());
    }
    @Test
    public void KirjaaReittiEiKirjaaHuonoaReittiPistettaNullKomennolla(){
        this.reitinLukija.KirjaaEnsimmainenPiste("2", "2");
        boolean onnistuiko = this.reitinLukija.KirjaaReittiPisteJosKomentoKelpaa(null);
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        assertEquals(1, reitti.size());
    }
    
    @Test
    public void LisaaAloitusAikaEiHyvaksyNegatiivista(){
        assertFalse(this.reitinLukija.LisaaAloitusAika("-4"));
    }
    
    @Test
    public void LisaaAloitusAikaEiHyvaksyNollaa(){
        assertFalse(this.reitinLukija.LisaaAloitusAika("0"));
    }
    
    @Test
    public void LisaaAloitusAikaHyvaksyyYkkosen(){
        assertTrue(this.reitinLukija.LisaaAloitusAika("1"));
    }
    @Test
    public void LisaaAloitusAikaLisaaHyvanAjan(){
        this.reitinLukija.LisaaAloitusAika("5");
        assertEquals(this.reitinLukija.getAloitusAika(),5);
    }
    @Test
    public void LisaaAloitusAikaEiLisaaHuonoaAikaa(){
        this.reitinLukija.LisaaAloitusAika("-5");
        assertEquals(this.reitinLukija.getAloitusAika(),1);
    }
    
    public ArrayList<Integer> LuoReittiKoordinaattiPiste(int rivi, int sarake) {
        ArrayList<Integer> koordinaatit = new ArrayList<Integer>();
        koordinaatit.add(rivi);
        koordinaatit.add(sarake);
        return koordinaatit;
    }
}
