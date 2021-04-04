/*
package src.Dijkstra;

import java.util.ArrayList;
class Dijkstra {
  public static void main(String args[]) {

    Node nodeS =new Node("S",14);
		Node nodeA =new Node("A",7);
		Node nodeB =new Node("B",10);
    Node nodeC =new Node("C",4);
		Node nodeD =new Node("D",2);
    Node nodeE =new Node("E",4);
		Node nodeG =new Node("G",0);

    nodeS.adjacentNodes = new ArrayList<Path>();
    nodeS.adjacentNodes.add(new Path(nodeA, 6));
    nodeS.adjacentNodes.add(new Path(nodeB, 5));
    nodeS.adjacentNodes.add(new Path(nodeC, 10));

    nodeA.adjacentNodes = new ArrayList<Path>();
    nodeA.adjacentNodes.add(new Path(nodeD, 6));

    nodeB.adjacentNodes = new ArrayList<Path>();
    nodeB.adjacentNodes.add(new Path(nodeD, 6));
    nodeB.adjacentNodes.add(new Path(nodeE, 7));

    nodeC.adjacentNodes = new ArrayList<Path>();
    nodeC.adjacentNodes.add(new Path(nodeE, 6));

    nodeD.adjacentNodes = new ArrayList<Path>();
    nodeD.adjacentNodes.add(new Path(nodeC, 4));
    nodeD.adjacentNodes.add(new Path(nodeG, 4));

    nodeE.adjacentNodes = new ArrayList<Path>();
    nodeE.adjacentNodes.add(new Path(nodeA, 3));
    nodeE.adjacentNodes.add(new Path(nodeG, 6));

    Dijkstra dijkstra = new Dijkstra();
    System.out.print("Traverse Path: ");
    dijkstra.search(nodeS, "G");
  }
}
*/