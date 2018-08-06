package com.company;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.ArrayList;
import java.util.Comparator;

import static java.lang.Math.abs;

/**
 * Created by student on 2/8/2017.
 */
public class Node implements Comparable<Node> {
    State s;
    ArrayList<String> actionList = new ArrayList<String>();

    int cost = Integer.MAX_VALUE;

    public int getCost() {
        return cost;
    }
    public int caculateCost(){
        cost = g() + h();
        return cost;
    }
    public int g(){
        return actionList.size();
                    // action list er size
    }
    public int h(){
        int goal_r;
        int goal_c;
        int man_dist=0;

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(s.puzzle[i][j] != 0) {
                    goal_r = s.puzzle[i][j] / 4;
                    goal_c = s.puzzle[i][j] % 4;

                    man_dist = man_dist+ (Math.abs(i - goal_r) + Math.abs(j - goal_c));

                    // man_dist = |row=row_goal|+|col-col_goal|
                }
            }
        }

        return man_dist;
    }

    @Override
    public int compareTo(Node o) {      // priority queue er sorting ta
        return this.cost-o.cost;        // jate cost onujayi hoy
                                        // cost diye compare korbo
    }
}
