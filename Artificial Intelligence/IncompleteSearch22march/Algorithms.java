import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;


public class Algorithms {
    static Random myRand = new Random();

    public static void main(String... args) {
        /*NQueen p1 = generateRandomly(5);
		NQueen p2 = generateRandomly(5);
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(singleRandomCrossover(p1, p2));
		*/

        System.out.println(geneticAlgorithm(500, 5, 10));

    }

    public static NQueen geneticAlgorithm(int maxIter, int n, int populationSize) {
        ArrayList<NQueen> P = new ArrayList<NQueen>();
        for (int i = 0; i < populationSize; i++)
            P.add(generateRandomly(n));

        for (NQueen x : P) {
            if (x.getQuality() == 0) {
                System.out.println(0);
                return x;
            }


        }
        int iter = 0;
        while (iter++ < maxIter) {
            // 1. Generate children using cross over
            int k = 0;

            PriorityQueue<NQueen> myqueue = new PriorityQueue<NQueen>();
            while (k < (populationSize / 2)) {
                ArrayList<NQueen> pcrossover = new ArrayList<NQueen>();

                int rdn1 = myRand.nextInt(populationSize);
                int rdn2 = myRand.nextInt(populationSize);
                if (rdn1 != rdn2) {

                    pcrossover = singleRandomCrossover(P.get(rdn1), P.get(rdn2));
                    while (!pcrossover.isEmpty()) {
                        NQueen temp = pcrossover.remove(0);
                        if (temp.getQuality() == 0) {
                            System.out.println(iter);
                            return temp;
                        }
                        myqueue.add(temp);
                        NQueen tem = generateRandomNeighbor(temp);
                        if (tem.getQuality() == 0) {
                            System.out.println(iter);
                            return tem;
                        }
                        myqueue.add(tem);

                    }

                    k++;
                }

            }
            // 3. Select best

            while (!P.isEmpty()) {
                myqueue.add(P.remove(0));

            }

            int i = 1;
            while (i <= populationSize) {
                P.add(myqueue.poll());
                i++;
            }

            myqueue.clear();


            // 2. Generate children using mutation


        }
        return null;

    }

    public static ArrayList<NQueen> singleRandomCrossover(NQueen p1, NQueen p2) {
        int n = p1.n;
        int rp = myRand.nextInt(n - 1);
        NQueen c1 = new NQueen(n);
        NQueen c2 = new NQueen(n);
        for (int i = 0; i < n; i++) {
            c1.queens[i] = (i <= rp) ? p1.queens[i] : p2.queens[i];
            c2.queens[i] = (i <= rp) ? p2.queens[i] : p1.queens[i];
        }
        ArrayList<NQueen> twoChild = new ArrayList<NQueen>();
        twoChild.add(c1);
        twoChild.add(c2);
        return twoChild;
    }

    public static NQueen generateRandomNeighbor(NQueen other) {
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


}
