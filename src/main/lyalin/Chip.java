package lyalin;

class Chip {

    int first;
    int second;
    boolean flipped; //

    Chip(int first, int second) {
        this.first = first;
        this.second = second;
    }

    /**
     *
     * @param ch2 compare this chip and chip ch2
     * @return true if this == ch2
     */
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

    /**
     * Flipping chip
     * @return chip
     */
    Chip flipChip() {
        Chip answ = new Chip(first, second);
        answ.first = this.second;
        answ.second = this.first;
        return answ;
    }

    /**
     * Cjnvert chip numbers to stringFormat
     * @return string chip.first + ":" + chip second
     */
    String chipToString() {
        String answ = "";
        answ += this.first + ":" + this.second;
        return answ;
    }

    /**
     *
     * @param chip this chip
     * @return if this chip is doople return true (when first == second)
     */

    boolean isDoople(Chip chip) {
        if (chip.first == chip.second) {
            return true;
        }
        return false;
    }
}
