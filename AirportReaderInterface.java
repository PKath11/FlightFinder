import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface AirportReaderInterface {
  //public AirportReaderDW();
  public List<AirportInterface> readFlightsFromFile(String filename) throws FileNotFoundException;
}
