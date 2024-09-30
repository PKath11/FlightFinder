import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * Class to represent graph of Dijkstra
 * 
 */
public class FlightFinderAEBD<NodeType, EdgeType extends Number> implements FlightFinderAEInterfaceBD<NodeType, EdgeType> {
  // Store the nodes in graph
  private List<NodeType> nodes;

  // Store the edges in graph
  private List<List<NodeType>> edges;

  // Store the weights in graph
  private List<EdgeType> edgeWeights;

  /**
   * Constructor to init all fields
   * 
   */
  public FlightFinderAEBD() {
    // Init all fields
	  this.nodes = new ArrayList<NodeType>();
    this.edges = new ArrayList<List<NodeType>>();
    this.edgeWeights = new ArrayList<EdgeType>();
  }
  
  /**
   * Mutator function to add node to graph
   * 
   */
  public boolean insertNode(NodeType node) {
    // Add node to graph
    this.nodes.add(node);

    // Return true since added to graph
    return true;
  }
  
  /**
   * Mutator method to add edge to graph
   * 
   * @param pred starting node
   * @param succ ending node
   * @param weight the weight of the edge
   */
  public boolean insertEdge(NodeType pred, NodeType succ, EdgeType weight) {
    // Create edge with two nodes
    List<NodeType> edge = new ArrayList<NodeType>();
    edge.add(pred);
    edge.add(succ);

    // Add the edge to field
    this.edges.add(edge);
    // Add the weight to field
    this.edgeWeights.add(weight);

    // Return true since edge added
    return true;
  }
  
  /**
   * Accessor method to get total count of nodes
   * 
   * @return the total nodes in graph
   */
  public int getNodeCount() {
    // The total nodes in field
    return this.nodes.size();
  }
  
  /**
   * Mutator method to remove edge from graph
   * 
   */
  public boolean removeEdge(NodeType pred, NodeType succ) {
    // Remove edge from graph and ensure it happened successfully
    return this.edges.remove(this.edges.size() - 1) != null;
  }
  
  /**
   * Accessor method to check if edge exists in graph
   * 
   * @param pred start node
   * @param succ end node
   * @return true if edge in graph, else return false
   */
  public boolean containsEdge(NodeType pred, NodeType succ) {
    // Iterate through edges in graph
    for(List<NodeType> edge : this.edges) {
      // Check if edge exists and return true if so
      if(edge.get(0).equals(pred) && edge.get(1).equals(succ)) {
        return true;
      }
    }

    // Return false if edge is not in graph
    return false;
  }

  /**
   * Accessor method to get edge from graph
   * 
   * @param pred start node
   * @param succ end node
   * @return the weight of edge in graph
   */
  public EdgeType getEdge(NodeType pred, NodeType succ) {
    // Iterate through edges in graph
    for(List<NodeType> edge : this.edges) {
      // Check if edge exists
      if(edge.get(0).equals(pred) && edge.get(1).equals(succ)) {
        // Return the corresponding weight in graph
        return this.edgeWeights.get(this.edges.indexOf(edge));
      }
    }

    // Throw NoSuchElementException since edge not in graph
    throw new NoSuchElementException("Edge not in graph");
  }
  
  /**
   * Accessor method to get total number of edges
   * 
   * @return the count of edges
   */
  public int getEdgeCount() {
    // Return the size of field edges list
    return this.edges.size();
  }
  
  /**
   * Method to return the shortest path in graph
   * 
   * @return a list of nodes in path
   */
  public List<NodeType> shortestPathData(NodeType start, NodeType end) {
    // For week 3, just return all nodes 
    return this.listOfAllNodes();
  }

  /**
   * Method to return the cost of shortest path
   * 
   * @return cost of path
   */
  public double shortestPathCost(NodeType start, NodeType end) {
    // Init to 0.0
    Double total = 0.0;

    // Iterate through all edge weights
    for (EdgeType weight: edgeWeights) {
      // Add current weight to total
      total += weight.doubleValue();
    }

    // Return the total cost
    return total;
  }
  
  /**
   * Method to return the shortest path cost in graph
   * with restraints
   * 
   * @return the cost of path with restraints
   */
  public double shortestPathCostWithRequiredRestraints(NodeType start, NodeType end, List<NodeType> restraints) {
    // For week 3, just return shortest path cost
    return this.shortestPathCost(start, end);
  }
  
  /**
   * Method to return the shortest path cost in graph
   * with restricted restraints
   * 
   * @return the cost of path with restraints
   */
  public double shortestPathCostWithRestrictedRestraints(NodeType start, NodeType end, List<NodeType> restraints) {
    // For week 3, just return shortest path cost
    return this.shortestPathCost(start, end);
  }
  
  /**
   * Method to return the shortest path in graph
   * with restricted restraints
   * 
   * @return the path with restricted restraints
   */
  public List<NodeType> shortestPathDataWithRestrictedRestraints(NodeType start, NodeType end, List<NodeType> restraints) {
    // For week 3, just return all nodes
    return this.listOfAllNodes();
  }
  
  /**
   * Method to return the shortest path in graph
   * with required restraints
   * 
   * @return the path with required restraints
   */
  public List<NodeType> shortestPathDataWithRequiredRestraints(NodeType start, NodeType end, List<NodeType> restraints) {
    // For week 3, just return all nodes
    return this.listOfAllNodes();
  }

  /**
   * Accessor method to list all nodes
   * 
   * @return field nodes
   */
  public List<NodeType> listOfAllNodes() {
    // Return the field nodes
    return this.nodes;
  }



  /**
   * Mutator method to remove node from graph
   * 
   * @return true if node removed, else false
   */
  @Override
  public boolean removeNode(NodeType data) {
    // Remove node from field array
    return this.nodes.remove(data);
  }

  /**
   * Accessor method to check if node is in graph
   * 
   * @return true if node exists, else false
   */
  @Override
  public boolean containsNode(NodeType data) {
    // Check if node data is in array
    return this.nodes.contains(data);
  }
}
