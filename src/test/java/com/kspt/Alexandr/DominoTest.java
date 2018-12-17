package com.kspt.Alexandr;

import org.junit.Test;

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
        assertEquals(-1,domino.canGoinCase(ch1,ch1.flipChip()));
        assertEquals(-1,domino.canGoinCase(ch1,ch1));
    }


    @Test
    public void setDeck() {
    }

    @Test
    public void getFile() {
    }

    @Test
    public void checkChip() {
        Domino domino = new Domino();
        Chip ch1 = new Chip(0,5);
        Chip ch2 = new Chip(8,8);
        Chip ch3 = new Chip(0,8);
        assertTrue(domino.checkChip(ch1));
        assertFalse(domino.checkChip(ch2));
        assertFalse(domino.checkChip(ch3));
    }

    @Test
    public void containsDeck() {
        Domino domino = new Domino();
        Chip ch1 = new Chip(0,5);
        Chip ch2 = new Chip(0,4);
        Chip ch3 = new Chip(0,3);
        Chip ch4 = new Chip(0,2);
        Chip ch5 = new Chip(0,1);
        Chip ch6 = new Chip(0,0);
         Chip ch7 = new Chip(0,0);
         Chip ch8 = new Chip(0,8);
         Chip ch9 = new Chip(0,10);
        Chip ch10 = new Chip(1,5);
        List<Chip> inp = new ArrayList<Chip>();
        inp.add(ch1);
        inp.add(ch2);
        inp.add(ch3);
        inp.add(ch4);
        inp.add(ch5);
        inp.add(ch6);
        inp.add(ch7);
        inp.add(ch8);
        inp.add(ch9);
        inp.add(ch10);
        
    }
}