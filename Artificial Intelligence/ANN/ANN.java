package com.company;

/**
 * Created by student on 4/26/2017.
 */
/**
 * Created by Asif Ahmed Shuvo on 25-04-17.
 */

import java.util.Arrays;
import java.util.Random;


public class ANN {

    public static double[][] X = {
            {0, 0, 0},
            {0, 0, 1},
            {0, 1, 0},
            {0, 1, 1},
            {1, 0, 0},          //change
            {1, 0, 1},
            {1, 1, 0},
            {1, 1, 1}
    };

    public static double[][] y = {
            {0,1},
            {1,0},
            {1,0},
            {0,1},
            {1,0},                  //change
            {0,1},
            {0,1},
            {1,0}
    };

   

    public static double least_error = 100;
    public static double[][] hiddenWeights = new double[3][4];/*{
        {-5.0,10.0,-10.0, x},
		{-5.0,-10.0,10.0,x },           // change
		{-5.0,-10.0,10.0,x }
	};*/
    public static double[][] outputWeights = new double[2][4];/*{
    {-5,10,10,x},
    {-5,10,10,x}                // change
    };*/
    public static double[][] backtrackedHiddenWeight = new double[3][4];/*{
        {-5.0,10.0,-10.0, x},
		{-5.0,-10.0,10.0,x },           // change
		{-5.0,-10.0,10.0,x }
	};*/
    public static double[][] backtrackedOutputWeight = new double[2][4];

    public static Random rnd = new Random();

    public static double activation(double z) {
        //return z>0?1:0;
        return 1.0 / (1 + Math.exp(-1.0 * z));
    }

    public static double[] feedForward(double[] x) {
        // generic korte hobe
        double[] output = new double[2];
        double outputNH0 = hiddenWeights[0][0] +                        // change
                            hiddenWeights[0][1] * x[0] +
                            hiddenWeights[0][2] * x[1] +
                            hiddenWeights[0][3] * x[2] ;
        double outputNH1 = hiddenWeights[1][0] +
                            hiddenWeights[1][1] * x[0] +
                            hiddenWeights[1][2] * x[1] +            // change
                            hiddenWeights[1][3] * x[2] ;
        double outputNH2 = hiddenWeights[2][0] +
                            hiddenWeights[2][1] * x[0] +
                            hiddenWeights[2][2] * x[1] +            // change
                            hiddenWeights[2][3] * x[2] ;

        double outputNHA0 = activation(outputNH0);
        double outputNHA1 = activation(outputNH1);
        double outputNHA2 = activation(outputNH2);          // change

        double outputNHF0 = outputWeights[0][0] +
                            outputWeights[0][1] * outputNHA0 +
                            outputWeights[0][2] * outputNHA1 +       // change
                            outputWeights[0][3] * outputNHA2;

        double outputNHF1 = outputWeights[1][0] +
                            outputWeights[1][1] * outputNHA0 +         // change
                            outputWeights[1][2] * outputNHA1 +
                            outputWeights[1][3] * outputNHA2;

        output[0] = activation(outputNHF0);                 // change
        output[1] = activation(outputNHF1);
        //System.out.println(output);
        return output;

    }

    public static void main(String[] args) {

        job();
        System.out.println("\n\n Y1 and Y2");
        for (double [] x : X) {
            System.out.println(Arrays.toString(feedForward(x)));                // change
        }

        System.out.println("\n\n HiddenWeights");
        for (double [] x : hiddenWeights) {
            System.out.println(Arrays.toString(x));
        }
        //System.out.println(Arrays.toString(outputWeights));
        //System.out.println(Arrays.toString(outputWeights));
    }

    public static void job(){

        int m, n;
        int iter = 0, cnt = 0;
        while (iter <= 10000000) {
            learn();

            int i = 0;
            double[] ans = new double[2];
            double error = 0;
            for (double[] x : X) {
                ans = feedForward(x);
                for(int j = 0 ; j < y[0].length; j++)                   // change
                    error = error + (Math.abs(y[i][j] - ans[j]));           // change
                i++;
            }


            if (error < least_error) {
                cnt++;
                least_error = error;

                for (m = 0; m < hiddenWeights.length; m++)                 // change
                    for (n = 0; n < hiddenWeights[0].length; n++)
                        backtrackedHiddenWeight[m][n] = hiddenWeights[m][n];
                for (m = 0; m < outputWeights.length; m++)
                    for (n = 0; n < outputWeights[0].length; n++)
                        backtrackedOutputWeight[m][n] = outputWeights[m][n];             // add
            }
            iter++;
        }

        for (m = 0; m < hiddenWeights.length; m++)
            for (n = 0; n < hiddenWeights[0].length; n++) {                   // change
                hiddenWeights[m][n] = backtrackedHiddenWeight[m][n];
            }
        for (m = 0; m < outputWeights.length; m++)
            for (n = 0; n < outputWeights[0].length; n++)                             // change
                outputWeights[m][n] = backtrackedOutputWeight[m][n];

    }

    public static void learn() {
        for (int i = 0; i < hiddenWeights.length; i++)
            for (int j = 0; j < hiddenWeights[0].length; j++)                   // kono change nai
                hiddenWeights[i][j] = -10 + rnd.nextDouble() * 20;
        for (int i = 0; i < outputWeights.length; i++)
            for (int j = 0; j < hiddenWeights[0].length; j++)                   // change
                outputWeights[i][j] = -10 + rnd.nextDouble() * 20;
    }

}
