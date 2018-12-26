package com.kspt.Alexandr;

import java.util.*;

class Graph {

    class Vertex {
        Map<Chip, Vertex> neighbors = new HashMap<Chip, Vertex>();
        Chip chip;

        public Vertex(Chip chip) {
            this.chip = chip;
        }

        boolean used = false;

        /**
         *
         * @return all neighbours of this vertex
         */

        public List<Vertex> getNeighbors() {
            List<Vertex> answ = new ArrayList<Vertex>();
            Collection<Vertex> coll = neighbors.values();
            answ.addAll(coll);
            return answ;
        }

        /**
         *  make this vertex in chain used
         */
        public void makeUsed() {
            this.used = true;
        }
    }

    List<Vertex> vertices = new ArrayList<Vertex>();
    List<Chip> verticesChip = new ArrayList<Chip>();
    List<Pair<Chip, Chip>> connectionsList = new ArrayList<Pair<Chip, Chip>>();  // каждой паре соед. соотв. свой swCase
    List<Integer> swCase = new ArrayList<Integer>();

    String maximalWay = "";

    /**
     *  add vertex in graph
     * @param name value of this chip
     */
    public void addVertex(Chip name) {
        Vertex vert = new Vertex(name);
        vertices.add(vert);
        verticesChip.add(name);
    }

    /**
     *  connecting chip1 with chip2
     * @param first chip
     * @param second chip
     */

    public void connect(Chip first, Chip second) {
        if (second != null && first != null) {
            Vertex f = vertices.get(verticesChip.indexOf(first));
            Vertex s = vertices.get(verticesChip.indexOf(second));
            f.neighbors.put(s.chip, s);
            s.neighbors.put(f.chip, f);
        } else {
            throw new NullPointerException();
        }
    }

    /**
     *
     * @param name value of Chip
     * @return all neighbors of this chip
     */

    public List<Chip> neighbors(Chip name) {
        List<Chip> answ = new ArrayList<Chip>();
        Vertex vert = this.vertices.get(verticesChip.indexOf(name));
        List<Vertex> list = vert.getNeighbors();
        for (Vertex v : list) {
            answ.add(v.chip);
        }
        return answ;
    }

