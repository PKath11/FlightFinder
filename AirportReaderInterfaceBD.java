import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface AirportReaderInterfaceBD {
	//public AirportReaderDW();
	public ArrayList<AirportInterface> readFlightsFromFile(String filename) throws
			FileNotFoundException;
}
