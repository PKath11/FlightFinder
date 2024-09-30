import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
/**
 * This class adds two additional methods to the DijkstraGraph algorithm, and is suppose to be the base class for the FlightFinderProject
 */
public class FlightFinderAE<NodeType extends Comparable<NodeType>,EdgeType extends Number> extends DijkstraGraph<NodeType,EdgeType> implements FlightFinderAEInterface<NodeType,EdgeType>{

    /**
     * Constructor for this class, creates a new hashtable of nodes.
     */
    public FlightFinderAE(){
        nodes = new Hashtable();
    }
    /**
     * Remove a node from the graph.
     * And also remove all edges adjacent to that node.
     * 
     * @param data is the data item stored in the node to be removed
     * @return true if a vertex with data is found and removed, or
     *         false if that data value is not found in the graph
     * @throws NullPointerException if data is null
     */
    @Override
    public boolean removeNode(NodeType data) {
        return super.removeNode(data);
    }
    /**
     * Check whether the graph contains a node with the provided data.
     * 
     * @param data the node contents to check for
     * @return true if data item is stored in a node within the graph, or
               false otherwise
     */
    @Override
    public boolean containsNode(NodeType data) {
        return super.containsNode(data);
    }

    /**
     * Insert a new node into the graph.
     * 
     * @param data is the data item stored in the new node
     * @return true if the data is unique and can be inserted into a new node,
     *         or false if this data is already in the graph
     * @throws NullPointerException if data is null
     */
    @Override
    public boolean insertNode(NodeType data) {
        return super.insertNode(data);
    }

    /**
     * Insert a new directed edge with positive edges weight into the graph.
     * Or if an edge between pred and succ already exists, update the data
     * stored in hat edge to be weight.
     * 
     * @param pred is the data item contained in the new edge's predecesor node
     * @param succ is the data item contained in the new edge's successor node
     * @param weight is the non-negative data item stored in the new edge
     * @return true if the edge could be inserted or updated, or
     *         false if the pred or succ data are not found in any graph nodes
     */
    @Override
    public boolean insertEdge(NodeType pred, NodeType succ, EdgeType weight) {
        return super.insertEdge(pred, succ, weight);
    }

    /**
     * Remove an edge from the graph.
     * 
     * @param pred the data item contained in the source node for the edge
     * @param succ the data item contained in the target node for the edge
     * @return true if the edge could be removed, or
     *         false if such an edge is not found in the graph
     */
    @Override
    public boolean removeEdge(NodeType pred, NodeType succ) {
        return super.removeEdge(pred, succ);
    }

    /**
     * Check if edge is in the graph.
     * 
     * @param pred the data item contained in the source node for the edge
     * @param succ the data item contained in the target node for the edge
     * @return true if the edge is found in the graph, or false other
     */
    @Override
    public boolean containsEdge(NodeType pred, NodeType succ) {
        return super.containsEdge(pred, succ);
    }

