package lyalin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Данный класс проверяет правильность введенных данных, а так же содержит методы, которые реализуют правила домино,
 * в рамках задачи.
 *
 */
public class Domino {

    List<Chip> deck = new ArrayList<Chip>();
    int caseConnected;
    String inputFileName;
    String outputFileName;
    String unusedChips;

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    /**
     * reading file
     *
     */
    public void setDeck() {
        try {
            File file = null;
            try {
                file = getFile();
            } catch (NullPointerException ex) {
                System.err.println("Empty file name");
                System.exit(-1);
            }
            Scanner fileScan = new Scanner(file);
            while (fileScan.hasNextLine()) {
                String currStr = fileScan.nextLine();
                Chip ch = null;
                 currStr = currStr.replaceAll(" *", "");
                 try {
                     if(!currStr.matches("([0-6]:{1}[0-6])")){
                         throw new IllegalArgumentException();
                     }
                 } catch (IllegalArgumentException ex){
                     System.err.println("Illegal argument: " + currStr);
                     System.exit(-6);
                 }

                if (currStr.matches("([0-6]:{1}[0-6])") && currStr.length() == 3) {
                    String[] forPair = currStr.split(":");
                    Chip chip = new Chip(Integer.valueOf(forPair[0]), Integer.valueOf(forPair[1]));
                    ch = chip;
                }
                if (ch != null) {
                    try {
                        if (!containsDeck(ch) && checkChip(ch)) {
                            deck.add(ch);
                        } else {
                            throw  new IllegalArgumentException();
                        }
                    }  catch (IllegalArgumentException exept){
                        System.err.println("Chip already in deck:" +" "+ch.chipToString());
                        System.exit(4);
                    }
                }
            }
        } catch (IOException ex) {
            System.err.println("IOExeption");
            System.exit(-4);
        }
    }

    /**
     * @return File vith dech - data
     *
     */
    private File getFile(){
        File file = new File(inputFileName);
        try {
            if (inputFileName.length() == 0) {
                throw new NullPointerException();
            }
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
        } catch (IOException exp){
            System.err.println("File not found");
            System.exit(-3);
        }
        return file;
    }

    /**
     * @param chip check chip by dimino rools
     * @return true if correct
     */
    private boolean checkChip(Chip chip) {
        if (chip.first <= 6 && chip.first >= 0 && chip.second <= 6 && chip.second >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param chip chip
     * @return true if deck contains this chip
     */
     boolean containsDeck(Chip chip) {
        boolean answ = false;
        for (Chip inDeck : deck) {
            if (inDeck.equals(chip)) {
                return true;
            }
        }
        return answ;
    }

    /**
     * @param one     chip
     * @param another chip
     * @return int case of connection of this chips
     *   Выводит значение случая по которому возможно соединить две фишки.
     *    -1 - невозможно соединить
     *    1 второе число 1 фишки к первому числу 2 фишки
     *    2 первое число 1 фишки к 2 числу 2 фишки
     *    3 первое число 1 фишки к первому числу 2 фишки
     *    4 второе число 1 фишки к второму числу 2 фишки
     *    0 если фишки равны
     *
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
     * @param answ write answer in file and check correct name of file
     *
     */
    public void writeAnsw(String answ,String unused) {
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
            if (deck.size() == 1) {
                fw.write(deck.get(0).chipToString());
            }
            fw.write("Answer: "+ answ+ "\n");
            fw.write("Not used chips: "+ unused);
            fw.close();
        } catch (IOException ex) {
            System.err.println("File not found");
            System.exit(3);
        }

    }
}
