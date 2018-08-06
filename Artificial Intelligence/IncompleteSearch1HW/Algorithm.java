import java.util.Random;


public class Algorithm {

    /**
     * @param args
     */
    static Random myRand = new Random();

    public static void main(String[] args) {

        NQueen init = generateRandomly(5);

        System.out.println(init);
        System.out.println(init.getQuality());


        System.out.println(Gibs(1000, 4));
        System.out.println("greedy");
        System.out.println(GreedyHillClimbing(1000, 5));

    }
        /*algos*/

    public static NQueen Gibs(int maxIter, int n) {
        NQueen s = generateRandomly(n);            //gibs
        if (s.getQuality() == 0) return s;
        int iter = 0;
        while (iter++ < maxIter) {
            NQueen sprime = generateRandomNeighbor(s);
            if (sprime.getQuality() == 0) {
                System.out.println(iter);            //gibs
                return sprime;
            }
            s = sprime;
        }
        return null;
    }

    public static NQueen RandomizedHillClimbing(int maxIter, int n) {

        NQueen s = generateRandomly(n);
        if (s.getQuality() == 0) return s;                // rhc

        int iter = 0;
        while (iter++ < maxIter) {
            NQueen sprime = generateRandomNeighbor(s);
            if (sprime.getQuality() == 0) {
                System.out.println(iter);
                return sprime;
            }
            if (s.getQuality() <= sprime.getQuality())                // rhc
                s = sprime;
        }
        return null;
    }

    public static NQueen GreedyHillClimbing(int maxIter, int n) {


        NQueen s = generateRandomly(n);
        if (s.getQuality() == 0) return s;

        int iter = 0;
        //NQueen sprime = selectBest(s,n);			// ghc
        while (iter++ < maxIter) {
            //NQueen sprime = generateRandomNeighbor(s);
            NQueen sprime = selectBest(s, n);
            if (sprime.getQuality() == 0) {
                System.out.println(iter);
                return sprime;
            }
            if (s.getQuality() >= sprime.getQuality()) {
                s = sprime;
                for (int k = 0; k < n; k++)
                    System.out.print(s.queens[k] + " ");        //ghc
                System.out.println();
                for (int k = 0; k < n; k++)
                    System.out.print(sprime.queens[k] + " ");
                System.out.println();
            }
        }
        return null;
    }


    public static NQueen MonteCarlo(int maxIter, int n) {
        int iter = 0;
        while (iter++ < maxIter) {
            NQueen s = generateRandomly(n);
            if (s.getQuality() == 0)                    // monnte carrlo
            {
                System.out.println(iter);
                return s;
            }
        }
        return null;
    }
        /* 	algo done	*/

    public static NQueen
    generateRandomNeighbor(NQueen other) {
        NQueen temp = new NQueen(other);
        int rq = myRand.nextInt(temp.n);
        int rv = myRand.nextInt(temp.n);
        temp.queens[rq] = rv;

        return temp;
    }

    public static NQueen generateRandomly(int n) {
        NQueen obj = new NQueen(n);
        for (int i = 0; i < n; i++) {
            obj.queens[i] = myRand.nextInt(n);
        }
        return obj;
    }

    public static NQueen
    selectBest(NQueen other, int n) {

        NQueen temp = null;
        NQueen temp2 = null;
        temp = new NQueen(other);
        System.out.println("main");
        for (int k = 0; k < n; k++)
            System.out.print(temp.queens[k] + " ");
        System.out.println();

        for (int i = 0; i < n; i++) {
            //	temp2 = new NQueen(other);
            for (int j = 0; j < n; j++) {
                temp2 = new NQueen(other);
                if (temp2.queens[i] != j)
                    temp2.queens[i] = j;
                //System.out.print(i+" "+j+"\n");
                if (temp.getQuality() >= temp2.getQuality())
                    temp = temp2;


            }
        }

        return temp;
    }

}
