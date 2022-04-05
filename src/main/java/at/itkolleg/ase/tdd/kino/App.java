package at.itkolleg.ase.tdd.kino;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Dieses Beispiel stammt aus https://training.cherriz.de/cherriz-training/1.0.0/testen/junit5.html
 */
public class App 
{
    public static void main( String[] args )
    {
        //Saal anlegen
        Map<Character,Integer> map = new HashMap<>();
        map.put('A',10);
        map.put('B',10);
        map.put('C',15);
        KinoSaal ks = new KinoSaal("LadyX",map);

        //Platz prüfen
        System.out.println(ks.pruefePlatz('A',11));
        System.out.println(ks.pruefePlatz('A',10));
        System.out.println(ks.pruefePlatz('B',10));
        System.out.println(ks.pruefePlatz('C',14));

        //Vorstellung anlegen
        Zeitfenster nachmittag = Zeitfenster.NACHMITTAG;
        Vorstellung vs1 = new Vorstellung(ks,nachmittag, LocalDate.of(2022,03,23),"Harry Potter",12.99f);

        //Vorstellung einplanen
        KinoVerwaltung kv = new KinoVerwaltung();
        kv.einplanenVorstellung(vs1);
        System.out.println(kv.getVorstellungen());

        kv.kaufeTicket(vs1,'A',5,13);

        //Debug
        System.out.println(kv.getVorstellungen());



    }
}
