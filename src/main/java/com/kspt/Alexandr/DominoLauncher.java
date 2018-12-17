package com.kspt.Alexandr;

import java.io.IOException;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

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
        Domino domino = new Domino();
        Graph graph = new Graph();
        domino.setDeck(inputFileName);
        graph.buildGraph(domino.deck);
        List<Chip> answ = graph.longestSimplePath(graph);
        domino.writeAnsw(outputFileName,answ);
    }
}
