

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes.  This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
    extends BaseGraph<NodeType,EdgeType>
    implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph.  The final node in this path is stored in it's node
     * field.  The total cost of this path is stored in its cost field.  And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in it's node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;
        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }
        public int compareTo(SearchNode other) {
            if( cost > other.cost ) return +1;
            if( cost < other.cost ) return -1;
            return 0;
        }
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations.  The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *         or when either start or end data do not correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) {
        //checks whether start and end are part of the graph
        //throws NoSuchElementException if either one is not in graph
        if(!containsNode(end)||!containsNode(start)){
            throw new NoSuchElementException("The input are not valid starting or ending positions.");
        }
        //creates a hashtable that stores the Node's that has been visited
        Hashtable<NodeType,Integer> table = new Hashtable<NodeType,Integer>();
        //creates a priority queue on SearchNodes
        PriorityQueue<SearchNode> pQueue = new PriorityQueue<SearchNode>();
        //initialized a searchNode with the starting node as parameter and adds it to the priority queue
        SearchNode startNode = new SearchNode(nodes.get(start), 0, null);
        pQueue.add(startNode);
        table.put(start, 1);
        //iteratively loop through thr priority queue to find he minimum path for the nodes found
        while(!pQueue.isEmpty()){
            SearchNode removedNode =  pQueue.remove();
            if(removedNode.node.data.equals(end)){
                return removedNode;
            }
            table.put(removedNode.node.data, 1);
            for(Edge edge : removedNode.node.edgesLeaving){
                if(!table.containsKey(edge.successor.data)){
                    pQueue.add(new SearchNode(edge.successor, edge.data.doubleValue()+removedNode.cost, removedNode));
                }
                
            }
        }
        throw new NoSuchElementException("There is no such path between the given nodes");
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
    public List<NodeType> shortestPathData(NodeType start, NodeType end) throws NoSuchElementException{
        //initialized list to return
       List<NodeType>ans = new ArrayList<NodeType>();
       //get the searchnode that ends with the end Node
       SearchNode searchNode = computeShortestPath(start, end);
       //adds the each data in the searchNode to the list in the right order
       while(searchNode!=null){
        ans.add(0,searchNode.node.data);
        searchNode = searchNode.predecessor;
       }
       return ans;
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
    public double shortestPathCost(NodeType start, NodeType end) throws NoSuchElementException{
        return computeShortestPath(start, end).cost;
    }

  
}