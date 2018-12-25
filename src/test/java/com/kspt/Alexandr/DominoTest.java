package com.kspt.Alexandr;

import org.junit.Test;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class DominoTest {

    @Test
    public void canGoInCase(){
        Domino domino = new Domino();
        Chip ch1 = new Chip(4,3);
        Chip ch2 = new Chip(3,5);
        Chip ch3 = new Chip(4,5);
        Chip ch4 = new Chip(6,6);
        Chip ch5 = new Chip(6,0);
        assertEquals(1,domino.canGoinCase(ch1,ch2));
        assertEquals(2,domino.canGoinCase(ch2,ch1));
        assertEquals(3,domino.canGoinCase(ch1,ch3));
        assertEquals(4,domino.canGoinCase(ch3,ch2));
        assertEquals(-1,domino.canGoinCase(ch1,ch4));
        assertEquals(3,domino.canGoinCase(ch4,ch5));
        assertEquals(3,domino.canGoinCase(ch5,ch4));
        assertEquals(0,domino.canGoinCase(ch1,ch1.flipChip()));
        assertEquals(0,domino.canGoinCase(ch1,ch1));
    }


    @Test
    public void setDeck() throws IOException {
        Domino domino = new Domino();
        String string = "C:\\Users\\LEGION\\IdeaProjects\\Domino\\src\\test\\testRecourses\\inp1.txt";
        domino.setInputFileName(string);
        domino.setDeck();
        List<Chip> expected = new ArrayList<Chip>();
        expected.add(new Chip(0,1));
        expected.add(new Chip(0,2));
        expected.add(new Chip(0,3));
        expected.add(new Chip(0,0));
        expected.add(new Chip(5,5));
        expected.add(new Chip(1,5));
        assertEquals(expected.size(),domino.deck.size());
        if(expected.size()==domino.deck.size()) {
            for (int i = 0; i < domino.deck.size(); i++) {
                boolean check = expected.get(i).equals(domino.deck.get(i));
                assertTrue(check);
            }
        }
    }
}