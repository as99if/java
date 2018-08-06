package com.company;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by student on 2/8/2017.
 */public class BFS {
    public static void main(String ...args){
        State start = new State(
                new int [][]{
                        {1,2,6,3},
                        {4,5,10,7},
                        {8,0,9,11},
                        {12,13,14,15}
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

        ArrayList<State> visitedList
                = new ArrayList<State>();

        visitedList.add(start);
        visitedList.add(goal);



        System.out.println(visitedList.contains(start));
        System.out.println(visitedList.contains(goal));

        /* testing actions*/
        System.out.println("Testing actions :");
        System.out.println("RIGHT \n" + start.performRight());
        System.out.println("UP \n" + start.performUp());
        System.out.println("DOWN \n" + start.performDown());
        System.out.println("LEFT \n" + start.performLeft());
        /*                */

        System.out.println("Solution " + bfs(start));


    }

    public static ArrayList<String> bfs(State start){
        ArrayList<State> visitedList = new ArrayList<State>();

        Node initial = new Node();
        initial.s = start;
        initial.actionList = new ArrayList<String>();

        ArrayList<Node> queue = new ArrayList<Node>();
        queue.add(initial);

        while(!queue.isEmpty()){
            Node top = queue.remove(0);
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
                queue.add(temp);
            }

            State down = top.s.performDown();
            if(down != null){
                Node temp = new Node();
                temp.s = down;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("DOWN");
                queue.add(temp);
            }

            State left = top.s.performLeft();
            if(left != null){
                Node temp = new Node();
                temp.s = left;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("LEFT");
                queue.add(temp);
            }

            State right = top.s.performRight();
            if(right != null){
                Node temp = new Node();
                temp.s = right;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("RIGHT");
                queue.add(temp);
            }

            visitedList.add(top.s);

        }
        return null;
    }
}