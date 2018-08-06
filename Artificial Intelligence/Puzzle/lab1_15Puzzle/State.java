package com.company;

/**
 * Created by student on 2/8/2017.
 */
public class State {
    int [][] puzzle = new int[4][4];

    /*actions*/
        /// up
    public State performUp(){
        // first find the row and column of 0
        int row=0;
        int col=0;
        boolean found=false;
        for(row=0;row<4;row++){
            for(col=0;col<4;col++){
                if(puzzle[row][col]==0){
                    found=true;
                    break;
                }
            }
            if(found==true)
                break;
        }
        // you can not perform up if row == 0
        if(row==0)
            return null;
        State up = new State(new int[4][4]);
        // copy the puzzle from
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                up.puzzle[i][j]=puzzle[i][j];

        up.puzzle[row][col]=up.puzzle[row-1][col];
        up.puzzle[row-1][col]=0;

        return up;
    }
    ///down
    public State performDown(){
        // first find the row and column of 0
        int row=0;
        int col=0;
        boolean found=false;
        for(row=0;row<4;row++){
            for(col=0;col<4;col++){
                if(puzzle[row][col]==0){
                    found=true;
                    break;
                }
            }
            if(found==true)
                break;
        }
        // you can not perform down if row == 3
        if(row==3)
            return null;
        State down = new State(new int[4][4]);
        // first copy the puzzle
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                down.puzzle[i][j]=puzzle[i][j];

        down.puzzle[row][col]=down.puzzle[row+1][col];
        down.puzzle[row+1][col]=0;

        return down;
    }
    /// left
    public State performLeft(){
        // first find the row and column of 0
        int row=0;
        int col=0;
        boolean found=false;
        for(row=0;row<4;row++){
            for(col=0;col<4;col++){
                if(puzzle[row][col]==0){
                    found=true;
                    break;
                }
            }
            if(found==true)
                break;
        }
        // you can not perform left if col == 0
        if(col==0)
            return null;
        State left = new State(new int[4][4]);
        // first copy the puzzle
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                left.puzzle[i][j]=puzzle[i][j];

        left.puzzle[row][col]=left.puzzle[row][col-1];
        left.puzzle[row][col-1]=0;

        return left;
    }

    ///right
    public State performRight(){
        // first find the row and column of 0
        int row=0;
        int col=0;
        boolean found=false;
        for(row=0;row<4;row++){
            for(col=0;col<4;col++){
                if(puzzle[row][col]==0){
                    found=true;
                    break;
                }
            }
            if(found==true)
                break;
        }
        // you can not perform right if col == 3
        if(col==3)
            return null;
        State right = new State(new int[4][4]);
        // first copy the puzzle
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                right.puzzle[i][j]=puzzle[i][j];

        right.puzzle[row][col]=right.puzzle[row][col+1];
        right.puzzle[row][col+1]=0;

        return right;
    }

    /*actions done*/


    public boolean isGoal(){
        int count=0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(puzzle[i][j]!=count)
                    return false;
                count++;
            }
        }

        return true;
    }

    public State(int[][] puzzle){
        super();
        this.puzzle = puzzle;
    }



    public int[][] getPuzzle(){
        return puzzle;
    }

    public void setPuzzle(int[][] puzzle){
        this.puzzle = puzzle;
    }

    public String toString(){           /* toString ta override kora hoise, Syste.out.println er jonno
                                           string k matrix akare print korar jonno */
        String temp="";
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                temp = temp+puzzle[i][j]+" ";
            }
            temp = temp+"\n";
        }
        return temp;
    }

    public boolean equals(Object o){            /*equals method override korsi, karon
                                                dui ta same object er value element equal
                                                ki na ta lagbe*/
        State s=(State)o;

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(this.puzzle[i][j]!=
                        s.puzzle[i][j])
                    return false;
            }
        }
        return true;
    }
}
