package at.itkolleg.ase.tdd.kino;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;


public class junitTestAdvanced {


    private Vorstellung vorstellung1;
    private Vorstellung vorstellung2;
    private Vorstellung vorstellung3;
    private KinoSaal ks;


    private KinoVerwaltung kinoVerwaltung;

    @BeforeEach
    void setUp() {

        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 10);
        map.put('B', 10);
        map.put('C', 15);
        map.put('D', 15);
        ks = new KinoSaal("LadyX", map);

        vorstellung1 = new Vorstellung(ks, Zeitfenster.ABEND, LocalDate.of(2022, 03, 23), "Harry Potter", 12.99f);
        vorstellung2 = new Vorstellung(ks, Zeitfenster.NACHT, LocalDate.of(2022, 03, 23), "Harry Potter", 12.99f);
        vorstellung3 = new Vorstellung(ks, Zeitfenster.NACHMITTAG, LocalDate.of(2022, 03, 23), "Harry Potter", 12.99f);


        kinoVerwaltung = new KinoVerwaltung();
    }

    @DisplayName("Vorstellung anlegen")
    @Test
    void vorstellungenAnlegen() {

        kinoVerwaltung.einplanenVorstellung(vorstellung1);
        kinoVerwaltung.einplanenVorstellung(vorstellung2);
        kinoVerwaltung.einplanenVorstellung(vorstellung3);
        assertAll(
                () -> assertEquals(vorstellung2, kinoVerwaltung.getVorstellungen().get(1), "Vorstellung 1 wurde nicht gespeichert"),
                () -> assertEquals(3, kinoVerwaltung.getVorstellungen().size(), "Es wurden nicht alle Vorstellungen gespeichert"),
                () -> assertEquals(vorstellung1, kinoVerwaltung.getVorstellungen().get(0), "Vorstellung 2 wurde nicht gespeichert")


        );
    }

    @DisplayName("Keine Doppel-Buchung von Vorstellungen")
    @Test
    void vorstellungDoppelBuchung() { // Using Assertions.assertThrows()

        //Siehe KinoVerwaltung Klasse
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            kinoVerwaltung.einplanenVorstellung(vorstellung1);
            kinoVerwaltung.einplanenVorstellung(vorstellung1);
        });

        // Message -> siehe Kinoverwaltung.einplanenVorstellung
        assertEquals(thrown.getMessage(), "Die Vorstellung ist bereits eingeplant");
    }

    @DisplayName("Ticket-Kauf-Test")
    @ParameterizedTest(name = "Reihe: {0}, Platz: {1}, Geld: {2}")
    @CsvSource({"A, 1, 13.0", "B, 2, 13.0", "C, 3, 13.0", "D, 4, 13.0"})
    void kaufeTicket(char reihe, int platz, float geld) {


        assertTrue(vorstellung1.kaufeTicket(reihe, platz, geld) instanceof Ticket, "Fehler bei der Ticket erzeugung");


    }

    /*@DisplayName("Testfactory Test")
    @TestFactory
    public Stream<DynamicTest> ticketTestfactory() {
        kinoVerwaltung.einplanenVorstellung(vorstellung1);


        Random random = new Random();
        char reihe = (char) (random.nextInt(26) + 'A');
        int platz = random.nextInt(10);
        float geld = random.nextFloat();

        try {
            vorstellung1.kaufeTicket(
                    reihe,
                    platz,
                    geld);
        } catch (IllegalArgumentException e) {
            boolean errGeld = "Nicht ausreichend Geld.".equals(e.getMessage());
            boolean errPlatz = e.getMessage()
                    .contains("existiert nicht");
            assertTrue(errGeld || errPlatz);


        }


    }*/
}













