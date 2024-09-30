/**
 * Interface to represent an Airport
 * 
 */
public interface AirportInterfaceBD {
	public String getAirport();
	public String getPaths();
	public String getPathsWeights();
	public void setAirport(String airport);
	public void setPath(String path);
	public void setPathsWeights(String pathWeights);
	public String toString();
}
