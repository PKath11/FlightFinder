 import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DataWranglerTests {
  /**
   * JUnit Test 1
   * This tests the regular case, where the lines in the dot file contain an airplane, path, and pathWeights
   * field. This tests the first airport.s
   */
  @Test
  public void test1() throws FileNotFoundException {
    //create reader and example array
    AirportReaderDW bookReader1 = new AirportReaderDW();
    ArrayList<AirportInterface> test1 = new ArrayList<>();

    try {
      // try and open file
      test1 = bookReader1.readFlightsFromFile("airports.dot");
    } catch (FileNotFoundException e) {

      e.printStackTrace();
    }
    // check first item
    assertEquals(test1.get(0).getAirport(), "Chicago");
    assertEquals(test1.get(0).getPaths(), "Chicago -> Miami, Chicago -> New_York");
    assertEquals(test1.get(0).getPathsWeights(), "[weight=7], [weight=6]");






  }
  /**
   * JUnit Test 2
   * This tests the regular case, where the lines in the dot file contain an airplane, path, and pathWeights
   * field. This tests the second airport.
   */
  @Test
  public void test2() {
    AirportReaderDW bookReader2 = new AirportReaderDW();
    ArrayList<AirportInterface> test2 = new ArrayList<>();

    try {
      test2 = bookReader2.readFlightsFromFile("airports.dot");
    } catch (FileNotFoundException e) {

      e.printStackTrace();
    }

    assertEquals(test2.get(1).getAirport(), "New_York");
    assertEquals(test2.get(1).getPaths(), "New_York -> Miami, New_York -> Los_Angeles");
    assertEquals(test2.get(1).getPathsWeights(), "[weight=4], [weight=10]");



  }
  /**
   * JUnit Test 3
   * This tests the regular case, where the lines in the dot file contain an airplane, path, and pathWeights
   * field. This tests the third airport.
   */
  @Test
  public void test3() {
    AirportReaderDW bookReader3 = new AirportReaderDW();
    ArrayList<AirportInterface> test3 = new ArrayList<>();

    try {
      test3 = bookReader3.readFlightsFromFile("airports.dot");
    } catch (FileNotFoundException e) {

      e.printStackTrace();
    }

    assertEquals(test3.get(2).getAirport(), "Miami");
    assertEquals(test3.get(2).getPaths(), "Miami -> Atlanta, Miami -> Los_Angeles");
    assertEquals(test3.get(2).getPathsWeights(), "[weight=3], [weight=9]");



  }

  /**
   * JUnit Test 4
   * This tests the regular case, where the lines in the dot file contain an airplane, path, and pathWeights
   * field. This tests the fourth airport.
   */
  @Test
  public void test4() {
    AirportReaderDW bookReader4 = new AirportReaderDW();
    ArrayList<AirportInterface> test4 = new ArrayList<>();

    try {
      test4 = bookReader4.readFlightsFromFile("airports.dot");
    } catch (FileNotFoundException e) {

      e.printStackTrace();
    }

    assertEquals(test4.get(3).getAirport(), "Atlanta");
    assertEquals(test4.get(3).getPaths(), "Atlanta -> Los_Angeles, Atlanta -> New_York");
    assertEquals(test4.get(3).getPathsWeights(), "[weight=5], [weight=6]");



  }

  /**
   * JUnit Test 5
   * This tests the regular case, where the lines in the dot file contain an airplane, path, and pathWeights
   * field. This tests the fifth airport.
   */
  @Test
  public void test5() {
    AirportReaderDW bookReader5 = new AirportReaderDW();
    ArrayList<AirportInterface> test5 = new ArrayList<>();

    try {
      test5 = bookReader5.readFlightsFromFile("airports.dot");
    } catch (FileNotFoundException e) {

      e.printStackTrace();
    }

    assertEquals(test5.get(4).getAirport(), "Los_Angeles");
    assertEquals(test5.get(4).getPaths(), "Los_Angeles -> Chicago");
    assertEquals(test5.get(4).getPathsWeights(), "[weight=8]");



  }
  /**
   * Integration Test 1
   * This is an integration test for the data wrangler and the backend.
   */
  @Test
  public void integrationTest1() {

    FlightManagerBackendBD backend = new FlightManagerBackendBD(new AirportReaderDW());
    try {
      backend.loadData("airports.dot");
    }catch (FileNotFoundException e){
      assertEquals(1,2);
    }

    //System.out.println(backend.toString().substring(24 , 36).trim());
    //System.out.println(backend.getAirports());
    System.out.println(backend.toString());
  //  System.out.println(backend.toString().substring(30).trim());
    assertEquals("New_York", backend.toString().substring(0 , 8).trim());
    assertEquals("Miami", backend.toString().substring(8 , 15).trim());
    assertEquals("Atlanta", backend.toString().substring(15 , 23).trim());
    assertEquals("Chicago", backend.toString().substring(23 , 31).trim());
    assertEquals("Los_Angeles", backend.toString().substring(31).trim());


  }
  /**
   * Integration Test 2
   * This is an integration test for the data wrangler and the backend.
   */
  @Test
  public void integrationTest2() {
      AirportReaderDW reader = new AirportReaderDW();
    FlightManagerBackendBD backend = new FlightManagerBackendBD(reader);
    try {
      backend.loadData("airports.dot");
    }catch (FileNotFoundException e){
      assertEquals(1,2);
    }
    //List<AirportInterface> airports = new ArrayList<>();
    //airports.add
    //System.out.println(reader.airports);
    //System.out.println(backend.getAirports());

    for (AirportInterface airport: reader.airports) {
      assertEquals(true, backend.getAirports().contains(airport));
    }

    // assertEquals(reader.airports, backend.getAirports());



  }
  /**
   * Code Review of Algorithm Engineer test 1
   * This is the first test for a code review of the algorithm engineer
   */
  @Test
  public void CodeReviewOfAlgorithmEngineer1() {
    FlightFinderAE<Character,Integer> FlightFinderAEGraph = new FlightFinderAE<Character,Integer>();
    FlightFinderAEGraph.insertNode('C');
    FlightFinderAEGraph.insertNode('N');
    FlightFinderAEGraph.insertNode('M');
    FlightFinderAEGraph.insertNode('A');
    FlightFinderAEGraph.insertNode('L');

    FlightFinderAEGraph.insertEdge('C', 'M', 7);
    FlightFinderAEGraph.insertEdge('C', 'N', 6);
    FlightFinderAEGraph.insertEdge('N', 'M', 4);
    FlightFinderAEGraph.insertEdge('N', 'L', 10);
    FlightFinderAEGraph.insertEdge('M', 'A', 3);
    FlightFinderAEGraph.insertEdge('M', 'L', 9);
    FlightFinderAEGraph.insertEdge('A', 'L', 5);
    FlightFinderAEGraph.insertEdge('A', 'N', 6);
    FlightFinderAEGraph.insertEdge('L', 'C', 8);
     List<Character> restrictedCharacters = new ArrayList<Character>();
    restrictedCharacters.add('M');
    Assertions.assertEquals(16, FlightFinderAEGraph.shortestPathCostWithRestrictedRestraints('C', 'L',restrictedCharacters));
    Assertions.assertEquals("[C, N, L]",FlightFinderAEGraph.shortestPathDataWithRestrictedRestraints('C', 'L',restrictedCharacters).toString());


  }
  /**
   * Code Review of Algorithm Engineer test 2
   * This is the second test for a code review of the algorithm engineer
   */
  @Test
  public void CodeReviewOfAlgorithmEngineer2() {
    FlightFinderAE<Character,Integer> FlightFinderAEGraph = new FlightFinderAE<Character,Integer>();
    FlightFinderAEGraph.insertNode('C');
    FlightFinderAEGraph.insertNode('N');
    FlightFinderAEGraph.insertNode('M');
    FlightFinderAEGraph.insertNode('A');
    FlightFinderAEGraph.insertNode('L');

    FlightFinderAEGraph.insertEdge('C', 'M', 7);
    FlightFinderAEGraph.insertEdge('C', 'N', 6);
    FlightFinderAEGraph.insertEdge('N', 'M', 4);
    FlightFinderAEGraph.insertEdge('N', 'L', 10);
    FlightFinderAEGraph.insertEdge('M', 'A', 3);
    FlightFinderAEGraph.insertEdge('M', 'L', 9);
    FlightFinderAEGraph.insertEdge('A', 'L', 5);
    FlightFinderAEGraph.insertEdge('A', 'N', 6);
    FlightFinderAEGraph.insertEdge('L', 'C', 8);
    List<Character> restrictedCharacters = new ArrayList<Character>();
  //  restrictedCharacters.add('A','L');
  //  System.out.println(FlightFinderAEGraph.shortestPathCostWithRestrictedRestraints('M', 'N',restrictedCharacters));
    //System.out.println(FlightFinderAEGraph.shortestPathDataWithRestrictedRestraints('M', 'N',restrictedCharacters).toString());
    Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> { // better error message if restrictions prevent from reaching end
      restrictedCharacters.add('A','L');;
    });
   // Assertions.assertEquals(16, FlightFinderAEGraph.shortestPathCostWithRestrictedRestraints('C', 'L',restrictedCharacters));
    //Assertions.assertEquals("[C, N, L]",FlightFinderAEGraph.shortestPathDataWithRestrictedRestraints('C', 'L',restrictedCharacters).toString());


  }
}
