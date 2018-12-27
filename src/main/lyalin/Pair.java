package lyalin;

public class Pair<T, N> {
    Chip first;
    Chip second;

    public Pair(Chip first, Chip second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Pair other) {
        boolean answ = false;
        if(this.first == other.first && this.second == other.second){
            answ = true;
        }
        if(this.first == other.second && this.second == other.first){
            answ = true;
        }
        return answ;
    }
}
