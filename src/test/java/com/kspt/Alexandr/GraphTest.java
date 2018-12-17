package com.kspt.Alexandr;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void addVertex() {
        Graph graph = new Graph();
        List<Graph.Vertex> listVert = graph.vertices;
        List<Chip> listName = graph.verticesChip;
        Chip chip = new Chip(0, 0);
        graph.addVertex(chip);
        assertEquals(1, listVert.size());
        assertEquals(1, listName.size());
        assertEquals(chip, listName.get(0));
    }

    @Test
    public void connect() {
        Graph graph = new Graph();
        List<Graph.Vertex> listVert = graph.vertices;
        List<Chip> listName = graph.verticesChip;
        Chip chipA = new Chip(2, 0);
        Chip chipB = new Chip(0, 1);
        graph.addVertex(chipA);
        graph.addVertex(chipB);
        assertEquals(2, listVert.size());
        assertEquals(2, listName.size());
        graph.connect(chipA, chipB);
        List<Graph.Vertex> neighbA = graph.vertices.get(0).neighbors;
        List<Graph.Vertex> neighbB = graph.vertices.get(1).neighbors;
        assertEquals(1, neighbA.size());
        assertEquals(chipB, neighbA.get(0).chip);
    }

    @Test
    public void build() {
        Graph graph = new Graph();
        Chip ch0 = new Chip(0, 1);
        Chip ch1 = new Chip(1, 2);
        Chip ch2 = new Chip(0, 5);
        Chip ch3 = new Chip(3, 0);
        Chip ch4 = new Chip(3, 4);
        List<Chip> calloda = new ArrayList<Chip>();
        calloda.add(ch0);
        calloda.add(ch1);
        calloda.add(ch2);
        calloda.add(ch3);
        calloda.add(ch4);
        graph.buildGraph(calloda);
        List<Chip> neighb = graph.neighbors(ch0);
        for (Chip ch : neighb) {
            System.out.println(ch.chipToString());
        }
        assertEquals(3, neighb.size());
    }

    @Test
    public void build2() {
        Graph graph = new Graph();
        Chip ch0 = new Chip(0, 1);
        Chip ch1 = new Chip(1, 3);
        Chip ch2 = new Chip(4, 5);
        Chip ch3 = new Chip(3, 3);
        Chip ch4 = new Chip(3, 4);
        Chip ch5 = new Chip(0, 4);
        Chip ch6 = new Chip(4, 6);
        Chip ch7 = new Chip(0, 0);
        List<Chip> calloda = new ArrayList<Chip>();
        calloda.add(ch0);
        calloda.add(ch1);
        calloda.add(ch2);
        calloda.add(ch3);
        calloda.add(ch4);
        calloda.add(ch5);
        calloda.add(ch6);
        calloda.add(ch7);
        graph.buildGraph(calloda);
        List<Integer> nei = new ArrayList<Integer>();
        nei.add(3);
        nei.add(3);
        nei.add(3);
        nei.add(2);
        nei.add(5);
        nei.add(5);
        nei.add(3);
        nei.add(2);
        for (int i = 0; i < nei.size(); i++) {
            List<Chip> neighb = graph.neighbors(calloda.get(i));
            int expected = nei.get(i);
            assertEquals(expected, neighb.size());
        }
    }

    @Test
    public void build3() {
        Graph graph = new Graph();
        List<Chip> calloda = new ArrayList<Chip>();
        try {
            graph.buildGraph(calloda);
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void longestWay() {  // пока не работает
        Graph graph = new Graph();
        Chip ch0 = new Chip(0, 1);
        Chip ch1 = new Chip(1, 3);
        Chip ch2 = new Chip(4, 5);
        Chip ch3 = new Chip(3, 3);
        Chip ch4 = new Chip(3, 4);
        Chip ch5 = new Chip(0, 4);
        Chip ch6 = new Chip(4, 6);
        Chip ch7 = new Chip(0, 0);
        List<Chip> calloda = new ArrayList<Chip>();
        calloda.add(ch0);
        calloda.add(ch1);
        calloda.add(ch2.flipChip());
        calloda.add(ch3);
        calloda.add(ch4);
        calloda.add(ch5.flipChip());
        calloda.add(ch6);
        calloda.add(ch7);
        graph.buildGraph(calloda);
        List<Chip> answ = new ArrayList<Chip>();
        answ.add(ch2.flipChip());
        answ.add(ch5.flipChip());
        answ.add(ch7);
        answ.add(ch0);
        answ.add(ch1);
        answ.add(ch3);
        answ.add(ch4);
        answ.add(ch6);
        List<Graph.Vertex> test = new ArrayList<Graph.Vertex>();
       // List<Chip> actual = graph.longestSimplePath(graph);
        String expected = "";
        String toCompare = "";
        for(Chip ch : answ){
            expected += ch.chipToString() + " ";
        }
        for(Graph.Vertex vert : test){
            toCompare += vert.chip.chipToString() + " ";
        }
        assertEquals(expected,toCompare);
    }
}