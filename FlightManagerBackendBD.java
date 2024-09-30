import java.util.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Backend class to manage all flights and pass to frontend
 * 
 */
public class FlightManagerBackendBD implements FlightFinderBackendInterface {
  // Private field to store the path finding logic
  FlightFinderAEInterface<AirportInterface, Double> graph;
  // Reader to read airports from a file
  AirportReaderInterface reader;

  /**
   * Constructor to init the fields
   *
   * @param reader reader to read airports from files
   */
  public FlightManagerBackendBD(AirportReaderInterface reader) {
    // Set fields to corresponding params
    this.graph = new FlightFinderAE<>();
    this.reader = reader;
  }

  /**
   * Method to load the data from a filename
   * 
   * @throws FileNotFoundException if file does not exist
   */
  public void loadData(String filename) throws FileNotFoundException {
    // Use try catch to catch IO exception
//    try {
      // Reset graph
      this.graph = new FlightFinderAE<>();

      // Get list of airports from reader
      List<AirportInterface> airports = reader.readFlightsFromFile(filename);

      // Iterate through list of airports
      for (AirportInterface airport : airports) {
        // Add each airport to dataStructure
        this.graph.insertNode(airport);
      }

      // Iterate through airports in the graph
      for (AirportInterface airport : this.graph.listOfAllNodes()) {
        // Split the paths by comma
        String[] paths = airport.getPaths().split(",");

        // Split the weights by comma
        String[] weights = airport.getPathsWeights().split(",");

        // Iterate through paths
        for (int i = 0; i < paths.length; i++) {
          // Get path components split by arrow
          String[] path = paths[i].split("->");
          // Get weight components split by :
          String[] weight = weights[i].split("=");

          // Get airport destination
          AirportInterface nextAirport = 
                getAirportByNameHelperMethod(path[1].trim());

          // Remove "]" from weight string
          weight[1] = weight[1].substring(0, weight[1].indexOf("]"));

          // Add edge
          this.graph.insertEdge(airport, 
              nextAirport, 
              Double.parseDouble(weight[1].trim()));
        }
      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }

  /**
   * Helper method to get airport given string 
   * 
   * @param name the name of airport to get
   * @return the airport based on name
   * @throws NoSuchElementException if the airport is not in graph
   */
  private AirportInterface getAirportByNameHelperMethod(String name)
                                      throws NoSuchElementException {
    // Iterate through all airports in graph
    for (AirportInterface airport : this.graph.listOfAllNodes()) {
      // Check if name matches airport
      if (name.trim().equals(airport.getAirport().trim())) {
        // If so, return airport instance
        return airport;
      }
    }

    // Otherwise, throw NoSuchElementException
    throw new NoSuchElementException("Airport does not exist in graph");
  }

  /**
   * Method to return all of the airports
   *
   * @return a list of all airports
   */
  public List<AirportInterface> getAirports() {
    // Return a list of all the nodes

    List<AirportInterface> res = this.graph.listOfAllNodes();
    Collections.sort(res);
    return res;
  }
  /**
   * Method to get a path between the two airpots
   * 
   * @param airport1 start airport
   * @param airport2 end airport
   */
  public List<AirportInterface> getPath(
              AirportInterface airport1, 
              AirportInterface airport2) {
    // Get the airport instances from graph
    AirportInterface a1 = this.getAirportByNameHelperMethod(
      airport1.getAirport()
      );
    AirportInterface a2 = this.getAirportByNameHelperMethod(
      airport2.getAirport()
      );

    // Get the path between the two airports
    List<AirportInterface> path = this.graph.shortestPathData(a1, a2);

    // Return this path to be used by frontend
    return path;
  }

  @Override
  public List<AirportInterface> getPathWithRestrictedRestraints(
    AirportInterface airport1, AirportInterface airport2, 
    List<AirportInterface> restraints) throws NoSuchElementException {
    // Get the airport instances from graph
    AirportInterface a1 = 
      this.getAirportByNameHelperMethod(airport1.getAirport());
    AirportInterface a2 = 
      this.getAirportByNameHelperMethod(airport2.getAirport());

    // Init list of airports to store restraints from graph
    List<AirportInterface> actualRestraints = new ArrayList<>();

    // Iterate through restraints
    for (AirportInterface a : restraints) {
      // Add airport from graph to actual restraints
      actualRestraints.add(this.getAirportByNameHelperMethod(a.getAirport()));
    }

    // Get the path between the two airports
    List<AirportInterface> path = 
      this.graph.shortestPathDataWithRestrictedRestraints(
        a1, 
        a2, 
        restraints
      );

    // Return this path to be used by frontend
    return path;
  }

  /**
   * Method to get a path between the two airpots with a list of 
   * required restraints
   * 
   * @param airport1 start airport
   * @param airport2 end airport
   * @param restraints the restraints that the path must go through
   */
  @Override
  public List<AirportInterface> getPathWithRequiredRestraints(
      AirportInterface airport1, 
      AirportInterface airport2, 
      List<AirportInterface> restraints) throws NoSuchElementException {
    // Get the airport instances from graph
    AirportInterface a1 = 
      this.getAirportByNameHelperMethod(airport1.getAirport());
    AirportInterface a2 = 
      this.getAirportByNameHelperMethod(airport2.getAirport());

    // Init list of airports to store restraints from graph
    List<AirportInterface> actualRestraints = new ArrayList<>();

    // Iterate through restraints
    for (AirportInterface a : restraints) {
      // Add airport from graph to actual restraints
      actualRestraints.add(this.getAirportByNameHelperMethod(a.getAirport()));
    }

    // Get the path between the two airports
    List<AirportInterface> path = 
      this.graph.shortestPathDataWithRequiredRestraints(
        a1, 
        a2, 
        actualRestraints
      );

    // Return this path to be used by frontend
    return path;
  }


  /**
   * Method to get a path between the two airpots with a list of 
   * restraints
   * 
   * @param airport1 start airport
   * @param airport2 end airport
   * @param restraints the restraints that the path must go through
   */
  public List<AirportInterface> getPathWithRestraints(
                        AirportInterface airport1, 
                        AirportInterface airport2, 
                        List<AirportInterface> restraints) {
    // Get the airport instances from graph
    AirportInterface a1 = 
      this.getAirportByNameHelperMethod(airport1.getAirport());
    AirportInterface a2 = 
      this.getAirportByNameHelperMethod(airport2.getAirport());

    // Init list of airports to store restraints from graph
    List<AirportInterface> actualRestraints = new ArrayList<>();

    // Iterate through restraints
    for (AirportInterface a : restraints) {
      // Add airport from graph to actual restraints
      actualRestraints.add(this.getAirportByNameHelperMethod(a.getAirport()));
    }

    // Get the path between two airports with restraints
    List<AirportInterface> path = 
        this.graph.shortestPathDataWithRequiredRestraints(
          a1, 
          a2, 
          actualRestraints
        );

    // Return this path to be used by frontend
    return path;
  }

  /**
   * Method to represent backend as a string
   * 
   * @return string representation of backend
   */
  @Override
  public String toString() {
    // Init a blank string
    String finalString = "";

    // Check if the list is empty
    if (this.graph.listOfAllNodes().isEmpty()) {
      // If so, return string saying no airports
      return "No Airports";
    }

    // Iterate through all airports in the backend graph
    for (AirportInterface airport : this.graph.listOfAllNodes()) {
      // Add current airport to the string
      finalString += airport.getAirport();

      // Only add new line character if not last
      int last = this.graph.listOfAllNodes().size() - 1;
      if (this.graph.listOfAllNodes().indexOf(airport) != last) {
        finalString += "\n";
      }
    }

    // Return the string containing all airports
    return finalString;
  }

  /**
   * Method to remove an airport from the graph
   * 
   * @param airport the airport to remove from graph
   * @throws NoSuchElementException if node not removed correctly
   */
  public void removeAirport(AirportInterface airport) 
                          throws NoSuchElementException {
    // Get the airport instance from graph
    AirportInterface a1 = 
      this.getAirportByNameHelperMethod(airport.getAirport());

    // Remove node and check if removed successfully
    if (!this.graph.removeNode(a1)) {
      // If not, throw NoSuchElementException
      throw new NoSuchElementException("Airport not found");
    }
  }

  /**
   * Method to remove an edge from the graph
   * 
   * @param pred the starting node of edge to remove
   * @param succ the ending node of edge to remove
   * @throws NoSuchElementException if edge not removed correctly
   */
  public void removeAirportEdge(AirportInterface pred, AirportInterface succ) 
                                              throws NoSuchElementException {
    
    // Get the airport instances from graph
    AirportInterface a1 = 
      this.getAirportByNameHelperMethod(pred.getAirport());
    AirportInterface a2 = 
      this.getAirportByNameHelperMethod(succ.getAirport());
                                                
    // Remove edge and check if removed successfully
    if (!this.graph.removeEdge(a1, a2)) {
      // If not, throw NoSuchElementException
      throw new NoSuchElementException("Edge not found");
    }
  }

  /**
   * Method to return the shortest path cost between airports given restraints
   * 
   * @return the cost of the shortest path with restraints
   */
  public double shortestPathCostWithRestraints(
              AirportInterface start, 
              AirportInterface end, 
              List<AirportInterface> restraints) {
    // Get the airport instances from graph
    AirportInterface a1 = 
      this.getAirportByNameHelperMethod(start.getAirport());
    AirportInterface a2 = 
      this.getAirportByNameHelperMethod(end.getAirport());

    // Init list of airports to store restraints from graph
    List<AirportInterface> actualRestraints = new ArrayList<>();

    // Iterate through restraints
    for (AirportInterface a : restraints) {
      // Add airport from graph to actual restraints
      actualRestraints.add(this.getAirportByNameHelperMethod(a.getAirport()));
    }

    // Return the cost of the path from start to end with restraints
    return this.graph.shortestPathCostWithRequiredRestraints(
      a1, 
      a2, 
      actualRestraints
    );
  }	

  /**
   * Method to return the shortest path cost between airports
   * 
   * @return the cost of the shortest path
   */
  public double shortestPathCost(
    AirportInterface start, 
    AirportInterface end) {
    // Get the airport instances from graph
    AirportInterface a1 = 
        this.getAirportByNameHelperMethod(start.getAirport());
    AirportInterface a2 = 
        this.getAirportByNameHelperMethod(end.getAirport());

    // Return the cost of the path from start to end
    return this.graph.shortestPathCost(a1, a2);
  }

  @Override
  public double shortestPathCostWithRestrictedRestraints(
        AirportInterface start, 
        AirportInterface end, 
        List<AirportInterface> restraints) throws NoSuchElementException {
    // Get the airport instances from graph
    AirportInterface a1 = 
        this.getAirportByNameHelperMethod(start.getAirport());
    AirportInterface a2 = 
        this.getAirportByNameHelperMethod(end.getAirport());

    // Init list of airports to store restraints from graph
    List<AirportInterface> actualRestraints = new ArrayList<>();

    // Iterate through restraints
    for (AirportInterface a : restraints) {
      // Add airport from graph to actual restraints
      actualRestraints.add(this.getAirportByNameHelperMethod(a.getAirport()));
    }

    // Return the cost of the path from start to end
    return this.graph.shortestPathCostWithRestrictedRestraints(
      a1, 
      a2, 
      actualRestraints
    );
  }

  @Override
  public double shortestPathCostWithRequiredRestraints(
        AirportInterface start, 
        AirportInterface end, 
        List<AirportInterface> restraints) throws NoSuchElementException {
    // Get the airport instances from graph
    AirportInterface a1 = 
        this.getAirportByNameHelperMethod(start.getAirport());
    AirportInterface a2 = 
        this.getAirportByNameHelperMethod(end.getAirport());

    // Init list of airports to store restraints from graph
    List<AirportInterface> actualRestraints = new ArrayList<>();

    // Iterate through restraints
    for (AirportInterface a : restraints) {
      // Add airport from graph to actual restraints
      actualRestraints.add(this.getAirportByNameHelperMethod(a.getAirport()));
    }

    // Return the cost of the path from start to end
    return this.graph.shortestPathCostWithRequiredRestraints(
      a1, 
      a2, 
      actualRestraints
    );
  }
}
