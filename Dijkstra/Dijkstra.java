import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

class Dijkstra {

  /**
   * Calculate the shortest path from the starting node to the selected targetNode on a given graph
   * @param graph: The graph containing the nodes and paths between those nodes
   * @param startNode: The starting node from which the algorithm will branch out from
   * @param targetNode: The node that is being searched for in the graph
   * @return The graph with the updated data from the dijkstra algorithm
   */
  public Graph calculateShortestPath(Graph graph, Node startNode, Node targetNode) {
    startNode.setDistance(0);

    Comparator<Node> distanceComparator = new Comparator<Node>() {
        public int compare(Node a, Node b) {
          return Integer.compare(a.distance, b.distance);
        }
      };
    PriorityQueue<Node> queue = new PriorityQueue<>(distanceComparator);
    queue.add(startNode);

    while(!queue.isEmpty()) {
      Node currentNode = queue.poll();
      // If multiple of the same node has already been added to the queue ensure that
      // only one is visited.
      if(currentNode.visited == false) {
        // Set the visited value of the node to true to avoid it being visited agoin
        currentNode.visited = true;
        System.out.println("current node: "+currentNode.data);
        System.out.println(currentNode);

        if (currentNode == targetNode) {
          System.out.println("Found");
          printShortestPath(currentNode);
          return graph;
        }

        ArrayList<Path> paths = currentNode.getPaths();
        // go through all the paths of the current node
        for(Path path: paths) {
          // ensure that the node at the end of the path isn't already visited
          if(path.destination.visited == false) {

            int temp = currentNode.distance + path.cost;
            // check to see if the cost to get to the node from the current path is -
            // shorter than any of the other paths that have this node as its destination
            if (temp < path.destination.distance) {
              path.destination.distance = temp; // set the distance to get to that node
              path.destination.parent = currentNode; // set the parent node
              System.out.print(path.destination.data+" ");
              System.out.println(path.destination.distance);
              queue.add(path.destination); // add the node to the queue
            }
          }
        }
      }
    } // end of while
    return graph;
  } // end of calculateShortest Path

  /**
   * gets the data from the point of the graph which is deemed the solution and works backwards
   * to get the actual path to take
   * @param solution: the desired Node in the graph to work backwards from to reach the start
   * @return void
   */
  public void printShortestPath(Node solution) {
    Stack<String> stack= new Stack<>();
    for(Node thisNode = solution; thisNode!=null; thisNode = thisNode.parent)
      stack.push(thisNode.data);
    while(!stack.isEmpty()) {
      System.out.print(stack.pop()+" ");
    }
    return;
  } // end of printShortestPath
} // end of Dijkstra class
