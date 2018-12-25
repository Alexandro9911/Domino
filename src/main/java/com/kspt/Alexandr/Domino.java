package com.kspt.Alexandr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Domino {

    List<Chip> deck = new ArrayList<Chip>(); // Колода(без ошибок пользователя)
    int caseConnected;
    String inputFileName;
    String outputFileName;

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public void setDeck() throws IOException {  // читает файл и сортирует, после этого данные записывает в колоду
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


    private boolean checkChip(Chip chip) {  // проверка на правильность фишки
        if (chip.first <= 6 && chip.first >= 0 && chip.second <= 6 && chip.second >= 0) {
            return true;
        } else {
            return false;
        }
    }


    private boolean containsDeck(Chip chip) { // содержит ли колода данную фишку уже
        boolean answ = false;
        for (Chip inDeck : deck) {
            if (inDeck.equals(chip)) {
                return true;
            }
        }
        return answ;
    }

    int canGoinCase(Chip one, Chip another) { // проверяет можно ли соединить фишки и выводит случай соединения
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
