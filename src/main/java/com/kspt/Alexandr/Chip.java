package com.kspt.Alexandr;


public class Chip {
    int first;
    int second;

    public Chip(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public  boolean equals(Chip ch2){
        boolean answ = false;
        if(this.first == ch2.first && this.second == ch2.second){
            answ =  true;
        }
        if(this.first == ch2.flipChip().first && this.second == ch2.flipChip().second){
            answ = true;
        }
        return answ;
    }

    public Chip flipChip() {
        Chip answ = new Chip(first, second);
        answ.first = this.second;
        answ.second = this.first;
        return answ;
    }

    public String chipToString(){
        String answ = "";
        answ += this.first + ":" + this.second;
        return answ;
    }
}
