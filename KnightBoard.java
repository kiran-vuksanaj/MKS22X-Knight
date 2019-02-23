public class KnightBoard{
  private int stepsNecessary;
  private Space[][] board;

  public static void main(String[] args){
    KnightBoard kb = null;
    int r,c;
    if(args.length >= 2){
      kb = new KnightBoard(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
    }else{
      kb = new KnightBoard(5,5);
    }
    if(args.length >= 4){
      r = Integer.parseInt(args[2]);
      c = Integer.parseInt(args[3]);
    }else{
      r=0;
      c=0;
    }
    System.out.println(kb.countSolutions(r,c));
    System.out.println(kb.solve(r,c));
    System.out.println(kb);
  }

  public KnightBoard(int startingRows,int startingCols){
    if(startingRows <= 0 || startingCols <= 0) throw new IllegalArgumentException("negative row/col");
    //INITIATE SPACES
    board = new Space[startingRows][startingCols];
    for(int i=0;i<board.length;i++){
      for(int j=0;j<board[i].length;j++){
        board[i][j] = new Space(board,i,j);
      }
    }
    stepsNecessary = startingRows*startingCols - 1;
  }

  public String toString(){
    String out = "";
    for(Space[] row : board){
      for(Space cell : row){
        out += cell;
      }
      out += "\n";
    }
    return out;
  }

  public boolean solve(int startingRow,int startingCol){
    //startBoard();
    if(startingRow < 0 || startingCol < 0 ||
       startingRow >= board.length || startingCol >= board[0].length)
       throw new IllegalArgumentException("row/col out of bounds");
    return solver(board[startingRow][startingCol],0);
  }
  private boolean solver(Space s,int step){
    //System.out.println(Text.go(0,0)+toString());
    if(step == stepsNecessary){
      //BASE CASE; BOARD IS FULL, SUCCESSFUL SOLVE
      s.fill(step);
      //resetBoard();
      return true;
    }else{
      s.fill(step);
      for(Space nextSpace : s.getNextMoves()){
        if(solver(nextSpace,step+1)) return true;
      }
      //IF NOT RETURNS TRUE, REMOVE SELF, AND RETURN FALSE
      s.empty();
      return false;
    }
  }


  public int countSolutions(int startingRow,int startingCol){
    if(startingRow < 0 || startingCol < 0 ||
       startingRow >= board.length || startingCol >= board[0].length)
       throw new IllegalArgumentException("row/col out of bounds");
    return counter(board[startingRow][startingCol],0);
  }
  private int counter(Space s, int step){
    //System.out.println(Text.go(10,0)+toString());
    if(step == stepsNecessary){
      //base case: bottom of tree, one(1) successful branch
      return 1;
    }else{
      //recursive case
      //add step to board, initiate sum variable
      s.fill(step);
      int out = 0;
      //cycle through possible moves, add total successes
      for(Space nextS : s.getNextMoves()){
        out += counter(nextS,step+1);
      }
      //remove step from board, return total of tree branch
      s.empty();
      return out;
    }
  }

  //only for testing purposes, allows continuous display of board in one position
  private void startBoard(){//only use when empty
    System.out.println(Text.CLEAR_SCREEN);
    System.out.println(Text.HIDE_CURSOR);
  }
  private void resetBoard(){
    System.out.println(Text.RESET);
  }
}
