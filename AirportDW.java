public class AirportDW implements AirportInterface {
  private String airport;
  private String paths;
  private String pathWeights;

  public AirportDW(String airport, String paths, String pathWeights) {

    this.airport = airport;
    this.paths = paths;
    this.pathWeights = pathWeights;
  }

  @Override
  public String getAirport() {
    return airport;
  }

  @Override
  public String getPaths() {
    return paths;
  }

  @Override
  public String getPathsWeights() {
    return pathWeights;
  }

  @Override
  public void setAirport(String airport) {
    this.airport = airport;
  }

  @Override
  public void setPath(String paths) {
    this.paths = paths;

  }

  @Override
  public void setPathsWeights(String pathWeights) {

      this.pathWeights = pathWeights;

  }



  @Override
  public String toString() {
    String toReturn = new String();
    toReturn += "Airport: " + airport + "\n";
    toReturn += "Paths: " + paths+ "\n";
    toReturn += "Path Weights: " + pathWeights;
    return toReturn;

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
