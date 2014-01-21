package tienhoitopeliid.tienhoitopeli;

import java.util.ArrayList;
import java.util.HashMap;
import tienhoitopeliid.tienhoitopeli.Sovelluslogiikka.Kartta;
import tienhoitopeliid.tienhoitopeli.Sovelluslogiikka.Lumikerros;
import tienhoitopeliid.tienhoitopeli.Sovelluslogiikka.Saaennuste;

/**
 * Hello world!
 *
 */
public class Kayttoliittyma 
{
    public static void main( String[] args )
    {
        
        Kartta koeKartta= new Kartta();
        koeKartta.LueKartta();
        koeKartta.TulostaKartta();
        koeKartta.TulostaSelite();
        
        Saaennuste ennuste=new Saaennuste();
        ennuste.LueSaaennuste();
        ennuste.TulostaSaaennuste();
        
        Lumikerros lumikerros=new Lumikerros(koeKartta,ennuste);
        lumikerros.AlustaLumikerros();
        lumikerros.lisaaLuntaYhdenSekunninSateenVerran();
        lumikerros.TulostaLumikerros();
        

    }
}
