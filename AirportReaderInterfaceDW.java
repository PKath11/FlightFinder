import java.io.IOException;
import java.util.ArrayList;

public interface AirportReaderInterfaceDW {
  //public AirportReaderDW();
  public ArrayList<AirportInterface> readFlightsFromFile(String filename) throws IOException;
}
