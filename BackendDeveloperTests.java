import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

/**
 * Class to test all of the BackendDeveloper code
 */
public class BackendDeveloperTests {
  /**
   * This test tests the validity of loadData function and getAirports
   * function for the backend
   * 
   */
  @Test
  public void test1() {
    // Init the airport reader
    AirportReaderBD reader = new AirportReaderBD();

    // Init the backend with reader
    FlightManagerBackendBD backend = new FlightManagerBackendBD(reader);

    // Use try catch to catch FileNotFoundException
    try {
      // Load the data into the backend
      backend.loadData("AirportBD.java");
    } catch (FileNotFoundException e) {
      // If error, assert false statement to make test fail
      assertEquals(1, 2);
    }

    // Ensure that there are 3 elements in total airports list
    assertEquals(3, backend.getAirports().size());

    // Store the actual result for all airports
    String actualAirports = 
              "Airport: Airport1\nAirport: Airport2\nAirport: Airport3\n";

    // Init string to store airports in backend
    String airports = "";
    // Iterate through all airports in backend
    for (AirportInterface airport : backend.getAirports()) {
      // Append airport to airports string
      airports += airport.toString() + "\n";
    }

    // Ensure that actual and expected are equal
    assertEquals(actualAirports, airports);
  }

  /**
   * This test tests the validity of the getPath function in the
   * backend
   * 
   */
  @Test
  public void test2() {
    // Init the airport reader
    AirportReaderBD reader = new AirportReaderBD();

    // Init the backend with reader
    FlightManagerBackendBD backend = new FlightManagerBackendBD(reader);

    // Use try catch to catch FileNotFoundException
    try {
      // Load the data into the backend
      backend.loadData("AirportBD.java");
    } catch (FileNotFoundException e) {
      // If error, assert false statement to make test fail
      assertEquals(1, 2);
    }

    // Store the actual path 
    String actualPath = 
              "[Airport: Airport1, Airport: Airport2]";

    // Init two airport instances
    AirportInterface airport1 = new AirportBD("Airport1", "", "");
    AirportInterface airport2 = new AirportBD("Airport2", "", "");

    // Get the path from airport 1 to 2 as a string
    String path = backend.getPath(airport1, airport2).toString();

    // Ensure that path is expected
    assertEquals(actualPath, path);
  }

  @Test
  public void test3() {
    // Init the airport reader
    AirportReaderBD reader = new AirportReaderBD();

    // Init the backend with reader
    FlightManagerBackendBD backend = new FlightManagerBackendBD(reader);

    // Use try catch to catch FileNotFoundException
    try {
      // Load the data into the backend
      backend.loadData("AirportBD.java");
    } catch (FileNotFoundException e) {
      // If error, assert false statement to make test fail
      assertEquals(1, 2);
    }

    String actualPath = 
              "[Airport: Airport1, Airport: Airport2]";

    // Init two airport instances
    AirportInterface airport1 = new AirportBD("Airport1", "", "");
    AirportInterface airport2 = new AirportBD("Airport2", "", "");

    // Init the list of restraints
    List<AirportInterface> restraints = new ArrayList<AirportInterface>();

    // Get the path with restraints from 1 to 2
    String path = 
      backend.getPathWithRestraints(airport1, airport2, restraints).toString();

    // Ensure that path is expected
    assertEquals(actualPath, path);
  }

  @Test
  public void test4() {
    // Init the airport reader
    AirportReaderBD reader = new AirportReaderBD();

    // Init the backend with reader
    FlightManagerBackendBD backend = new FlightManagerBackendBD(reader);

    // Use try catch to catch FileNotFoundException
    try {
      // Load the data into the backend
      backend.loadData("AirportBD.java");
    } catch (FileNotFoundException e) {
      // If error, assert false statement to make test fail
      assertEquals(1, 2);
    }

    // Init the actual cost of path
    Double actualCost = 1.0;
    
    // Init two airport instances
    AirportBD airport1 = new AirportBD("Airport1", "", "");
    AirportBD airport2 = new AirportBD("Airport2", "", "");

    // Get the cost of path from 1 to 2
    Double cost =
          backend.shortestPathCost(airport1, airport2);

    // Ensure that path is expected
    assertEquals(actualCost, cost);
  }

