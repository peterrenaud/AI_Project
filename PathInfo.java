import java.util.List;

class PathInfo {
  Node node;
  List<Node> path;
  int cost;

  public PathInfo(Node current, List<Node> path, int cost) {
  	this.node = current;
    this.path = path;
    this.cost = cost;
  }

}
