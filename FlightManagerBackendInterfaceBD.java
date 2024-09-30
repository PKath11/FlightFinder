import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Interface to represent the backend flight manager
 * 
 */
public interface FlightManagerBackendInterfaceBD {

	// public FlightManagerBackend(FlightInterface flightData);
	public void loadData(String filename) throws FileNotFoundException;
	public List<AirportInterface> getAirports();
	public List<AirportInterface> getPath(AirportInterface airport1,
										  AirportInterface airport2) throws NoSuchElementException;
	public List<AirportInterface> getPathWithRestrictedRestraints(AirportInterface airport1,
																  AirportInterface airport2,
																  List<AirportInterface> restraints) throws NoSuchElementException;

	public List<AirportInterface> getPathWithRequiredRestraints(AirportInterface airport1,
																AirportInterface airport2,
																List<AirportInterface> restraints) throws NoSuchElementException;


	public String toString();
	public void removeAirport(AirportInterface airport) throws NoSuchElementException;
	public void removeAirportEdge(AirportInterface pred, AirportInterface succ) throws NoSuchElementException;

	public double shortestPathCost(AirportInterface start, AirportInterface end) throws NoSuchElementException;

	public double shortestPathCostWithRestrictedRestraints(AirportInterface start, AirportInterface end, List<AirportInterface> restraints) throws NoSuchElementException;

	public double shortestPathCostWithRequiredRestraints(AirportInterface start, AirportInterface end, List<AirportInterface> restraints) throws NoSuchElementException;


}
