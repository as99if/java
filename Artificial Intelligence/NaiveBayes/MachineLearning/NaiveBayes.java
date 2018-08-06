import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


/**
 * Created by swakkhar on 4/10/17.
 */
public class NaiveBayes {
    public static int X[][] = new int[5000][400];
    public static int y[] = new int[5000];
    public static Random rnd = new Random();

    public static int i, j;

    public static int sum[][] = new int[10][400];

    public static void readData() throws IOException {
        BufferedReader readerX = new BufferedReader(new FileReader("X.csv"));
        BufferedReader readerY = new BufferedReader(new FileReader("Y.csv"));
        String xStr = null;               // feature
        String yStr = null;               // label
        i = 0;
        while (true) {
            xStr = readerX.readLine();
            yStr = readerY.readLine();
            if (xStr == null) break;

            j = 0;
            for (String x : xStr.split(",")) {
                X[i][j] = Integer.parseInt(x);
                j++;
            }
            y[i] = Integer.parseInt(yStr);
            i++;

        }
    }

    public static void main(String[] args) throws IOException {
        readData();
        int acc=0;
        for (int c = 0; c < 1000; c++) {
            int index = rnd.nextInt(5000);
           // System.out.println(index);
            //new DigitDisplay(X[index]);
           // System.out.println("Real Label:" + y[index]);
            //System.out.println("Predicted Label:" + predictData(X[index]));
            int preData = predictData(X[index]);
            if(preData == y[index]){
                acc++;
            }
        }
        System.out.println("Correct prediction : " + acc);
    }

    public static int predictData(int[] x) {

        for (i = 0; i < 10; i++) {

            for (j = 0; j < 400; j++) {
                int count = 0;
                for (int k = i * 500; k < i * 500 + 500; k++) {
                    if (X[k][j] == 1) {
                        count++;
                    }
                    sum[i][j] = count;
                }
                //System.out.print(sum[i][j] + " ");
            }
        }
        System.out.println();
        int accuracy = 0;
        double pro[] = new double[10];
        double max = 0;
        int pre = 0;
        for (int q = 0; q < 10; q++) {
            pro[q] = 1;

            for (i = 0; i < 400; i++) {
                if (x[i] == 1) {
                    pro[q] = (pro[q] * sum[q][i]) / 500;                // genjam
                } else if (x[i] == 0) {
                    pro[q] = (pro[q] * (500 - sum[q][i])) / 500;        // genjam
                }
            }
            //System.out.println("Pro " + q + " = " + pro[q]);
            if (pro[q] >= max) {
                max = pro[q];
                pre = q;
            }
        }

        //System.out.println("Max pro = " + max);
       // System.out.println(accuracy);
        return pre;
        //return p;
    }
}


class DigitDisplay extends JFrame {
    int[][] data = new int[20][20];

    public DigitDisplay(int[] d) {
        super("Digit Display");
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                data[i][j] = d[i * 20 + j];
        setSize(400, 400);
        setVisible(true);
    }

    public void paint(Graphics g) {
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {
                if (data[i][j] == 1)
                    g.fillRect(i * 20, j * 20, 20, 20);
            }
    }
}
