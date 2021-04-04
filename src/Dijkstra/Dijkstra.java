package src.Dijkstra;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

import src.Connect.*;

public class Dijkstra {

  /**
   * Calculate the shortest path from the starting node to the selected targetNode on a given graph
   * @param graph: The graph containing the nodes and paths between those nodes
   * @param startNode: The starting node from which the algorithm will branch out from
   * @param targetNode: The node that is being searched for in the graph
   * @return The graph with the updated data from the dijkstra algorithm
   */
  public JunctionNode calculateShortestPath(StreetFinder streetfinder, JunctionNode startNode, JunctionNode targetNode) {
    double destination[] = {targetNode.getLatitude(), targetNode.getLongitude()};
    startNode.setDistance(0);

    Comparator<JunctionNode> distanceComparator = new Comparator<JunctionNode>() {
        public int compare(JunctionNode a, JunctionNode b) {
          return Double.compare(a.getDistanceFromDestination()+a.getDistance(), b.getDistanceFromDestination()+b.getDistance());
        }
      };
      
    PriorityQueue<JunctionNode> queue = new PriorityQueue<JunctionNode>(distanceComparator);
    queue.add(startNode);
    while(!queue.isEmpty()) {
      JunctionNode currentNode = queue.poll();
      // If multiple of the same node has already been added to the queue ensure that
      // only one is visited.
      if(currentNode.getVisited() == false) {
        currentNode.setVisited(true); 
        // Set the visited value of the node to true to avoid it being visited again
        //System.out.println("current node:");
        //System.out.println(currentNode);

        if (currentNode.getJunction_ID() == targetNode.getJunction_ID()) {
          System.out.println("Found");
          printShortestPath(currentNode);
          return currentNode;
        }

        ArrayList<JunctionPath> paths = currentNode.getPaths();
        // go through all the paths of the current node
        for(JunctionPath path: paths) {
          // ensure that the node at the end of the path isn't already visited
          if(path.getDestination().getVisited() == false) {
            double temp = currentNode.getDistance() + path.getCost();
            // check to see if the cost to get to the node from the current path is -
            // shorter than any of the other paths that have this node as its destination
            if (temp < path.getDestination().getDistance()) {
              path.getDestination().setDistance(temp); // set the distance to get to that node
              path.getDestination().setParent(currentNode); // set the parent node
              streetfinder.findAdjacentJunctions(path.getDestination(), destination);
              //System.out.print(path.getDestination().getData() + "\n");
              queue.add(path.getDestination()); // add the node to the queue
            }
          }
        }
      }
    } // end of while
    return null;
  } // end of calculateShortest Path

  /**
   * gets the data from the point of the graph which is deemed the solution and works backwards
   * to get the actual path to take
   * @param solution: the desired Node in the graph to work backwards from to reach the start
   * @return void
   */
  public void printShortestPath(JunctionNode solution) {
    Stack<String> stack= new Stack<>();
    for(JunctionNode thisNode = solution; thisNode!=null; thisNode = thisNode.getParent())
      stack.push(thisNode.getData() + "\n");
    while(!stack.isEmpty()) {
      System.out.print(stack.pop()+" ");
    }
    return;
  } // end of printShortestPath
} // end of Dijkstra class
