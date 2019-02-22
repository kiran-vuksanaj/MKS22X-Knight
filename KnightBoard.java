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

  public static void main(String[] args){
    KnightBoard kb = new KnightBoard(5,5);
    System.out.println(kb.solve(0,0));
    System.out.println(kb.toString());
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
    stepsNecessary = startingRows*startingCols - 1;
  }

  public String toString(){
    String out = "";
    for(int[] row : pubBoard){
      for(int cell : row){
        if(cell==-1){//will only be used during testing;
          out += "   ";
        }else if(cell<10){
          out += " "+(cell+1)+" ";
        }else{
          out += (cell+1)+" ";
        }
      }
      out += "\n";
    }
    return out;
  }

  public boolean solve(int startingRow,int startingCol){
    //showBoard();
    if(startingRow < 0 || startingCol < 0 ||
       startingRow >= pubBoard.length || startingCol >= pubBoard[0].length)
       throw new IllegalArgumentException("row/col out of bounds");
    return solver(startingRow,startingCol,0);
  }
  private boolean solver(int row,int col,int step){
    //System.out.println(Text.go(0,0)+toString());
    if(step == stepsNecessary){
      pubBoard[row][col] = step;
      //resetBoard();
      return true;
    }else{
      pubBoard[row][col] = step;
      for(int i=0;i<moveStatic.length;i++){
        int rowTry = row + moveStatic[i][0];
        int colTry = col + moveStatic[i][1];
        if(rowTry >= 0 && rowTry < pubBoard.length &&
           colTry >= 0 && colTry < pubBoard[rowTry].length &&
           pubBoard[rowTry][colTry] == -1 &&
           solver(rowTry,colTry,step+1)){
             return true;
           }
      }
      //CYCLE THROUGH MOVES, RECURSING DOWN
      //IF NOT RETURNED, REMOVE SELF, AND RETURN FALSE
      pubBoard[row][col] = -1;
      //updateBoard(row,col,step,true);
      return false;
    }
  }

  public int countSolutions(int startingRow,int startingCol){
    if(startingRow < 0 || startingCol < 0 ||
       startingRow >= pubBoard.length || startingCol >= pubBoard[0].length)
       throw new IllegalArgumentException("row/col out of bounds");
    return -1;
  }

  private void showBoard(){//only use when empty
    System.out.println(Text.CLEAR_SCREEN);
    System.out.println(Text.HIDE_CURSOR);
  }
  private void resetBoard(){
    System.out.println(Text.RESET);
  }
}
