package com.kspt.Alexandr;

import java.io.IOException;

import org.kohsuke.args4j.Argument;

public class DominoLauncher {

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private String inputFileName = null;

    @Argument(required = true, metaVar = "OutputName", usage = "Output file name")
    private String outputFileName = null;

    public static void main(String[] args)throws IOException {
        DominoLauncher work = new DominoLauncher();
        work.launch(args);
    }

    private  void launch(String[] args) throws IOException {
        String string ="";
        Domino domino = new Domino();
        Graph graph = new Graph();
        domino.setInputFileName(inputFileName);
        domino.setOutputFileName(outputFileName);
        domino.setDeck();
        graph.buildGraph(domino.deck);
        string = graph.getMax();
        domino.writeAnsw(string);
        System.exit(0);
    }
}
