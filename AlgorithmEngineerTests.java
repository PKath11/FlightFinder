import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class AlgorithmEngineerTests {
    /*
     * Path to check goes from A to M
     * The shortest path from A to M is 4 (A->B->M), but with node B restricted, the  shortest path allowed is now 5(A->M)
     */
    @Test
    public void test1() {
        FlightFinderAE<Character,Integer> FlightFinderAEGraph = new FlightFinderAE<Character,Integer>();
        FlightFinderAEGraph.insertNode('A');
        FlightFinderAEGraph.insertNode('B');
        FlightFinderAEGraph.insertNode('H');
        FlightFinderAEGraph.insertNode('M');
        FlightFinderAEGraph.insertNode('E');
        FlightFinderAEGraph.insertNode('F');
        FlightFinderAEGraph.insertNode('D');
        FlightFinderAEGraph.insertNode('G');
        FlightFinderAEGraph.insertNode('L');
        FlightFinderAEGraph.insertNode('I');
        FlightFinderAEGraph.insertEdge('D', 'A', 7);
        FlightFinderAEGraph.insertEdge('D', 'G', 2);
        FlightFinderAEGraph.insertEdge('A', 'B', 1);
        FlightFinderAEGraph.insertEdge('A', 'H', 8);
        FlightFinderAEGraph.insertEdge('A', 'M', 5);
        FlightFinderAEGraph.insertEdge('H', 'B', 6);
        FlightFinderAEGraph.insertEdge('B', 'M', 3);
        FlightFinderAEGraph.insertEdge('M', 'E', 3);
        FlightFinderAEGraph.insertEdge('M', 'F', 4);   
        FlightFinderAEGraph.insertEdge('F', 'G', 9);
        FlightFinderAEGraph.insertEdge('G', 'L', 7);
        FlightFinderAEGraph.insertEdge('I', 'L', 5);
        FlightFinderAEGraph.insertEdge('I', 'D', 1);
        FlightFinderAEGraph.insertEdge('I', 'H', 2);
        FlightFinderAEGraph.insertEdge('H', 'I', 2);
        List<Character> restrictedCharacters = new ArrayList<Character>();
        restrictedCharacters.add('B');
        assertEquals(5, FlightFinderAEGraph.shortestPathCostWithRestrictedRestraints('A', 'M',restrictedCharacters));
        assertEquals("[A, M]",FlightFinderAEGraph.shortestPathDataWithRestrictedRestraints('A', 'M',restrictedCharacters).toString());
    }
    /*
     * This test checks if finding the shortest path with restriction
     * will alter the original graph. First it calls a shortestPathWithRestricted path from A to M, restricted node B.
     * Calling this method will remove the node and paths connected to B, and restore it later.
     * So we check if the shortest path from A to B is the original 4 that it should be after the restricted method is called.
     */
    @Test
    public void test2() {
        FlightFinderAE<Character,Integer> FlightFinderAEGraph = new FlightFinderAE<Character,Integer>();
        FlightFinderAEGraph.insertNode('A');
        FlightFinderAEGraph.insertNode('B');
        FlightFinderAEGraph.insertNode('H');
        FlightFinderAEGraph.insertNode('M');
        FlightFinderAEGraph.insertNode('E');
        FlightFinderAEGraph.insertNode('F');
        FlightFinderAEGraph.insertNode('D');
        FlightFinderAEGraph.insertNode('G');
        FlightFinderAEGraph.insertNode('L');
        FlightFinderAEGraph.insertNode('I');
        FlightFinderAEGraph.insertEdge('D', 'A', 7);
        FlightFinderAEGraph.insertEdge('D', 'G', 2);
        FlightFinderAEGraph.insertEdge('A', 'B', 1);
        FlightFinderAEGraph.insertEdge('A', 'H', 8);
        FlightFinderAEGraph.insertEdge('A', 'M', 5);
        FlightFinderAEGraph.insertEdge('H', 'B', 6);
        FlightFinderAEGraph.insertEdge('B', 'M', 3);
        FlightFinderAEGraph.insertEdge('M', 'E', 3);
        FlightFinderAEGraph.insertEdge('M', 'F', 4);   
        FlightFinderAEGraph.insertEdge('F', 'G', 9);
        FlightFinderAEGraph.insertEdge('G', 'L', 7);
        FlightFinderAEGraph.insertEdge('I', 'L', 5);
        FlightFinderAEGraph.insertEdge('I', 'D', 1);
        FlightFinderAEGraph.insertEdge('I', 'H', 2);
        FlightFinderAEGraph.insertEdge('H', 'I', 2);
        List<Character> restrictedCharacters = new ArrayList<Character>();
        restrictedCharacters.add('B');
        FlightFinderAEGraph.shortestPathCostWithRestrictedRestraints('A', 'M',restrictedCharacters);
        assertEquals(4, FlightFinderAEGraph.shortestPathCost('A', 'M'));
    }
    /*
     * Path to check goes from A to E
     * There is a path from A to E in the original graph, but there is no more path from A to E if M is restricted
     */
    @Test
    public void test3() {
        FlightFinderAE<Character,Integer> FlightFinderAEGraph = new FlightFinderAE<Character,Integer>();
        FlightFinderAEGraph.insertNode('A');
        FlightFinderAEGraph.insertNode('B');
        FlightFinderAEGraph.insertNode('H');
        FlightFinderAEGraph.insertNode('M');
        FlightFinderAEGraph.insertNode('E');
        FlightFinderAEGraph.insertNode('F');
        FlightFinderAEGraph.insertNode('D');
        FlightFinderAEGraph.insertNode('G');
        FlightFinderAEGraph.insertNode('L');
        FlightFinderAEGraph.insertNode('I');
        FlightFinderAEGraph.insertEdge('D', 'A', 7);
        FlightFinderAEGraph.insertEdge('D', 'G', 2);
        FlightFinderAEGraph.insertEdge('A', 'B', 1);
        FlightFinderAEGraph.insertEdge('A', 'H', 8);
        FlightFinderAEGraph.insertEdge('A', 'M', 5);
        FlightFinderAEGraph.insertEdge('H', 'B', 6);
        FlightFinderAEGraph.insertEdge('B', 'M', 3);
        FlightFinderAEGraph.insertEdge('M', 'E', 3);
        FlightFinderAEGraph.insertEdge('M', 'F', 4);   
        FlightFinderAEGraph.insertEdge('F', 'G', 9);
        FlightFinderAEGraph.insertEdge('G', 'L', 7);
        FlightFinderAEGraph.insertEdge('I', 'L', 5);
        FlightFinderAEGraph.insertEdge('I', 'D', 1);
        FlightFinderAEGraph.insertEdge('I', 'H', 2);
        FlightFinderAEGraph.insertEdge('H', 'I', 2);
        List<Character> restrictedCharacters = new ArrayList<Character>();
        restrictedCharacters.add('M');
        try{
            FlightFinderAEGraph.shortestPathCostWithRestrictedRestraints('A', 'E',restrictedCharacters);
            //should not get to this line
            assertEquals(-1,0);
        }
        catch(Exception e){
            assertEquals("There is no such path between the given nodes",e.getMessage());
        }
    }
    /**
     * Path to check goes from A to F
     * Normally, it will take a path (A-M-F), but with nodes E, the path will not exist, and an error would be thrown
     */
    @Test
    public void test4() {
        FlightFinderAE<Character,Integer> FlightFinderAEGraph = new FlightFinderAE<Character,Integer>();
        FlightFinderAEGraph.insertNode('A');
        FlightFinderAEGraph.insertNode('B');
        FlightFinderAEGraph.insertNode('H');
        FlightFinderAEGraph.insertNode('M');
        FlightFinderAEGraph.insertNode('E');
        FlightFinderAEGraph.insertNode('F');
        FlightFinderAEGraph.insertNode('D');
        FlightFinderAEGraph.insertNode('G');
        FlightFinderAEGraph.insertNode('L');
        FlightFinderAEGraph.insertNode('I');
        FlightFinderAEGraph.insertEdge('D', 'A', 7);
        FlightFinderAEGraph.insertEdge('D', 'G', 2);
        FlightFinderAEGraph.insertEdge('A', 'B', 1);
        FlightFinderAEGraph.insertEdge('A', 'H', 8);
        FlightFinderAEGraph.insertEdge('A', 'M', 5);
        FlightFinderAEGraph.insertEdge('H', 'B', 6);
        FlightFinderAEGraph.insertEdge('B', 'M', 3);
        FlightFinderAEGraph.insertEdge('M', 'E', 3);
        FlightFinderAEGraph.insertEdge('M', 'F', 4);   
        FlightFinderAEGraph.insertEdge('F', 'G', 9);
        FlightFinderAEGraph.insertEdge('G', 'L', 7);
        FlightFinderAEGraph.insertEdge('I', 'L', 5);
        FlightFinderAEGraph.insertEdge('I', 'D', 1);
        FlightFinderAEGraph.insertEdge('I', 'H', 2);
        FlightFinderAEGraph.insertEdge('H', 'I', 2);
        List<Character> restrictedCharacters = new ArrayList<Character>();
        restrictedCharacters.add('E');
        try{
            FlightFinderAEGraph.shortestPathCostWithRequiredRestraints('A', 'F',restrictedCharacters);
            //should not get to this line
            assertEquals(-1,0);
        }
        catch(Exception e){
            assertEquals("There is no such path between the given nodes",e.getMessage());
        }
    }
    /**
     * Path to check goes from I to H
     * Normally, it will take a path of 2 (I->H), but with nodes A and D required, the path will be
     * (I->D->A->H) which will be 16
     */
    @Test
    public void test5() {
        FlightFinderAE<Character,Integer> FlightFinderAEGraph = new FlightFinderAE<Character,Integer>();
        FlightFinderAEGraph.insertNode('A');
        FlightFinderAEGraph.insertNode('B');
        FlightFinderAEGraph.insertNode('H');
        FlightFinderAEGraph.insertNode('M');
        FlightFinderAEGraph.insertNode('E');
        FlightFinderAEGraph.insertNode('F');
        FlightFinderAEGraph.insertNode('D');
        FlightFinderAEGraph.insertNode('G');
        FlightFinderAEGraph.insertNode('L');
        FlightFinderAEGraph.insertNode('I');
        FlightFinderAEGraph.insertEdge('D', 'A', 7);
        FlightFinderAEGraph.insertEdge('D', 'G', 2);
        FlightFinderAEGraph.insertEdge('A', 'B', 1);
        FlightFinderAEGraph.insertEdge('A', 'H', 8);
        FlightFinderAEGraph.insertEdge('A', 'M', 5);
        FlightFinderAEGraph.insertEdge('H', 'B', 6);
        FlightFinderAEGraph.insertEdge('B', 'M', 3);
        FlightFinderAEGraph.insertEdge('M', 'E', 3);
        FlightFinderAEGraph.insertEdge('M', 'F', 4);   
        FlightFinderAEGraph.insertEdge('F', 'G', 9);
        FlightFinderAEGraph.insertEdge('G', 'L', 7);
        FlightFinderAEGraph.insertEdge('I', 'L', 5);
        FlightFinderAEGraph.insertEdge('I', 'D', 1);
        FlightFinderAEGraph.insertEdge('I', 'H', 2);
        FlightFinderAEGraph.insertEdge('H', 'I', 2);
        List<Character> restrictedCharacters = new ArrayList<Character>();
        restrictedCharacters.add('A');
        restrictedCharacters.add('D');  
        assertEquals(16,FlightFinderAEGraph.shortestPathCostWithRequiredRestraints('I', 'H',restrictedCharacters));
        assertEquals("[I, D, A, H]",FlightFinderAEGraph.shortestPathDataWithRequiredRestraints('I', 'H',restrictedCharacters).toString());
    }
    /*
     * This test will test if the DataWrangler class is able to detect a file that is not in the directory passed into the parameter
     */
    @Test
    public void CodeReviewOfDataWrangler1(){
	    try{
		    AirportReaderDW ar = new AirportReaderDW();
		    ar.readFlightsFromFile("Hello");//there is no such file named hello, exception should be thrown
		    assertEquals(1,2);//we should not get to this line
		}
	    catch(FileNotFoundException e){
		    assertEquals(1,1);//catching a file not found error means we are in the right place 
	    }
    }

    /*
     *This test will test the DW load file method with a different file
     *The formatting of this file is a bit different than the original file
     */
    @Test
    public void CodeReviewOfDataWrangler2(){
	    try{
	    	AirportReaderDW ar = new AirportReaderDW();
	    	List<AirportInterface> list = new ArrayList<AirportInterface>();
	    	list = ar.readFlightsFromFile("airports.dot");
	    	assertEquals(list.get(0).getAirport(),"Chicago");
	    }
	    catch(Exception e){
		    assertEquals(1,2);//no exception should be thrown
	    }
    }
    /*
     * This test tests the integration of DataWrangler and AlgorithmEngineer,
     * specifically, if the flight routes are added correctly to the graph,
     * and that the total amount of airports is 5
     */
    @Test
    public void Integration1(){
	    try{
		AirportReaderDW ar = new AirportReaderDW();
	 	List<AirportInterface> list = new ArrayList<AirportInterface>();
		list = ar.readFlightsFromFile("airports.dot");
		FlightFinderAE<String,Integer> FFAE = new FlightFinderAE<String,Integer>();
		for(AirportInterface airport : list){
			FFAE.insertNode(airport.getAirport());//this inserts all the airports into the graph
		}
		for(AirportInterface airport : list){//this imports all the edges into the graph
			String airportName = airport.getAirport();//stores the source airport
			String paths =  airport.getPaths();//gets the paths from this airport 
			String weights = airport.getPathsWeights();//gets the weight of the paths from this airport
			String[] p = paths.split(",");//splits the string of the paths to get each path as an individual string
			String[] w = weights.split(",");//does thhe same thing as above but for weights

			//the following adds all the edges going out of the current airport
			for(int i=0;i<p.length;i++){//repeats through each edge coming out of the current airport
				String pTemp = p[i].trim();//trims the current path
				String wTemp = w[i].trim();//trims the current weight string 
				String[]temp = pTemp.split("->");//splits the path so that we get the source and destination airport in a new array
				int weight = Integer.parseInt(wTemp.split("=")[1].substring(0,1));//gets the weight of the path from the current airport to the destination
				String destination = temp[1].trim();//gets the destination airport
				FFAE.insertEdge(airportName,destination,weight);//adds the edge from the current airport, to the destination airport with the weight
			}
		}
		assertEquals(FFAE.listOfAllNodes().size(),5);//there should be a total of 5 airports

	    }
	    catch(Exception e){
		    assertEquals(1,2);//No exception should be thrown
	    }
    }

    /*
     * This test tests the integration of DataWrangler and AlgorithmEngineer,
     * specifically, the shortestPathWithRestrictedNodes method, in the airports.dot 
     * file, the shortest path from Miami to Los Angeles is from Miami->Atlanta->Los Angeles which is 8,
     * but with Atlanta restricted, the shortest path should now be from Miami->Los Angeles which is 9
     */
    @Test
    public void Integration2(){
	    try{
		AirportReaderDW ar = new AirportReaderDW();
	 	List<AirportInterface> list = new ArrayList<AirportInterface>();
		list = ar.readFlightsFromFile("airports.dot");
		FlightFinderAE<String,Integer> FFAE = new FlightFinderAE<String,Integer>();
		for(AirportInterface airport : list){
			FFAE.insertNode(airport.getAirport());//this inserts all the airports into the graph
		}
		for(AirportInterface airport : list){//this imports all the edges into the graph
			String airportName = airport.getAirport();//stores the source airport
			String paths =  airport.getPaths();//gets the paths from this airport 
			String weights = airport.getPathsWeights();//gets the weight of the paths from this airport
			String[] p = paths.split(",");//splits the string of the paths to get each path as an individual string
			String[] w = weights.split(",");//does thhe same thing as above but for weights

			//the following adds all the edges going out of the current airport
			for(int i=0;i<p.length;i++){//repeats through each edge coming out of the current airport
				String pTemp = p[i].trim();//trims the current path
				String wTemp = w[i].trim();//trims the current weight string 
				String[]temp = pTemp.split("->");//splits the path so that we get the source and destination airport in a new array
				int weight = Integer.parseInt(wTemp.split("=")[1].substring(0,1));//gets the weight of the path from the current airport to the destination
				String destination = temp[1].trim();//gets the destination airport
				FFAE.insertEdge(airportName,destination,weight);//adds the edge from the current airport, to the destination airport with the weight
			}
		}
		List<String>restricted = new ArrayList<String>();
		restricted.add("Atlanta");
		assertEquals(9,FFAE.shortestPathCostWithRestrictedRestraints("Miami","Los_Angeles",restricted));//there should be a total of 5 airports

	    }
	    catch(Exception e){
		    assertEquals(1,2);//No exception should be thrown
	    }
    }
	
	
    
    public static void main(String[] args) {
        FlightFinderAE<Character,Integer> FlightFinderAEGraph = new FlightFinderAE<Character,Integer>();
        FlightFinderAEGraph.insertNode('A');
        FlightFinderAEGraph.insertNode('B');
        FlightFinderAEGraph.insertNode('H');
        FlightFinderAEGraph.insertNode('M');
        FlightFinderAEGraph.insertNode('E');
        FlightFinderAEGraph.insertNode('F');
        FlightFinderAEGraph.insertNode('D');
        FlightFinderAEGraph.insertNode('G');
        FlightFinderAEGraph.insertNode('L');
        FlightFinderAEGraph.insertNode('I');
        FlightFinderAEGraph.insertEdge('D', 'A', 7);
        FlightFinderAEGraph.insertEdge('D', 'G', 2);
        FlightFinderAEGraph.insertEdge('A', 'B', 1);
        FlightFinderAEGraph.insertEdge('A', 'H', 8);
        FlightFinderAEGraph.insertEdge('A', 'M', 5);
        FlightFinderAEGraph.insertEdge('H', 'B', 6);
        FlightFinderAEGraph.insertEdge('B', 'M', 3);
        FlightFinderAEGraph.insertEdge('M', 'E', 3);
        FlightFinderAEGraph.insertEdge('M', 'F', 4);   
        FlightFinderAEGraph.insertEdge('F', 'G', 9);
        FlightFinderAEGraph.insertEdge('G', 'L', 7);
        FlightFinderAEGraph.insertEdge('I', 'L', 5);
        FlightFinderAEGraph.insertEdge('I', 'D', 1);
        FlightFinderAEGraph.insertEdge('I', 'H', 2);
        FlightFinderAEGraph.insertEdge('H', 'I', 2);
        List<Character> restrictedCharacters = new ArrayList<Character>();
        restrictedCharacters.add('A');
        restrictedCharacters.add('D');  
        FlightFinderAEGraph.shortestPathDataWithRequiredRestraints('I', 'H',restrictedCharacters);
    }
}

