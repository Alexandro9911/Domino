package com.kspt.Alexandr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Domino {

    List<Chip> deck = new ArrayList<Chip>(); // Колода(без ошибок пользователя)
    int caseConnected;

    public void setDeck(String inp) throws IOException {  // читает файл и сортирует, после этого данные записывает в колоду
        File file = getFile(inp);
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

    public File getFile(String string)throws IOException{
        if (string.length() == 0){
            throw new NoSuchFileException("Нулевая ссылка");
        }
        File file = new File(string);
        if(!file.exists()) {
            throw new FileNotFoundException("Файл не существует");
        }
        if(file.length() == 0){
            throw new NullPointerException("Пустой файл");
        }
        return file;
    }

    public boolean checkChip(Chip chip){  // проверка на правильность фишки
        if(chip.first <= 6 && chip.first >= 0 && chip.second <= 6 && chip.second >= 0){
            return true;
        } else{
            return false;
        }
    }


    public boolean containsDeck(Chip chip){ // содержит ли колода данную фишку уже
        boolean answ = false;
        for (Chip inDeck : deck){
                if(inDeck.equals(chip)){
                   return true;
                }
            }
        return answ;
    }

    public int canGoinCase(Chip one, Chip another) { // проверяет можно ли соединить фишки и выводит случай соединения
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
            answ = -1;
        }
        caseConnected = answ;
        return answ;
    }

    public void writeAnsw(String outputName, List<Chip> result)throws IOException {
        File answ = new File(outputName);
        FileWriter fw = new FileWriter(answ);

    }
}