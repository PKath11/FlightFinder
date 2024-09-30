import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Placeholder class for the Flight Finder backend
 */

public class FlightFinderBackendFD implements FlightFinderBackendInterface {
    private List<AirportInterface> airports;

    /**
     * Constructor for the class, sets the list of AirportInterfaces
     * @param airports, sets this to the AirportInterface list field
     */

    public FlightFinderBackendFD(List<AirportInterface> airports) {
        this.airports = airports;
    }

    /**
     * Loads the file with filename
     * @param filename, name of the file
     * @throws FileNotFoundException if file not valid
     */
    @Override
    public void loadData(String filename) throws FileNotFoundException {
        if (filename.equals("TestFile.data")) {
            return;
        } else {
            throw new FileNotFoundException();
        }
    }

    /**
     * Getter for the list of AirportInterface field
     * @return airports
     */
    @Override
    public List<AirportInterface> getAirports() {
        return airports;
    }

    /**
     * Calculates and returns the shortest path between two airports
     * @param airport1 - starting Airport
     * @param airport2 - ending Airport
     * @return - list of Airports that make up the path
     * @throws NoSuchElementException if no such path exists
     */
    @Override
    public List<AirportInterface> getPath(AirportInterface airport1, AirportInterface airport2) throws NoSuchElementException {
        List<AirportInterface> list = new ArrayList<>();
        AirportInterface Airport1 = new AirportFD("airport 1", "To: 5,2,3");
        AirportInterface Airport2 = new AirportFD("airport 2", "To: 1,4");
        AirportInterface Airport3 = new AirportFD("airport 3", "To: 1,4");
        list.add(Airport1);
        list.add(Airport2);
        list.add(Airport3);

        if (airport2.getAirport().equals("airport 4")) {
            throw new NoSuchElementException();
        }
        return list;
    }

    /**
     * Calculates and returns the shortes path with restricted restraints
     * @param airport1 - starting Airport
     * @param airport2 - ending Airport
     * @param restraints - list of Airports in the restraint
     * @return - list of Airports that make up the path
     * @throws NoSuchElementException if no such path exists
     */

    @Override
    public List<AirportInterface> getPathWithRestrictedRestraints(AirportInterface airport1, AirportInterface airport2, List<AirportInterface> restraints) throws NoSuchElementException {

        List<AirportInterface> list = new ArrayList<>();
        AirportInterface Airport1 = new AirportFD("airport 1", "To: 5,2,3");
        AirportInterface Airport2 = new AirportFD("airport 2", "To: 1,4");
        AirportInterface Airport3 = new AirportFD("airport 3", "To: 1,4");
        AirportInterface Airport4 = new AirportFD("airport 4", "To: 2,3");
        AirportInterface Airport5 = new AirportFD("airport 5", "To: 1");
        list.add(Airport5);
        list.add(Airport1);
        list.add(Airport2);
        list.add(Airport4);

        for (int i = 0; i < restraints.size(); i ++) {
            if (restraints.get(i).getAirport().equals("airport 1")) {
                throw new NoSuchElementException("here");
            }
        }

        return list;
    }

    /**
     * Calculates and returns the shortes path with required restraints
     * @param airport1 - starting Airport
     * @param airport2 - ending Airport
     * @param restraints - list of Airports in the restraint
     * @return - list of Airports that make up the path
     * @throws NoSuchElementException if no such path exists
     */

    @Override
    public List<AirportInterface> getPathWithRequiredRestraints(AirportInterface airport1, AirportInterface airport2, List<AirportInterface> restraints) throws NoSuchElementException {
        List<AirportInterface> list = new ArrayList<>();
        AirportInterface Airport1 = new AirportFD("airport 1", "To: 5,2,3");
        AirportInterface Airport2 = new AirportFD("airport 2", "To: 1,4");
        AirportInterface Airport3 = new AirportFD("airport 3", "To: 1,4");
        AirportInterface Airport4 = new AirportFD("airport 4", "To: 2,3");
        AirportInterface Airport5 = new AirportFD("airport 5", "To: 1");
        list.add(Airport5);
        list.add(Airport1);
        list.add(Airport2);
        list.add(Airport4);

        for (int i = 0; i < restraints.size(); i ++) {
            if (restraints.get(i).getAirport().equals("airport 1")) {
                throw new NoSuchElementException("here");
            }
        }

        return list;


    }


    /**
     * Removes an airport
     * @param airport to remove
     * @throws NoSuchElementException if no such airport exists
     */
    @Override
    public void removeAirport(AirportInterface airport) throws NoSuchElementException {


        if (!airport.getAirport().equals("airport 2")) {
            throw new NoSuchElementException();
        }

    }

    /**
     * Removes an edge between to airports
     * @param pred the first airport of the edge
     * @param succ the second airport of the edge
     * @throws NoSuchElementException if no such edge exists
     */
    @Override
    public void removeAirportEdge(AirportInterface pred, AirportInterface succ) throws NoSuchElementException {




        AirportFD Airport1 = new AirportFD("airport 1", "To: 5,2,3");
        AirportFD Airport2 = new AirportFD("airport 2", "To: 1,4");
        AirportFD Airport3 = new AirportFD("airport 3", "To: 1,4");
        AirportFD Airport4 = new AirportFD("airport 4", "To: 2,3");
        AirportFD Airport5 = new AirportFD("airport 5", "To: 1");




        if (pred.equals(succ) || (pred.getAirport().equals("airport 1") && succ.getAirport().equals("airport 4"))) {
            throw new NoSuchElementException();
        } else {
            return;
        }



    }

    /**
     * Returns the cost of the shortest path between two airports
     * @param start starting airport of path
     * @param end ending airport of path
     * @return the cost
     * @throws NoSuchElementException if no such path exists
     */
    @Override
    public double shortestPathCost(AirportInterface start, AirportInterface end) throws NoSuchElementException {
        return 2.0;
    }

    /**
     * Returns the cost of the shortest path with restricted restraints between two airports
     * @param start starting airport of path
     * @param end ending airport of path
     * @return the cost
     * @throws NoSuchElementException if no such path exists
     */
    @Override
    public double shortestPathCostWithRestrictedRestraints(AirportInterface start, AirportInterface end, List<AirportInterface> restraints) throws NoSuchElementException{
        return 6.0;
    }

    /**
     * Returns the cost of the shortest path with required restraints between two airports
     * @param start starting airport of path
     * @param end ending airport of path
     * @return the cost
     * @throws NoSuchElementException if no such path exists
     */
    @Override
    public double shortestPathCostWithRequiredRestraints(AirportInterface start, AirportInterface end, List<AirportInterface> restraints) throws NoSuchElementException {
        return 6.0;
    }


}
