public class KnightBoard{
  private int[][] pubBoard;
  private int stepsNecessary;
  private static int[][] moveStatic = {
    {-1,2},
    {-2,1},
    {-2,-1},
    {-1,-2},
    {1,-2},
    {2,-1},
    {2,1},
    {1,2}
  };

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
    pubBoard = new int[startingRows][startingCols];
    //FILL WITH -1, DEFAULT VALUE
    for(int i=0;i<pubBoard.length;i++){
      for(int j=0;j<pubBoard[i].length;j++){
        pubBoard[i][j] = -1;
      }
    }
    //INITIATE SPACES
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
       startingRow >= pubBoard.length || startingCol >= pubBoard[0].length)
       throw new IllegalArgumentException("row/col out of bounds");
    return solver(startingRow,startingCol,0);
  }
  private boolean solver(int row,int col,int step){
    //System.out.println(Text.go(0,0)+toString());
    if(step == stepsNecessary){
      //BASE CASE; BOARD IS FULL, SUCCESSFUL SOLVE
      pubBoard[row][col] = step;
      //resetBoard();
      return true;
    }else{
      pubBoard[row][col] = step;
      for(int i=0;i<moveStatic.length;i++){
        int rowTry = row + moveStatic[i][0];
        int colTry = col + moveStatic[i][1];
        if(isAValidEmptySpace(rowTry,colTry) && solver(rowTry,colTry,step+1)){
          //recursion down, this if statement succeeds when space is valid, empty, and successfully solves
          //circuit break using && so solver only runs on valid spaces
          return true;
        }
      }
      //IF NOT RETURNS TRUE, REMOVE SELF, AND RETURN FALSE
      pubBoard[row][col] = -1;
      return false;
    }
  }



  private boolean isAValidEmptySpace(int r,int c){
    return  r >= 0 && r < pubBoard.length && //row within valid space
            c >= 0 && c < pubBoard[r].length && //col within valid space
            pubBoard[r][c] == -1;//space not already filled
  }

  public int countSolutions(int startingRow,int startingCol){
    if(startingRow < 0 || startingCol < 0 ||
       startingRow >= pubBoard.length || startingCol >= pubBoard[0].length)
       throw new IllegalArgumentException("row/col out of bounds");
    return counter(startingRow,startingCol,0);
  }
  private int counter(int row,int col, int step){
    //System.out.println(Text.go(10,0)+toString());
    if(step == stepsNecessary){
      //base case: bottom of tree, one(1) successful branch
      return 1;
    }else{
      //recursive case
      //add step to board, initiate sum variable
      pubBoard[row][col] = step;
      int out = 0;
      //cycle through possible moves, add total successes
      for(int[] move : moveStatic){
        int rowTry = move[0]+row;
        int colTry = move[1]+col;
        if(isAValidEmptySpace(rowTry,colTry)){
          //when a valid space is found, recurses down
          out += counter(rowTry,colTry,step+1);
        }
      }
      //remove step from board, return total of tree branch
      pubBoard[row][col] = -1;
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
