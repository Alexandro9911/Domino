package com.kspt.Alexandr;

import java.util.*;


class Graph {

      class Vertex{
        List<Vertex> neighbors = new ArrayList<Vertex>();
        Chip chip;

        public Vertex(Chip chip) {
            this.chip = chip;
        }

         public List<Vertex> getNeighbors() {
             return neighbors;
         }
     }

     List<Vertex> vertices = new ArrayList<Vertex>();
     List<Chip> verticesChip = new ArrayList<Chip>();
     List<Pair<Chip,Chip>> connectionsList = new ArrayList<Pair<Chip,Chip>>();  // каждой паре соед. соотв. свой swCase
     List<Integer> swCase = new ArrayList<Integer>();                           // их 6

    public void addVertex(Chip name) {
        Vertex vert = new Vertex(name);
        vertices.add(vert);
        verticesChip.add(name);
    }
    public void connect(Chip first, Chip second){
        if (second != null && first != null) {
            Vertex f = vertices.get(verticesChip.indexOf(first));
            Vertex s = vertices.get(verticesChip.indexOf(second));
            f.neighbors.add(s);
            s.neighbors.add(f);
        } else {
            throw new NullPointerException();
        }
    }

    public List<Chip> neighbors(Chip name){
        List<Chip> answ = new ArrayList<Chip>();
        Vertex vert = this.vertices.get(verticesChip.indexOf(name));
        List<Vertex> list = vertices.get(verticesChip.indexOf(name)).neighbors;
        for(Vertex v : list){
            if(v.neighbors.contains(vert)){
                answ.add(v.chip);
            }
        }
        return answ;
    }


    public void buildGraph(List<Chip> deck) {  // строит граф по правилам домино(соединяет если это возможно в домино)
        if(deck.size() == 0) throw new NullPointerException();
        Domino rools = new Domino();
        for (Chip ch : deck) {
            addVertex(ch);
        }
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                Chip chip1 = vertices.get(i).chip;
                Chip chip2 = vertices.get(j).chip;
                Pair<Chip,Chip> pair = new Pair<Chip, Chip>(chip1,chip2);
                int sw = rools.canGoinCase(chip1,chip2);
                switch (sw) {
                    case (1): { //one.second == another.first
                        if(!connectionsList.contains(pair)) {
                            swCase.add(sw);
                            connectionsList.add(pair);
                            connect(pair.first, pair.second);
                        }
                    }
                    case (2): { // one.first == another.second
                        if(!connectionsList.contains(pair)) {
                            swCase.add(sw);
                            connectionsList.add(pair);
                            connect(pair.first, pair.second);
                        }
                    }
                    case (3): { // one.first == another.first
                        if(!connectionsList.contains(pair)) {
                            swCase.add(sw);
                            connectionsList.add(pair);
                            connect(pair.first, pair.second);
                        }
                    }
                    case (4): { // one.second == another.second
                        if(!connectionsList.contains(pair)) {
                            swCase.add(sw);
                            connectionsList.add(pair);
                            connect(pair.first, pair.second);
                        }
                    }
                }
            }
        }
    }
    public List<Chip> longestSimplePath(Graph graph) {  //  поиск в глубину и вывод самой длинной цепочки
        List<Chip> answ = new ArrayList<Chip>();

        return answ;
    }
    private void dfs(Graph graph, Chip start){

    }

}
