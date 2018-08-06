package com.company;

/**
 * Created by student on 2/21/2017.
 */

public class State {
    boolean boat;
    boolean goat;
    boolean leaf;
    boolean tiger;


    public State moveLeaf()
    {
        // if boat and leaf are not in the same side
        // you can not move leaf
        if(boat!=leaf)
            return null;

        State temp = new State(this);
        temp.boat=!temp.boat;
        temp.leaf=!temp.leaf;

        if((temp.tiger == temp.goat)
                && (temp.goat != temp.boat))
            return null;
        return temp;


    }

    public State moveTiger()
    {
        // if boat and tiger are not in the same side
        // you can not move tiger
        if(boat!=tiger)
            return null;

        State temp = new State(this);
        temp.boat=!temp.boat;
        temp.tiger=!temp.tiger;

        if((temp.leaf == temp.goat)
                && (temp.goat != temp.boat))
            return null;
        return temp;


    }

    public State moveGoat()
    {
        // if boat and goat are not in the same side
        // you can not move goat
        if(boat!=goat)
            return null;

        State temp = new State(this);
        temp.boat=!temp.boat;
        temp.goat=!temp.goat;


        return temp;


    }


    public State moveEmpty()
    {

        State temp = new State(this);
        temp.boat=!temp.boat;

        //temp.tiger=!temp.tiger;

        if((temp.leaf == temp.goat)
                && (temp.goat != temp.boat))
            return null;

        if((temp.tiger == temp.goat)
                && (temp.goat != temp.boat))
            return null;



        return temp;


    }


    public boolean isGoal()
    {
        return boat&&goat&&leaf&&tiger;
    }

    public State() {
        super();
    }

    public State(State o)
    {
        this(o.boat,o.goat,o.leaf,o.tiger);
    }


    public State(boolean boat, boolean goat, boolean leaf, boolean tiger) {
        super();
        this.boat = boat;
        this.goat = goat;
        this.leaf = leaf;
        this.tiger = tiger;
    }



    @Override
    public String toString() {
        return "State [boat=" + boat + ", goat=" + goat + ", leaf=" + leaf
                + ", tiger=" + tiger + "]";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        State other = (State) obj;
        if (boat != other.boat)
            return false;
        if (goat != other.goat)
            return false;
        if (leaf != other.leaf)
            return false;
        if (tiger != other.tiger)
            return false;
        return true;
    }



}
