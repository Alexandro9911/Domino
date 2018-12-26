package com.kspt.Alexandr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Domino {

    List<Chip> deck = new ArrayList<Chip>();
    int caseConnected;
    String inputFileName;
    String outputFileName;

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    /**
     * reading file
     * @throws IOException
     */
    public void setDeck() throws IOException {
        File file = null;
        try {
            try {
                file = getFile();
            } catch (FileNotFoundException exep) {
                System.err.println("File not found");
                System.exit(-3);
            }
        } catch (NullPointerException ex) {
            System.err.println("Empty file");
            System.exit(-1);
        }
        Scanner fileScan = new Scanner(file);
        while (fileScan.hasNextLine()) {
            String currStr = fileScan.nextLine();
            Chip ch = null;
            currStr = currStr.replaceAll(" *", "");
            if (currStr.matches("[0-6]:[0-6]") && currStr.length() == 3) {
                String[] forPair = currStr.split(":");
                Chip chip = new Chip(Integer.valueOf(forPair[0]), Integer.valueOf(forPair[1]));
                ch = chip;
            }
            if (ch != null) {
                if (!containsDeck(ch) && checkChip(ch)) {
                    deck.add(ch);
                }
            }
        }
    }

    /**
     *
     * @return File vith dech - data
     * @throws IOException
     */
    private File getFile() throws IOException {
        if (inputFileName.length() == 0) {
            throw new NullPointerException();
        }
        File file = new File(inputFileName);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        return file;
    }

    /**
     *
     * @param chip check chip by dimino rools
     * @return true if correct
     */
    private boolean checkChip(Chip chip) {  // проверка на правильность фишки
        if (chip.first <= 6 && chip.first >= 0 && chip.second <= 6 && chip.second >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param chip chip
     * @return true if deck contains this chip
     */
    private boolean containsDeck(Chip chip) {
        boolean answ = false;
        for (Chip inDeck : deck) {
            if (inDeck.equals(chip)) {
                return true;
            }
        }
        return answ;
    }

    /**
     *
     * @param one chip
     * @param another chip
     * @return int case of connection of this chips
     */
    int canGoinCase(Chip one, Chip another) {
        if (one == null || another == null) {
            return -1;
        }
        int answ = -1;
        if (one.second == another.first) {
            answ = 1;
        }
        if (one.first == another.second) {
            answ = 2;
        }
        if (one.first == another.first) {
            answ = 3;
        }
        if (one.second == another.second) {
            answ = 4;
        }
        if (one.equals(another)) {
            answ = 0;
        }
        caseConnected = answ;
        return answ;
    }

    /**
     *
     * @param answ write answer in file and check correct name of file
     * @throws IOException
     */
    public void writeAnsw(String answ) throws IOException {
        try {
            if (outputFileName.length() == 0) throw new NullPointerException();
        } catch (NullPointerException ex) {
            System.err.println("Empty outputfile name");
            System.exit(1);
        }
        try {
            if (outputFileName.matches("((\\?)|(\\*)|(:)|(\")|(<|>)|(\\|))"))
                throw new IllegalArgumentException();
        } catch (IllegalArgumentException ex) {
            System.err.println("Uncorrect outputfile name");
            System.exit(2);
        }
        File file = new File(outputFileName);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(answ);
            fw.close();
        } catch (FileNotFoundException ex){
            System.err.println("File not found");
            System.exit(3);
        }
    }
}
