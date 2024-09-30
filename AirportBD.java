
/**
 * Sample class for Airport
 */
public class AirportBD implements AirportInterface {
  private String airports;
  private String paths;
  private String pathWeights;

  /**
   * Constructor to create airport
   * 
   *
   */
  public AirportBD(String airports, String paths, String pathWeights) {
    // Set fields to corresponding params
    this.airports = airports;
    this.paths = paths;
    this.pathWeights = pathWeights;
  }

  /**
   * Accessor method to return the name of the airport
   * 
   * @return field name of airport
   */
	public String getAirport() {
    // Return field airport
    return this.airports;
  }

  /**
   * Accessor method to return the paths of the airport
   * 
   * @return the field paths
   */
	public String getPaths() {
    // Return the path of the airport
    return this.paths;
  }

  /**
   * Accessor method to return the paths weights
   * 
   * @return the field paths
   */
  public String getPathsWeights() {
    // Return the path weights of airport
    return this.pathWeights;
  }

  /**
   * Mutator method to change the name of the airport
   * 
   */
	public void setAirport(String airport) {
    // Set field airport to param
    this.airports = airport;
  }

  /**
   * Mutator method to change the path of airport
   * 
   */
	public void setPath(String path) {
    // Set field path to param
    this.paths = path;
  }

  /**
   * Mutator method to change the path weights of airport
   * 
   */
  public void setPathsWeights(String pathWeights) {
    // Set field path to param
    this.pathWeights = pathWeights;
  }



  /**
   * Method to return a string representation of the airport
   * 
   */
  @Override
	public String toString() {
    return "Airport: " + this.airports;
  }

  /**
   * CompareTo method for Airports
   * @param o the object to be compared.
   * @return integer indicating the compareTo result
   */
  @Override
  public int compareTo(AirportInterface o) {
    return this.getAirport().compareTo(o.getAirport());
  }
}
