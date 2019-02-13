public class KnightBoard{
  private int[][] pubBoard;
  private int stepsNecessary;
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
    return "";
  }

  public boolean solve(int startingRow,int startingCol){
    if(startingRow < 0 || startingCol < 0 ||
       startingRow >= pubBoard.length || startingCol >= pubBoard[0].length)
       throw new IllegalArgumentException("row/col out of bounds");
    return solver(startingRow,startingCol,0);
  }
  private boolean solver(int row,int col,int step){
    if(step == stepsNecessary){
      pubBoard[row][col] = step;
      return true;
    }else{
      //GENERATE LIST OF POTENTIAL MOVES (only -1)
      //CYCLE THROUGH MOVES, RECURSING DOWN
      //IF NOT RETURNED, REMOVE SELF, AND RETURN FALSE
      return false;
    }
  }

  public int countSolutions(int startingRow,int startingCol){
    if(startingRow < 0 || startingCol < 0 ||
       startingRow >= pubBoard.length || startingCol >= pubBoard[0].length)
       throw new IllegalArgumentException("row/col out of bounds");
    return -1;
  }
}
