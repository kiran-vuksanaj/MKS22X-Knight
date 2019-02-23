public class Space implements Comparable<Space>{
  private int step;
  private int availSteps;
  private Space[][] boardSpace;
  private int row;
  private int col;

  public Space(Space[][] board,int row,int col){

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
