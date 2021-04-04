package src.Dijkstra;

import java.util.ArrayList;
class TestDijkstra {
  public static void main(String args[]) {
    // assign the node data
    Node nodeS =new Node("S");
		Node nodeA =new Node("A");
		Node nodeB =new Node("B");
    Node nodeC =new Node("C");
		Node nodeD =new Node("D");
		Node nodeG =new Node("G");

    // create an arrayList for the paths and add the paths to nodes with the cost of the path
    //    nodeS.adjacentNodes = new ArrayList<Path>();
    nodeS.adjacentNodes.add(new Path(nodeA, 3));
    nodeS.adjacentNodes.add(new Path(nodeB, 2));

    //nodeA.adjacentNodes = new ArrayList<Path>();
    nodeA.adjacentNodes.add(new Path(nodeB, 1));
    nodeA.adjacentNodes.add(new Path(nodeD, 5));

    //nodeB.adjacentNodes = new ArrayList<Path>();
    nodeB.adjacentNodes.add(new Path(nodeC, 2));
    nodeB.adjacentNodes.add(new Path(nodeD, 3));

    //nodeC.adjacentNodes = new ArrayList<Path>();
    nodeC.adjacentNodes.add(new Path(nodeD, 3));
    nodeC.adjacentNodes.add(new Path(nodeG, 4));


    //nodeD.adjacentNodes = new ArrayList<Path>();
    nodeD.adjacentNodes.add(new Path(nodeG, 1));


    Graph graph = new Graph();
    graph.nodes.add(nodeS);
    graph.nodes.add(nodeA);
    graph.nodes.add(nodeB);
    graph.nodes.add(nodeC);
    graph.nodes.add(nodeD);
    graph.nodes.add(nodeG);

    Dijkstra dijkstra = new Dijkstra();

    //dijkstra.calculateShortestPath(graph, nodeS, nodeG);
  }
}
