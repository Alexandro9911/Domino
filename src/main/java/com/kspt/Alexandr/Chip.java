package com.kspt.Alexandr;


class Chip {

    int first;
    int second;
    boolean flipped;

    Chip(int first, int second) {
        this.first = first;
        this.second = second;
    }

    boolean equals(Chip ch2) {
        boolean answ = false;
        if (this.first == ch2.first && this.second == ch2.second) {
            answ = true;
        }
        if (this.first == ch2.flipChip().first && this.second == ch2.flipChip().second) {
            answ = true;
        }
        return answ;
    }

    Chip flipChip() {
        Chip answ = new Chip(first, second);
        answ.first = this.second;
        answ.second = this.first;
        return answ;
    }

    String chipToString() {
        String answ = "";
        answ += this.first + ":" + this.second;
        return answ;
    }

    boolean isDoople(Chip chip) {
        if (chip.first == chip.second) {
            return true;
        }
        return false;
    }
}
