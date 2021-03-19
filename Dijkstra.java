import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

class Dijkstra {
  public void calculateShortestPath(Graph graph, Node startNode, Node targetNode) {
    startNode.setDistance(0);
    ArrayList<Node> visited = new ArrayList<Node>();

    Comparator<PathInfo> costComparator = new Comparator<PathInfo>() {
        public int compare(PathInfo a, PathInfo b) {
          return Integer.compare(a.cost, b.cost);
        }
      };

    PriorityQueue<PathInfo> queue = new PriorityQueue<>(costComparator);
    System.out.println("Dijkstra class");

    queue.add(new PathInfo(startNode, new ArrayList<Node>(), 0));
    while(!queue.isEmpty()) {
      PathInfo currentPath = queue.poll();
      visited.add(currentPath.node);
      System.out.println("current node: "+currentPath.node.data);
      if (currentPath.node == targetNode) {
        System.out.println("Found");
        printPath(currentPath.node);
        return;
      }
      System.out.println("queue");

      ArrayList<Path> paths = currentPath.node.getPaths();
      for(Path path: paths) {
        if(!visited.contains(path.destination)) {
          int temp = currentPath.node.distance + path.cost;
          if (temp < path.destination.distance) {
            path.destination.distance = temp;
            path.destination.parent = currentPath.node;
          }
          queue.add(new PathInfo(path.destination, new ArrayList<Node>(), path.cost+currentPath.node.distance));
          System.out.print(path.destination.data+" ");
          System.out.println(path.destination.distance);
        }
      }





    } // end of while
    return;

  } // end of calculateShortest Path

  public void printPath(Node solution) {
    Stack<String> stack= new Stack<>();
    for(Node thisNode = solution; thisNode!=null; thisNode = thisNode.parent)
      stack.push(thisNode.data);
    while(!stack.isEmpty()) {
      System.out.print(stack.pop()+" ");
    }
  }
} // end of Dijkstra class