  @Test
  public void test5() {
    // Init the airport reader
    AirportReaderBD reader = new AirportReaderBD();

    // Init the backend with reader
    FlightManagerBackendBD backend = new FlightManagerBackendBD(reader);

    // Use try catch to catch FileNotFoundException
    try {
      // Load the data into the backend
      backend.loadData("AirportBD.java");
    } catch (FileNotFoundException e) {
      // If error, assert false statement to make test fail
      assertEquals(1, 2);
    }

    // Init the actual cost of path
    Double actualCost = 1.0;
    
    // Init two airport instances
    AirportInterface airport1 = new AirportBD("Airport1", "", "");
    AirportInterface airport2 = new AirportBD("Airport2", "", "");

    // Init the list of restraints
    List<AirportInterface> restraints = new ArrayList<AirportInterface>();

    // Get the cost of path from 1 to 2
    Double cost =
        backend.shortestPathCostWithRestraints(airport1, airport2, restraints);

    // Ensure that path is expected
    assertEquals(actualCost, cost);
  }

  /**
   * Code review test for Frontend Developer to test case where user enters
   * nothing
   * 
   */
  @Test
  public void CodeReviewofFrontendDeveloper1() {
    // Init the airport reader
    AirportReaderBD reader = new AirportReaderBD();

    // Init the backend with reader
    FlightManagerBackendBD backend = new FlightManagerBackendBD(reader);

    // Init string of expected output
    String expectedString = "Didn't recognize that command.  Please type one of the letters presented within []s to identify the command you would like to choose.";

    TextUITester tester = new TextUITester("\nQ\n");
    Scanner scanner = new Scanner(System.in);

    // Create frontend instance
    FlightFinderFrontendFD frontend = new FlightFinderFrontendFD(backend, scanner);

    // Run the command loop of frontend
    frontend.runCommandLoop();

    // Get output of tester
    String output = tester.checkOutput();

    // Check if output contains the expected string
    if (!output.contains(expectedString)) {
      // If not, assert false statement
      assertEquals(1, 2);
    }

    // Otherwise, assert true statement
    assertEquals(1, 1);
  }

  /**
   * Code review test for Frontend Developer to test case where edge contains
   * two invalid nodes
   * 
   */
  @Test
  public void CodeReviewofFrontendDeveloper2() {
    // Init the airport reader
    AirportReaderBD reader = new AirportReaderBD();

    // Init the backend with reader
    FlightManagerBackendBD backend = new FlightManagerBackendBD(reader);

    // Init string of expected output
    String expectedString = "Please choose valid options";

    TextUITester tester = new TextUITester("E\nairport 1\nairport 2\nQ\n");
    Scanner scanner = new Scanner(System.in);

    // Create frontend instance
    FlightFinderFrontendFD frontend = new FlightFinderFrontendFD(backend, scanner);

    // Run the command loop of frontend
    frontend.runCommandLoop();

    // Get output of tester without including dash lines (middle string)
    String output = tester.checkOutput();
    
    // Check if output contains the expected string
    if (!output.contains(expectedString)) {
      // If not, assert false statement
      assertEquals(1, 2);
    }

    // Otherwise, assert true statement
    assertEquals(1, 1);
  }


  @Test
  public void IntegrationTest1() {
    // Init the airport reader
    AirportReaderBD reader = new AirportReaderBD();

    // Init the backend with reader
    FlightManagerBackendBD backend = new FlightManagerBackendBD(reader);

    // Init string of expected output
    String expectedString = "Airport3\nAirport2";

    TextUITester tester = new TextUITester("L\nAirportBD.java\nR\nAirport1\nQ\n");
    Scanner scanner = new Scanner(System.in);

    // Create frontend instance
    FlightFinderFrontendFD frontend = new FlightFinderFrontendFD(backend, scanner);

    // Run the command loop of frontend
    frontend.runCommandLoop();

    // Get output of tester without including dash lines (middle string)
    String output = tester.checkOutput();

    assertEquals(expectedString, backend.toString());
  }

  @Test
  public void IntegrationTest2() {
    // Init the airport reader
    AirportReaderBD reader = new AirportReaderBD();

    // Init the backend with reader
    FlightManagerBackendBD backend = new FlightManagerBackendBD(reader);

    // Init string of expected output
    String expectedString = "1.0";

    TextUITester tester = new TextUITester("L\nAirportBD.java\nF\nAirport1\nAirport2\nQ\n");
    Scanner scanner = new Scanner(System.in);

    // Create frontend instance
    FlightFinderFrontendFD frontend = new FlightFinderFrontendFD(backend, scanner);

    // Run the command loop of frontend
    frontend.runCommandLoop();

    // Get output of tester without including dash lines (middle string)
    String output = tester.checkOutput();

    // Check if output contains the expected string
    if (!output.contains(expectedString)) {
      // If not, assert false statement
      assertEquals(1, 2);
    }

    // Otherwise, assert true statement
    assertEquals(1, 1);
  }
}