    /**
     * Building graph by roold of domino
     * @param deck all chips which user wanted to add
     */
    public void buildGraph(List<Chip> deck) {
        Domino rools = new Domino();
        try {
            if (deck.size() == 0) throw new NullPointerException();
        } catch (NullPointerException ex){
            System.err.println("Empty file");
            System.exit(-2);
        }
        for (Chip ch : deck) {
            addVertex(ch);
        }
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                Chip chip1 = vertices.get(i).chip;
                Chip chip2 = vertices.get(j).chip;
                Pair<Chip, Chip> pair = new Pair<Chip, Chip>(chip1, chip2);
                int sw = rools.canGoinCase(chip1, chip2);
                switch (sw) {
                    case (1): { //one.second == another.first
                        if (!connectionsList.contains(pair)) {
                            swCase.add(sw);
                            connectionsList.add(pair);
                            connect(pair.first, pair.second);
                        }
                    }
                    case (2): { // one.first == another.second
                        if (!connectionsList.contains(pair)) {
                            swCase.add(sw);
                            connectionsList.add(pair);
                            connect(pair.first, pair.second);
                        }
                    }
                    case (3): { // one.first == another.first
                        if (!connectionsList.contains(pair)) {
                            swCase.add(sw);
                            connectionsList.add(pair);
                            connect(pair.first, pair.second);
                        }
                    }
                    case (4): { // one.second == another.second
                        if (!connectionsList.contains(pair)) {
                            swCase.add(sw);
                            connectionsList.add(pair);
                            connect(pair.first, pair.second);
                        }
                    }
                }
            }
        }
    }

    /**
     * make all vertices unused
     */
    private void makeAllunUsed() {
        for (Vertex vert : vertices) {
            vert.used = false;
        }
    }

    /**
     * make all chips of graph unflipped
     */
    private void makeAllunFlipped() {
        for (Vertex vert : vertices) {
            vert.chip.flipped = false;
        }
    }

    /**
     *
     * @return all chains which possible in graph
     */
    List<List<Chip>> getAll() {
        List<List<Chip>> answ = new ArrayList<List<Chip>>();
        for (Vertex vertex : vertices) {
            List<List<Chip>> part = getAll(vertex);
            makeAllunUsed();
            for (List<Chip> addition : part) {
                answ.add(addition);
            }
        }
        return answ;
    }

    /**
     *
     * @return all chains which possible with start in vertex
     */
    private List<List<Chip>> getAll(Vertex vertex) {
        vertex.makeUsed();
        List<List<Chip>> answ = new ArrayList<List<Chip>>();
        for (Map.Entry<Chip, Vertex> child : vertex.neighbors.entrySet()) {
            Chip currChip = child.getKey();
            Vertex nextVert = child.getValue();
            List<List<Chip>> part = new ArrayList<List<Chip>>();
            if (!nextVert.used) {
                nextVert.makeUsed();
                final List<List<Chip>> partialAnsw = getAll(nextVert);
                for (List<Chip> ans : partialAnsw) {
                    List<Chip> addition = new ArrayList<Chip>();
                    addition.add(vertex.chip);
                    addition.addAll(ans);
                    part.add(addition);
                }
                answ.addAll(part);
                if (partialAnsw.size() == 0) {
                    List<Chip> addition = new ArrayList<Chip>();
                    addition.add(vertex.chip);
                    addition.add(currChip);
                    answ.add(addition);
                }
            }
        }
        return answ;
    }

    /**
     *
     * @return the lengest way in graph which builded by dominoe rools
     */
    public String getMax() {
        List<List<Chip>> lines = getAll();
        for (List<Chip> line : lines) {
            String str = "";
            List<Chip> correct = flipChain(line);
            makeAllunFlipped();
            for (Chip chip : correct) {
                str += chip.chipToString() + " ";
            }
            if (str.length() > maximalWay.length()) {
                maximalWay = str;
            }
        }
        return maximalWay;
    }

    /**
     *
     * @param line chain of domino chips
     * @return correct line of this chips
     */

    private List<Chip> flipChain(List<Chip> line) {
        Domino rools = new Domino();
        List<Chip> answ = new ArrayList<Chip>();
        List<Chip> partial = new ArrayList<Chip>();
        for (int i = 0; i < line.size() - 1; i++) {
            Chip curr = line.get(i);
            Chip next = line.get(i + 1);
            int swCase = rools.canGoinCase(curr, next);
            if (swCase == 1) {
                if (curr.flipped) {
                    partial.add(curr);
                    break;
                } else {
                    next.flipped = false;
                    partial.add(curr);
                }
            }
            if (swCase == 2) {
                if (i > 0 && !curr.flipped && !curr.isDoople(curr)) {
                    partial.add(curr);
                    break;
                } else {
                    curr.flipped = true;
                    next.flipped = true;
                    partial.add(curr);
                }
            }
            if (swCase == 3) {
                if (i > 0 && !curr.flipped && !curr.isDoople(curr)) {
                    partial.add(curr);
                    break;
                } else {
                    curr.flipped = true;
                    next.flipped = false;
                    partial.add(curr);
                }
            }
            if (swCase == 4) {
                if (curr.flipped) {
                    partial.add(curr);
                    break;
                } else {
                    next.flipped = true;
                    partial.add(curr);
                }
            }
            if (swCase == -1) {
                partial.add(curr);
                break;
            }
            if (i == line.size() - 2) {
                partial.add(next);
            }
        }
        for (Chip chip : partial) {
            if (chip.flipped) {
                answ.add(chip.flipChip());
            } else {
                answ.add(chip);
            }
        }
        return answ;
    }
}