package com.company;
import java.util.ArrayList;


public class Bfs {

    public static void main(String ...args)
    {
        State start = new State();

        State goal = new State(true,true,true,true);

        System.out.println(bfs(start));

    }


    public static ArrayList<String> bfs(State start)
    {
        // the visited lsit
        ArrayList<State> visitedList=
                new ArrayList<State>();
        Node initial = new Node();
        initial.s=start;
        initial.actionList= new ArrayList<String>();

        ArrayList<Node> Queue = new  ArrayList<Node>();
        Queue.add(initial);

        while(!Queue.isEmpty())
        {
            // first element remove from queue
            Node top = Queue.remove(0);

            // top is the goal or not
            if(top.s.isGoal())
                return top.actionList;

            if(visitedList.contains(top.s))
                continue;

            // expanding child. . .
            State goat = top.s.moveGoat();
            if(goat!=null)
            {
                Node temp=new Node();
                temp.s=goat;
                temp.actionList =
                        new ArrayList<String>(top.actionList);
                temp.actionList.add("GOAT");
                Queue.add(temp);

            }

            State tiger = top.s.moveTiger();
            if(tiger!=null)
            {
                Node temp=new Node();
                temp.s=tiger;
                temp.actionList =
                        new ArrayList<String>(top.actionList);
                temp.actionList.add("TIGER");
                Queue.add(temp);

            }

            State leaf = top.s.moveLeaf();
            if(leaf!=null)
            {
                Node temp=new Node();
                temp.s=leaf;
                temp.actionList =
                        new ArrayList<String>(top.actionList);
                temp.actionList.add("LEAF");
                Queue.add(temp);

            }

            State emty = top.s.moveEmpty();
            if(emty!=null)
            {
                Node temp=new Node();
                temp.s=emty;
                temp.actionList =
                        new ArrayList<String>(top.actionList);
                temp.actionList.add("EMPTY");
                Queue.add(temp);

            }

            visitedList.add(top.s);

        }
        return null;
    }






}
