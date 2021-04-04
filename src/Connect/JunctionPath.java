package src.Connect;

public class JunctionPath {
    private JunctionNode destination;
    private double cost;

    public JunctionPath(JunctionNode node, double c){
        destination = node;
        cost = c;
    }

    public void setDestination(JunctionNode node){
        destination = node;
    }

    public void setCost(double c){
        cost = c;
    }

    public JunctionNode getDestination(){
        return destination;
    }

    public double getCost(){
        return cost;
    }
}
