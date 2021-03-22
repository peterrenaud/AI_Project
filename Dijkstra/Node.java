import java.util.ArrayList;
import java.util.HashMap;
class Node {

  String data;
  int distance = Integer.MAX_VALUE;
  Node parent;
  boolean visited = false;
  ArrayList<Path> adjacentNodes = new ArrayList<>();;


  Node(String data) {
    this.data = data;
  }
  /*
  Node(String data, int cost) {
    this.data = data;
    this.cost = cost;
  }
  */
  /*
  public Map<Node, Interger> getPaths() {
    return adjacentNodes;
  }
  */
  public ArrayList<Path> getPaths() {
    return adjacentNodes;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }

}
