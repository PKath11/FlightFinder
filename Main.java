import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    AirportReaderDW reader = new AirportReaderDW();

    FlightManagerBackendBD backendBD = new FlightManagerBackendBD(reader);

    Scanner scanner = new Scanner(System.in);

    FlightFinderFrontendFD frontend = new FlightFinderFrontendFD(backendBD, scanner);

    frontend.runCommandLoop();
  }
}