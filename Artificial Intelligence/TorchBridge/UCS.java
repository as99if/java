import java.util.ArrayList;
import java.util.PriorityQueue;


public class UCS {
	public static void main(String...args)
	{
		State start= new State();
		System.out.println(start);
		System.out.println(start.MoveA());
		System.out.println(ucs(start));
	   	
	}

	public static ArrayList<String> ucs(State start){
        ArrayList<State> visitedList = new ArrayList<State>();

        Node initial = new Node();
        initial.s = start;
        initial.actionList = new ArrayList<String>();

        System.out.println(initial.calculateCost());


        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(initial);

        while(!pq.isEmpty()){
            Node top = pq.poll();
            if(top.s.isGoal())
                return top.actionList;
            if(visitedList.contains(top.s))
                continue;

            State a = top.s.MoveA();
            if(a != null){
                Node temp = new Node();
                temp.s = a;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("A");
                temp.calculateCost();
                pq.add(temp);
            }

            State b = top.s.MoveB();
            if(b != null){
                Node temp = new Node();
                temp.s = b;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("B");
                temp.calculateCost();
                pq.add(temp);
            }

            State c = top.s.MoveC();
            if(c != null){
                Node temp = new Node();
                temp.s = c;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("C");
                temp.calculateCost();
                pq.add(temp);
            }

            State d = top.s.MoveD();
            if(d != null){
                Node temp = new Node();
                temp.s = d;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("D");
                temp.calculateCost();
                pq.add(temp);
            }
            State ab = top.s.MoveAB();
            if(ab != null){
                Node temp = new Node();
                temp.s = ab;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("AB");
                temp.calculateCost();
                pq.add(temp);
            }
            State ac = top.s.MoveAC();
            if(ac != null){
                Node temp = new Node();
                temp.s = ac;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("AC");
                temp.calculateCost();
                pq.add(temp);
            }
            State ad = top.s.MoveAD();
            if(ad != null){
                Node temp = new Node();
                temp.s = ad;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("AD");
                temp.calculateCost();
                pq.add(temp);
            }
            State bc = top.s.MoveBC();
            if(bc != null){
                Node temp = new Node();
                temp.s = bc;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("BC");
                temp.calculateCost();
                pq.add(temp);
            }
            State bd = top.s.MoveBD();
            if(bd != null){
                Node temp = new Node();
                temp.s = bd;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("BD");
                temp.calculateCost();
                pq.add(temp);
            }
            State cd = top.s.MoveCD();
            if(cd != null){
                Node temp = new Node();
                temp.s = cd;
                temp.actionList = new ArrayList<String>(top.actionList);
                temp.actionList.add("CD");
                temp.calculateCost();
                pq.add(temp);
            }

            visitedList.add(top.s);

        }

		return null;
	}




	
}
