package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class KinoSaalTest {



    private KinoSaal kinosaalOriginal;
    private KinoSaal ksMock;

    @BeforeEach
    void setUp() {
        //Saal anlegen
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 10);
        map.put('B', 10);
        map.put('C', 15);
        kinosaalOriginal = new KinoSaal("IDBM", map);

        ksMock = Mockito.mock(KinoSaal.class);




    }

    @DisplayName("Namen des Saals auslesen")
    @Test
    void getName() {

        assertAll(
                ()->Mockito.when(ksMock.getName()).thenReturn("KS1"),
                ()->Mockito.verify(ksMock).getName(),
                ()->assertEquals("KS1",ksMock.getName()),
                ()->assertEquals("IDBM", kinosaalOriginal.getName())
        );




    }

    @Test
    void pruefePlatz() {

        assertTrue(kinosaalOriginal.pruefePlatz('A',10));
        assertFalse(kinosaalOriginal.pruefePlatz('A',15));

    }

    @Test
    void testEquals() {
        assertTrue(kinosaalOriginal.equals(kinosaalOriginal)); //Ist das gegebene Objekt eine Instanz von Kinosaal?
        assertFalse(kinosaalOriginal.equals(15)); //

    }


}