package application.guessingame;

public class Rand {
    private static int rand;
    public static int lim;

    public static int getLim() {
        return lim;
    }
    public static int getRand() {
        return rand;
    }

    public static void setRand(int limit) {
        lim = limit;
        if (limit <= 1){

            rand = 1;
        } else {
            rand = 1;
            rand = (int) (Math.random()*limit+1)+1;
            System.out.println(rand);
        }


    }
}
