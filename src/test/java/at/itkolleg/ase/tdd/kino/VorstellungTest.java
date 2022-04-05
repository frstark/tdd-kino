package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VorstellungTest {



    private KinoSaal ks;


    private AutoCloseable closable;
    private Vorstellung vorstellung;






    @BeforeEach
    void setUp() {
        //Saal anlegen
        Map<Character,Integer> map = new HashMap<>();
        map.put('A',10);
        map.put('B',10);
        map.put('C',15);
        map.put('D',15);
        ks = new KinoSaal("LadyX",map);
        //Mit dem Mock können wir die Methoden mit einem gemockten Saal testen.
        //ksMock = mock(KinoSaal.class);

         vorstellung = new Vorstellung(ks,Zeitfenster.ABEND, LocalDate.of(2022,03,23),"Harry Potter",12.99f);
    }
   /* @AfterEach
    void cleanup() throws Exception {
        closable.close();
    }*/



    @Test
    void getFilm() {
        assertEquals("Harry Potter", vorstellung.getFilm(),"Der Name wird nicht richtig ausgelesen!");
    }

    @Test
    void getSaal() {
        assertEquals(ks, vorstellung.getSaal(),"Der Saal wird nicht richtig ausgelesen!");



    }

    @Test
    void getZeitfenster() {
        assertEquals(Zeitfenster.ABEND,vorstellung.getZeitfenster(),"Die Tageszeit wird nicht richtig ausgelesen!");
    }

    @Test
    void getDatum() {
        assertEquals(LocalDate.of(2022,03,23),vorstellung.getDatum(),"Das Datum wird nicht richtig ausgelesen!");

    }


    @DisplayName("Ticket-Kauf-Test")
    @ParameterizedTest(name = "Reihe: {0}, Platz: {1}, Geld: {2}")
    @CsvSource({"A, 1, 13.0", "B, 2, 13.0", "C, 3, 13.0", "D, 4, 13.0"})
    void kaufeTicket(char reihe, int platz, float geld) {



    assertTrue(vorstellung.kaufeTicket(reihe,platz,geld) instanceof Ticket, "Fehler bei der Ticket erzeugung");




    }



    @Test
    void testEquals() {
        assertTrue(vorstellung.equals(vorstellung));
    }
}