    /**
     * Return the data associated with a specific edge.
     * 
     * @param pred the data item contained in the source node for the edge
     * @param succ the data item contained in the target node for the edge
     * @return the non-negative data from the edge between those nodes
     * @throws NoSuchElementException if either node or the edge between them
     *         are not found within this graph
     */
    @Override
    public EdgeType getEdge(NodeType pred, NodeType succ) {
        return super.getEdge(pred, succ);
    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value.  This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path.  This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    @Override
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
        return super.shortestPathData(start, end);
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path freom the node containing the start data to the node containing the
     * end data.  This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    @Override
    public double shortestPathCost(NodeType start, NodeType end) {
        return super.shortestPathCost(start, end);
        
    }
    /**
     * This method returns the shortest path(sum of weights) between two nodes, given a list of required nodes to path through
     * order doesnt matter
     * @param start the start Node
     * @param end the end Node
     * @param restraints the necessary nodes needed to be passed
     * @return shortest path(sum of weights) between two nodes, given a list of required nodes to path through
     */
    @Override
    public double shortestPathCostWithRequiredRestraints(NodeType start, NodeType end, List<NodeType> restraints) {
        //a one value array that will hold the minimum value
        double [] holder= new double[1];
        //set it to the biggest value for comparison with the Math.min operation
        holder[0]=Double.MAX_VALUE;
        //calls the helper method
        shortestPathCostWithRequiredRestraintsHelperMethod(start, end, restraints, new ArrayList<NodeType>(), holder);
        return holder[0];
    }
    /**
     * This method recursively finds all the different path permutations that pass through the restraints
     * It then modifies the input array's 1st element to be the minimum path found so far
     * @param start the start Node
     * @param end the end Node
     * @param restraints the necessary nodes needed to be passed
     * @param curr the current ordering of the restraints
     */ 
    private void shortestPathCostWithRequiredRestraintsHelperMethod(NodeType start, NodeType end,
     List<NodeType>restraints, List<NodeType>curr, double[]array){
        //when the restraint size is 0, then we have a complete path
        if(restraints.size()==0){
            //create a path that connects the start to end with the current ordering of the restraints
            List<NodeType> answer = new ArrayList<NodeType>();
            answer.add(start);
            double total = 0;
            for(NodeType node : curr){
                answer.add(node);
            }
            answer.add(end);
            //calculates the path between each node along the path
            for(int i=0;i<answer.size()-1;i++){
                total += shortestPathCost(answer.get(i), answer.get(i+1));
            }
            //checks if the current path is smaller than any other paths
            array[0] = Math.min(array[0], total);
            return;
        }
        //iterates through the current restraints left
        for(int i=0;i<restraints.size();i++){
            //removes each restraint once for a different permutation
            NodeType holder = restraints.get(i);
            restraints.remove(i);
            curr.add(holder);
            shortestPathCostWithRequiredRestraintsHelperMethod(start, end, restraints, curr, array);
            curr.remove(curr.size()-1);
            restraints.add(i, holder);
        }
    }
    /**
     * This method returns the shortest path(sum of weights) between two nodes, given a list of nodes not to path through
     * @param start the starting node
     * @param end   the ending node
     * @param restraints the nodes unable to path through
     * @return the minimum path between two nodes without pathing through the restrained nodes
     */
    @Override
    public double shortestPathCostWithRestrictedRestraints(NodeType start, NodeType end, List<NodeType> restraints) {
        //instantiates a list to store the edges to be removed
        List<Edge> edgesRemoved = new ArrayList<Edge>();
        //stores the edges and removes the edges and nodes connected to the restraints
        for(NodeType node: restraints){
            Node tempNode = nodes.get(node);
            for(Edge edge: tempNode.edgesEntering){
                edgesRemoved.add(edge);
            }
            for(Edge edge: tempNode.edgesLeaving){
                edgesRemoved.add(edge);
            }
            removeNode(node);
        }
        double ans = shortestPathCost(start, end);
        //restores the removed nodes
        for(NodeType node: restraints){
            insertNode(node);
        }
        //restores the removed edges
        for(Edge edge: edgesRemoved){
            insertEdge(edge.predecessor.data, edge.successor.data, edge.data);
        }
        return ans;
    }
    /**
     * This method returns the shortest path (with nodes) between two nodes, given a list of nodes not to path through
     * @param start the starting node
     * @param end   the ending node
     * @param restraints the nodes unable to path through
     * @return the minimum path between two nodes without pathing through the restrained nodes
     */
    @Override
    public  List<NodeType> shortestPathDataWithRestrictedRestraints(NodeType start, NodeType end, List<NodeType> restraints) {
        //instantiates a list to store the edges to be removed
        List<Edge> edgesRemoved = new ArrayList<Edge>();
        //stores the edges and removes the edges and nodes connected to the restraints
        for(NodeType node: restraints){
            Node tempNode = nodes.get(node);
            for(Edge edge: tempNode.edgesEntering){
                edgesRemoved.add(edge);
            }
            for(Edge edge: tempNode.edgesLeaving){
                edgesRemoved.add(edge);
            }
            removeNode(node);
        }
        List<NodeType> ans = shortestPathData(start, end);
        //restores the removed nodes
        for(NodeType node: restraints){
            insertNode(node);
        }
        //restores the removed edges
        for(Edge edge: edgesRemoved){
            insertEdge(edge.predecessor.data, edge.successor.data, edge.data);
        }
        return ans;
    }
    @Override
    public List<NodeType> shortestPathDataWithRequiredRestraints(NodeType start, NodeType end,
            List<NodeType> restraints) {
        //a one value array that will hold the minimum value
        double [] holder= new double[1];
        //set it to the biggest value for comparison with the Math.min operation
        holder[0]=Double.MAX_VALUE;
        ArrayList<NodeType> answer = new ArrayList<NodeType>();
        //calls the helper method
        //after it is called, answer should hold the shortest path data
        shortestPathDataWithRequiredRestraintsHelperMethod(start, end, restraints,new ArrayList<NodeType>(),holder,answer);
        return answer;
    }
    /**
     * This method recursively finds all the different path permutations that pass through the restraints
     * It then modifies the input array's 1st element to be the minimum path found so far
     * @param start the start Node
     * @param end the end Node
     * @param restraints the necessary nodes needed to be passed
     * @param curr the current ordering of the restraints
     * @param minPathData the current smallest data path
     */ 
    private void shortestPathDataWithRequiredRestraintsHelperMethod(NodeType start, NodeType end,
     List<NodeType>restraints, List<NodeType>curr, double[]array, List<NodeType>minPathData){
        //when the restraint size is 0, then we have a complete path
        if(restraints.size()==0){
            //create a path that connects the start to end with the current ordering of the restraints
            List<NodeType> answer = new ArrayList<NodeType>();
            answer.add(start);
            double total = 0;
            for(NodeType node : curr){
                answer.add(node);
            }
            answer.add(end);
            //calculates the path between each node along the path
            for(int i=0;i<answer.size()-1;i++){
                total += shortestPathCost(answer.get(i), answer.get(i+1));
            }
            //checks if the current path is smaller than any other paths
            //if it is, then replace the minimum data path with the current path
            if(array[0]>total){
                minPathData.clear();
                for(NodeType node: answer){//makes a deep copy of curr
                    minPathData.add(node);
                }
                array[0]=total;
            }
            return;
        }
        //iterates through the current restraints left
        for(int i=0;i<restraints.size();i++){
            //removes each restraint once for a different permutation
            NodeType holder = restraints.get(i);
            restraints.remove(i);
            curr.add(holder);
            shortestPathDataWithRequiredRestraintsHelperMethod(start, end, restraints, curr, array,minPathData);
            curr.remove(curr.size()-1);
            restraints.add(i, holder);
        }
    }
    /*
     * This methods returns all the nodes in the graph
     */
    @Override
    public List<NodeType> listOfAllNodes(){
	   List<NodeType> allNodes = new ArrayList<NodeType>();
	   for(NodeType node: nodes.keySet()){
		   allNodes.add(node);
	   }
	   return allNodes;
    }
}
