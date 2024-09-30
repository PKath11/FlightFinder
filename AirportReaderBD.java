import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;;

/**
 * Class to represent the reader of an airport
 * 
 */
public class AirportReaderBD implements AirportReaderInterface {
  /**
   * Method to create a list of airports from a file
   */
  public ArrayList<AirportInterface> readFlightsFromFile(String filename) 
                                              throws FileNotFoundException {
    // Init a list of airports
    ArrayList<AirportInterface> list = new ArrayList<AirportInterface>();

    // Create three airports with edges
    AirportBD airport1 = new AirportBD("Airport1", "", "");
    airport1.setPath("Airport1 -> Airport2, Airport1 -> Airport3");
    airport1.setPathsWeights("[weight=1], [weight=2]");

    AirportBD airport2 = new AirportBD("Airport2", "", "");
    airport2.setPath("Airport2 -> Airport1");
    airport2.setPathsWeights("[weight=3]");

    AirportBD airport3 = new AirportBD("Airport3", "", "");
    airport3.setPath("Airport3 -> Airport2");
    airport3.setPathsWeights("[weight=3]");


    // Add three airports to list
    list.add(airport1);
    list.add(airport2);
    list.add(airport3);

    // Return the list
    return list;
  }
}
