package lyalin;

import org.kohsuke.args4j.Argument;

public class DominoLauncher {

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private String inputFileName = null;

    @Argument(required = true, metaVar = "OutputName", usage = "Output file name")
    private String outputFileName = null;

    public static void main(String[] args) {
        DominoLauncher work = new DominoLauncher();
        work.launch(args);
    }

    /**
     *
     * @param args names of input- output files
     *
     */
    private  void launch(String[] args) {
        String string ="";
        Domino domino = new Domino();
        Graph graph = new Graph();
        domino.setInputFileName(inputFileName);
        domino.setOutputFileName(outputFileName);
        domino.setDeck();
        graph.buildGraph(domino.deck);
        string = graph.getMax(domino);
        domino.writeAnsw(string,domino.unusedChips);
        System.exit(0);
    }
}
