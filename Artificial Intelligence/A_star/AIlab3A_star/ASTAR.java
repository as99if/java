package com.company;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by student on 2/8/2017.
 */public class ASTAR {       /*bfs with priority queue*/
    public static void main(String ...args){
        State start = new State(
                new int [][]{
                        {4,1,2,6},
                        {0,5,10,3},
                        {8,13,9,7},
                        {12,14,15,11}
                });
        System.out.println(start);

        State goal = new State(
                new int[][]{
                        {0,1,2,3},
                        {4,5,6,7},
                        {8,9,10,11},
                        {12,13,14,15}
                });

        System.out.println(goal);
        System.out.println(start.isGoal());
        System.out.println(goal.isGoal());

        System.out.println(a_star(start));

    }

    public static ArrayList<String> a_star(State start){
        ArrayList<State> visitedList = new ArrayList<State>();

        Node initial = new Node();
        initial.s = start;
        initial.actionList = new ArrayList<String>();

        System.out.println(initial.h());
        System.out.println(initial.g());
        System.out.println(initial.caculateCost());

        //ArrayList<Node> queue = new ArrayList<Node>();
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(initial);

        while(!pq.isEmpty()){
            Node top = pq.poll();
            if(top.s.isGoal())
                return top.actionList;
            if(visitedList.contains(top.s))
                continue;

            State up = top.s.performUp();
            if(up != null){
                Node temp = new Node();
                temp.s = up;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("UP");
                temp.caculateCost();
                pq.add(temp);
            }

            State down = top.s.performDown();
            if(down != null){
                Node temp = new Node();
                temp.s = down;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("DOWN");
                temp.caculateCost();
                pq.add(temp);
            }

            State left = top.s.performLeft();
            if(left != null){
                Node temp = new Node();
                temp.s = left;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("LEFT");
                temp.caculateCost();
                pq.add(temp);
            }

            State right = top.s.performRight();
            if(right != null){
                Node temp = new Node();
                temp.s = right;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("RIGHT");
                temp.caculateCost();
                pq.add(temp);
            }

            visitedList.add(top.s);

        }

        return null;
    }
}