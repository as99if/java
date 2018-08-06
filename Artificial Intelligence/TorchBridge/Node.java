import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * Created by swakkhar on 3/1/2017.
 */
public class Node  implements Comparable<Node>{
    public State s;
    public ArrayList<String> actionList;
    public int cost=Integer.MAX_VALUE;

    public Node() {
    }

    public int getCost() {
        return cost;
    }

    public Node(State s, ArrayList<String> actionList, int cost) {
        this.s = new State(s);
        this.actionList = new ArrayList<>(actionList);

        
    }
    public int calculateCost(){
        int sum = 0;
        for (String temp : actionList) {
            if(temp.equals("A"))
                sum = sum + s.costA;
            if(temp.equals("B"))
                sum = sum + s.costB;
            if(temp.equals("C"))
                sum = sum + s.costC;
            if(temp.equals("D"))
                sum = sum + s.costD;
            if(temp.equals("AB"))
                sum = sum + Math.max(s.costA, s.costB);
            if(temp.equals("AC"))
                sum = sum + Math.max(s.costA, s.costC);
            if(temp.equals("AD"))
                sum = sum + Math.max(s.costA, s.costD);
            if(temp.equals("BC"))
                sum = sum + Math.max(s.costC, s.costB);
            if(temp.equals("BD"))
                sum = sum + Math.max(s.costD, s.costB);
            if(temp.equals("CD"))
                sum = sum + Math.max(s.costC, s.costD);
        }
        cost = sum;
        return cost;
    	// write something here
    }
    public Node(Node other) {
        this.s = new State(other.s);
        this.actionList = new ArrayList<>(other.actionList);
    }


    @Override
    public int compareTo(Node o) {
        return this.cost-o.cost;
    }
}
