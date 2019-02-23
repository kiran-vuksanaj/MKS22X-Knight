public class Space implements Comparable<Space>{
  private int step;
  private int availSteps;
  private Space[][] boardSpace;
  private int row;
  private int col;
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

  public Space(Space[][] board,int row,int col){
    boardSpace = board;
    this.row = row;
    this.col = col;
    step = -1;
    availSteps = checkAvailableInit();
  }

  public String toString(){
    if(step==-1) return "   ";
    else if(step<9) return " "+(step+1)+" ";
    else return (step+1)+" ";
  }

  private int checkAvailableInit(){//only use at start!!!
    int out = 0;
    for(int[] move : moveStatic){
      if(isAValidSpace(move[0]+row,move[1]+col)) out++;
    }
    return out;
  }

  public boolean isFree(){
    return step == -1;
  }

  public void fill(int step){

  }

  public void empty(){

  }

  public Space[] getNextMoves(){
    return null;
  }

  public int getAvailableSteps(){
    return availSteps;
  }

  public int compareTo(Space other){
    //allows sorting to put spaces in order
    //NON-FREE spaces placed at the end
    //FREE spaces with least available steps first
    if(!(isFree())){
      if(other.isFree()) return 0;
      else return 1;
    }else{
      return getAvailableSteps() - other.getAvailableSteps();
    }
  }

  private boolean isAValidSpace(int r,int c){
    return  r >= 0 && r < boardSpace.length && //row within valid space
            c >= 0 && c < boardSpace[r].length; //col within valid space
  }

}
