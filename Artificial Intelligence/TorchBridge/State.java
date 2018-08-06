/**
 * Created by swakkhar on 3/1/2017.
 */
public class State {
    public boolean torch;
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    /*cost of action*/
    public int costA = 1;
    public int costB = 2;
    public int costC = 5;
    public int costD = 8;

    /*actions*/
    public State MoveA(){
        if(torch!=A)
            return null;

        State temp = new State(this);
        temp.torch=!temp.torch;
        temp.A=!temp.A;
        return temp;

    }
    public State MoveB(){
        if(torch!=B)
            return null;

        State temp = new State(this);
        temp.torch=!temp.torch;
        temp.B=!temp.B;
        return temp;

    }
    public State MoveC(){
        if(torch!=C)
            return null;

        State temp = new State(this);
        temp.torch=!temp.torch;
        temp.C=!temp.C;
        return temp;

    }
    public State MoveD(){
        if(torch!=D)
            return null;

        State temp = new State(this);
        temp.torch=!temp.torch;
        temp.D=!temp.D;
        return temp;

    }
    public State MoveAB(){
        if(torch!=A | torch!=B | A!=B)
            return null;

        State temp = new State(this);
        temp.torch=!temp.torch;
        temp.A=!temp.A;
        temp.B=!temp.B;
        return temp;

    }
    public State MoveAC(){
        if(torch!=A | torch!=C | A!=C)
            return null;

        State temp = new State(this);
        temp.torch=!temp.torch;
        temp.A=!temp.A;
        temp.C=!temp.C;
        return temp;

    }
    public State MoveAD(){
        if(torch!=A | torch!=D | A!=D)
            return null;

        State temp = new State(this);
        temp.torch=!temp.torch;
        temp.A=!temp.A;
        temp.D=!temp.D;
        return temp;

    }
    public State MoveBC(){
        if(torch!=C | torch!=B | C!=B)
            return null;

        State temp = new State(this);
        temp.torch=!temp.torch;
        temp.B=!temp.B;
        temp.C=!temp.C;
        return temp;

    }
    public State MoveBD(){
        if(torch!=D | torch!=B | D!=B)
            return null;

        State temp = new State(this);
        temp.torch=!temp.torch;
        temp.B=!temp.B;
        temp.D=!temp.D;
        return temp;

    }
    public State MoveCD(){
        if(torch!=C | torch!=C | C!=D)
            return null;

        State temp = new State(this);
        temp.torch=!temp.torch;
        temp.C=!temp.C;
        temp.D=!temp.D;
        return temp;

    }



    /* */
    boolean isGoal() {
    	return A&&B&&C&&D&&torch;
    }
    
    public State() {
        super();
    }

    public State(boolean torch, boolean a, boolean b, boolean c, boolean d) {

        this.torch = torch;
        A = a;
        B = b;
        C = c;
        D = d;
    }
    public State(State other) {

        this.torch = other.torch;
        A = other.A;
        B = other.B;
        C = other.C;
        D = other.D;
    }

    @Override
    public String toString() {
        return "State{" +
                "torch=" + torch +
                ", A=" + A +
                ", B=" + B +
                ", C=" + C +
                ", D=" + D +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (torch != state.torch) return false;
        if (A != state.A) return false;
        if (B != state.B) return false;
        if (C != state.C) return false;
        return D == state.D;

    }


}
