public class Space implements Comparable<Space>{
  private int step;
  private int availSteps;
  private Space[][] boardSpace;
  private int row;
  private int col;

  public Space(Space[][] board,int row,int col){
    boardSpace = board;
    this.row = row;
    this.col = col;
    step = -1;
    availSteps = checkAvailable();
  }

  public String toString(){
    return "";
  }

  public int checkAvailable(){
    return -1;
  }

  public boolean isFree(){
    return false;
  }

  public void fill(){

  }

  public void empty(){

  }

  public Space[] getNextMoves(){
    return null;
  }

  public int getAvailableSteps(){
    return -1;
  }

  public int compareTo(Space other){
    return -1;
  }
}
