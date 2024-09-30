
import java.util.List;

/**
 * Interface for the FlightFinder Frontend
 */

public interface FlightFinderFrontendInterface {
    //public FFFrontendInterface(FFBackendInterface backend, Scanner input)
    public void runCommandLoop();
    public char mainMenuPrompt();
    public void loadData();
    public AirportInterface chooseAirportToRemove();
    public void removeAirport(AirportInterface airport);
    public List<AirportInterface> chooseEdgeToRemove();
    public void removeEdge(List<AirportInterface> EdgeToRemove);
    public List<AirportInterface> chooseAirports();

    public void getShortestPath(List<AirportInterface> airports);
    public void getShortestPathWithRequiredRestraint(List<AirportInterface> airports, List<AirportInterface> restraints);

    public List<AirportInterface> chooseRequiredRestraints();

    public void getShortestPathWithRestrictedRestraint(List<AirportInterface> airports, List<AirportInterface> restraints);

    public List<AirportInterface> chooseRestrictedRestraints();
}
