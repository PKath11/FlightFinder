/**
 * Placeholder for the Airport class
 */
public class AirportFD implements AirportInterface {

    String airport;
    String path;

    /**
     * Constructor for Airport placeholder
     * @param airport, sets airport field to this
     * @param path, sets path field to this
     */
    public AirportFD(String airport, String path) {
        this.airport = airport;
        this.path = path;
    }

    /**
     * Getter for airport field
     * @return airport field
     */
    @Override
    public String getAirport() {
        return this.airport;
    }

    /**
     * Getter for path field
     * @return path field
     */
    @Override
    public String getPaths() {
        return this.path;
    }

    @Override
    public String getPathsWeights() {
        return null;
    }

    /**
     * Setter for the airport field
     * @param airport, set airport to this
     */

    @Override
    public void setAirport(String airport) {
        this.airport = airport;
    }

    /**
     * Setter for the path field
     * @param path, set path to this
     */

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void setPathsWeights(String pathWeights) {

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
