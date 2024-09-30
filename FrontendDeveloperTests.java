import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Tester class for the FlightFinder Frontend
 */
public class FrontendDeveloperTests {


    private List<AirportInterface> airports;

    private FlightFinderBackendFD backend;

    private FlightFinderFrontendFD frontend;

    //private Scanner scanner = new Scanner(System.in);


    /**
     * Initializes a list of AirportFD objects to be used by all the testers
     */
    @BeforeEach
    public void setUp() {
        airports = new ArrayList<>();
        AirportFD Airport1 = new AirportFD("airport 1", "To: 5,2,3");
        AirportFD Airport2 = new AirportFD("airport 2", "To: 1,4");
        AirportFD Airport3 = new AirportFD("airport 3", "To: 1,4");
        AirportFD Airport4 = new AirportFD("airport 4", "To: 2,3");
        AirportFD Airport5 = new AirportFD("airport 5", "To: 1");


        airports.add(Airport1);
        airports.add(Airport2);
        airports.add(Airport3);
        airports.add(Airport4);
        airports.add(Airport5);
        backend =  new FlightFinderBackendFD(airports);
        //frontend = new FlightFinderFrontendFD(backend, scanner);

    }


    /**
     * Tests to make sure the loadData method works as intended with a valid file
     */
    //test load data
    @Test
    public void TestLoadingValidFile() {

        //Test with valid file

        try {
            TextUITester tester = new TextUITester("L\nTestFile.data\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();


            String output = tester.checkOutput();

            String result = "Enter the name of the file to load: \nData loaded successfully";
            //System.out.println(output);

            if (!output.contains(result)) {
                fail("Fail");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests to make sure the loadData method works as intended with an invalid file
     */
    @Test
    public void TestLoadingInvalidFile() {
        try {
            TextUITester tester = new TextUITester("L\nWrong.data\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();

            String output = tester.checkOutput();

            String result = "Enter the name of the file to load: \nError: Could not find or load file: Wrong.data";
            //System.out.println(output);


            if (!output.contains(result)) {
                fail("Fail");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //Checks that removing airport works

    /**
     * Tests to make sure removeAirport method works as intended when Airport is successfully removed
     */
    @Test
    public void TestAirportRemovedSuccessfully() {
        try {
            TextUITester tester = new TextUITester("R\nairport 2\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();

            String output = tester.checkOutput();


            String result = "Here is the list of current airports:\nairport 1\nairport 2\nairport 3\nairport 4\nairport 5" +
                    "\nType in the airport you wish to remove:\nAirport removed successfully";
            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("fail");
            }

            AirportFD Airport2 = new AirportFD("airport 2", "To: 1,4");





            if (backend.getAirports().contains(Airport2)) {
                fail("Airport not removed");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Tests to make sure removeAirport method works as intended when airport cannot be removed
     */
    @Test
    public void TestRemoveAirportUnsuccessful() {
        try {
            TextUITester tester = new TextUITester("R\nairport 5\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();

            String output = tester.checkOutput();


            String result = "Here is the list of current airports:\nairport 1\nairport 2\nairport 3\nairport 4\nairport 5" +
                    "\nType in the airport you wish to remove:\nAirport couldn't be removed";
            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("fail");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests to make sure the removeEdge method works as intended when both inputted Airports are valid
     */

    @Test
    public void TestRemoveEdgeBothValid() {
        //both are valid

        try {
            TextUITester tester = new TextUITester("E\nairport 1\nairport 5\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();


            //TextUITester.run();

            String output = tester.checkOutput();
            //System.out.println(output);

            //scanner.close();

            String result = "Here is the list of current airports and edges:\nairport 1 | path: To: 5,2,3\n" +
                    "airport 2 | path: To: 1,4\nairport 3 | path: To: 1,4\nairport 4 | path: To: 2,3\n" +
                    "airport 5 | path: To: 1\nType in the first airport:\nType in the second " +
                    "airport:\nEdge removed successfully";

            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Tests to make sure the removeEdge method works as intended when one inputted Airport is not valid
     */
    //one is not valid
    @Test
    public void TestRemoveEdgeOneNotValid() {
        try {
            TextUITester tester = new TextUITester("E\nairport 1\nairport 9\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();


            //TextUITester.run();

            String output = tester.checkOutput();
            //System.out.println(output);

            //scanner.close();

            String result = "Here is the list of current airports and edges:\nairport 1 | path: To: 5,2,3\n" +
                    "airport 2 | path: To: 1,4\nairport 3 | path: To: 1,4\nairport 4 | path: To: 2,3\n" +
                    "airport 5 | path: To: 1\nType in the first airport:\nType in the second " +
                    "airport:\nPlease choose valid options";

            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests to make sure the removeEdge method works as intended when both inputted Airports are the same
     */
    //both same
    @Test
    public void TestRemoveEdgeBothSame() {
        try {
            TextUITester tester = new TextUITester("E\nairport 1\nairport 1\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();


            //TextUITester.run();

            String output = tester.checkOutput();
            //System.out.println(output);

            //scanner.close();

            String result = "Here is the list of current airports and edges:\nairport 1 | path: To: 5,2,3\n" +
                    "airport 2 | path: To: 1,4\nairport 3 | path: To: 1,4\nairport 4 | path: To: 2,3\n" +
                    "airport 5 | path: To: 1\nType in the first airport:\nType in the second " +
                    "airport:\nEdge couldn't be removed";

            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests to make sure the removeEdge method works as intended when that edge does not exist
     */
    //not neighbors
    @Test
    public void TestRemoveEdgeNotNeighbors() {
        try {
            TextUITester tester = new TextUITester("E\nairport 1\nairport 4\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();


            //TextUITester.run();

            String output = tester.checkOutput();
            //System.out.println(output);

            //scanner.close();

            String result = "Here is the list of current airports and edges:\nairport 1 | path: To: 5,2,3\n" +
                    "airport 2 | path: To: 1,4\nairport 3 | path: To: 1,4\nairport 4 | path: To: 2,3\n" +
                    "airport 5 | path: To: 1\nType in the first airport:\nType in the second " +
                    "airport:\nEdge couldn't be removed";

            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Tests to make sure the removeEdge method works as intended when the user inputs a blank for the first Airport
     */

    //first one just press enter
    @Test
    public void TestRemoveEdgeFirstBlank() {
        try {
            TextUITester tester = new TextUITester("E\n\nairport 4\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();


            //TextUITester.run();

            String output = tester.checkOutput();
            //System.out.println(output);

            //scanner.close();

            String result = "Here is the list of current airports and edges:\nairport 1 | path: To: 5,2,3\n" +
                    "airport 2 | path: To: 1,4\nairport 3 | path: To: 1,4\nairport 4 | path: To: 2,3\n" +
                    "airport 5 | path: To: 1\nType in the first airport:\nPlease choose valid options";

            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests to make sure the removeEdge method works as intended when the user inputs a blank for the second Airport
     */
    //second one just press enter
    @Test
    public void TestRemoveEdgeSecondBlank() {
        try {
            TextUITester tester = new TextUITester("E\nairport 1\n\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();


            //TextUITester.run();

            String output = tester.checkOutput();
            //System.out.println(output);

            //scanner.close();

            String result = "Here is the list of current airports and edges:\nairport 1 | path: To: 5,2,3\n" +
                    "airport 2 | path: To: 1,4\nairport 3 | path: To: 1,4\nairport 4 | path: To: 2,3\n" +
                    "airport 5 | path: To: 1\nType in the first airport:\nType in the second " +
                    "airport:\nPlease choose valid options";

            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }














    //checks that getting shortest path works


    /**
     * Tests to make sure the getShortest method works as intended when both inputted Airports are valid
     */
    @Test
    public void TestShortestPathBothValid() {
        try {
            TextUITester tester = new TextUITester("F\nairport 1\nairport 5\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();


            //System.out.println("hello");

            String output = tester.checkOutput();


            String result = "Here is the list of current airports:\nairport 1\n" +
                    "airport 2\nairport 3\nairport 4\n" +
                    "airport 5\nType in the first airport:\nType in the second " +
                    "airport:\nList of airports in the shortest path:\nairport 1\nairport 2\nairport 3\nCost of the shortest path: 2.0";

            //System.out.println(output);
            //System.out.println(result);

            //System.out.println("hello");

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests to make sure the getShortestPath method works as intended when one inputted Airport is not valid
     */
    //One not valid
    @Test
    public void TestShortestPathOneNotValid() {
        try {
            TextUITester tester = new TextUITester("F\nairport 1\nairport 9\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();


            //System.out.println("hello");

            String output = tester.checkOutput();


            String result = "Here is the list of current airports:\nairport 1\n" +
                    "airport 2\nairport 3\nairport 4\n" +
                    "airport 5\nType in the first airport:\nType in the second " +
                    "airport:\nPlease choose valid options";

            //System.out.println(output);
            //System.out.println(result);

            //System.out.println("hello");

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests to make sure the getShortestPath method works as intended when the user inputs a blank for the first Airport
     */
    //first enter
    @Test
    public void TestShortestPathFirstBlank() {
        try {
            TextUITester tester = new TextUITester("F\n\nairport 5\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();


            //System.out.println("hello");

            String output = tester.checkOutput();


            String result = "Here is the list of current airports:\nairport 1\n" +
                    "airport 2\nairport 3\nairport 4\n" +
                    "airport 5\nType in the first airport:\nPlease choose valid options";

            //System.out.println(output);
            //System.out.println(result);

            //System.out.println("hello");

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests to make sure the getShortestPath method works as intended when the user inputs a blank for the second Airport
     */
    //second enter
    @Test
    public void TestShortestPathSecondBlank() {
        try {
            TextUITester tester = new TextUITester("F\nairport 1 \n\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();


            //System.out.println("hello");

            String output = tester.checkOutput();


            String result = "Here is the list of current airports:\nairport 1\n" +
                    "airport 2\nairport 3\nairport 4\n" +
                    "airport 5\nType in the first airport:\nType in the second airport:\nPlease choose valid options";

            //System.out.println(output);
            //System.out.println(result);

            //System.out.println("hello");

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests to make sure the getShortestPath method works as intended when there is an exception thrown
     */
    @Test
    public void TestShortestPathException() {
        try {
            TextUITester tester = new TextUITester("F\nairport 1\n airport 4\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();


            //System.out.println("hello");

            String output = tester.checkOutput();


            String result = "Here is the list of current airports:\nairport 1\n" +
                    "airport 2\nairport 3\nairport 4\n" +
                    "airport 5\nType in the first airport:\nType in the second airport:\nNo such path";

            //System.out.println(output);
            //System.out.println(result);

            //System.out.println("hello");

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests to make sure the getShortestPathWithRestrictedRestraint works when both Airports are valid
     * and the restraint is valid
     */
  
    @Test
    public void TestShortestPathRestraintRestrictedBothValid() {
        try {
            TextUITester tester = new TextUITester("S\nairport 5\nairport 4\nairport 3\n\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();




            String output = tester.checkOutput();


            String result = "Here is the list of current airports:\nairport 1\n" +
                    "airport 2\nairport 3\nairport 4\n" +
                    "airport 5\nType in the first airport:\nType in the second " +
                    "airport:\n" +
                    "Here is the list of current airport restraints to choose from:\n" +
                    "airport 1\nairport 2\nairport 3\nairport 4\nairport 5\n" +
                    "Type in a restricted restraint airport:\nType in a restricted restraint airport:\nList of airports in the shortest path:\nairport 5\nairport 1\nairport 2\nairport 4\nCost of the shortest path with restricted restraints: 6.0";

            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  

    /**
     * Tests to make sure the getShortestPathWithRestrictedRestraint works when the user inputs a blank
     * for one of the Airports
     */

    @Test
    public void TestShortestPathRestraintRestrictedOneBlank() {
        try {
            TextUITester tester = new TextUITester("S\nairport 5\n\nairport 3\n\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();




            String output = tester.checkOutput();


            String result = "Here is the list of current airports:\nairport 1\n" +
                    "airport 2\nairport 3\nairport 4\n" +
                    "airport 5\nType in the first airport:\nType in the second airport:\n" +
                    "Here is the list of current airport restraints to choose from:\n" +
                    "airport 1\nairport 2\nairport 3\nairport 4\nairport 5\n" +
                    "Type in a restricted restraint airport:\nType in a restricted restraint airport:\nPlease choose valid options";

            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //exception
    //WORKS

    /**
     * Tests to make sure the getShortestPathWithRestrictedRestraint works when an exception is thrown
     */
    @Test
    public void TestShortestPathRestraintRestrictedException() {
        try {
            TextUITester tester = new TextUITester("S\nairport 5\nairport 4\nairport 1\n\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();




            String output = tester.checkOutput();


            String result = "Here is the list of current airports:\nairport 1\n" +
                    "airport 2\nairport 3\nairport 4\n" +
                    "airport 5\nType in the first airport:\nType in the second " +
                    "airport:\n" +
                    "Here is the list of current airport restraints to choose from:\n" +
                    "airport 1\nairport 2\nairport 3\nairport 4\nairport 5\n" +
                    "Type in a restricted restraint airport:\nType in a restricted restraint airport:\nCannot get path with these airports or restraints";

            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests to make sure the getShortestPathWithRequiredRestraint works when both Airports are valid
     * and the restraint is valid
     */
    //both working

    @Test
    public void TestShortestPathRestraintRequiredBothValid() {
        try {
            TextUITester tester = new TextUITester("P\nairport 5\nairport 4\nairport 2\n\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();




            String output = tester.checkOutput();


            String result = "Here is the list of current airports:\nairport 1\n" +
                    "airport 2\nairport 3\nairport 4\n" +
                    "airport 5\nType in the first airport:\nType in the second " +
                    "airport:\n" +
                    "Here is the list of current airport restraints to choose from:\n" +
                    "airport 1\nairport 2\nairport 3\nairport 4\nairport 5\n" +
                    "Type in a required restraint airport:\nType in a required restraint airport:\nList of airports in the shortest path:\nairport 5\nairport 1\nairport 2\nairport 4\nCost of the shortest path with required restraints: 6.0";

            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //one blank


    /**
     * Tests to make sure the getShortestPathWithRequiredRestraint works when the user inputs a blank
     * for one of the Airports
     */

    @Test
    public void TestShortestPathRestraintRequiredOneBlank() {
        try {
            TextUITester tester = new TextUITester("P\nairport 5\n\nairport 2\n\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();




            String output = tester.checkOutput();


            String result = "Here is the list of current airports:\nairport 1\n" +
                    "airport 2\nairport 3\nairport 4\n" +
                    "airport 5\nType in the first airport:\nType in the second airport:\n" +
                    "Here is the list of current airport restraints to choose from:\n" +
                    "airport 1\nairport 2\nairport 3\nairport 4\nairport 5\n" +
                    "Type in a required restraint airport:\nType in a required restraint airport:\nPlease choose valid options";

            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //exception

    /**
     * Tests to make sure the getShortestPathWithRestrictedRestraint works when an exception is thrown
     */
    @Test
    public void TestShortestPathRestraintRequiredException() {
        try {
            TextUITester tester = new TextUITester("P\nairport 5\nairport 4\nairport 1\n\nQ");
            Scanner scanner = new Scanner(System.in);
            frontend = new FlightFinderFrontendFD(backend, scanner);
            frontend.runCommandLoop();




            String output = tester.checkOutput();


            String result = "Here is the list of current airports:\nairport 1\n" +
                    "airport 2\nairport 3\nairport 4\n" +
                    "airport 5\nType in the first airport:\nType in the second " +
                    "airport:\n" +
                    "Here is the list of current airport restraints to choose from:\n" +
                    "airport 1\nairport 2\nairport 3\nairport 4\nairport 5\n" +
                    "Type in a required restraint airport:\nType in a required restraint airport:\nCannot get path with these airports or restraints";

            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //////////////////Additional Tests/////////////////////


    //remove

    /**
     * First code review test of the backend, insures that the removeAirport method of the backend works as intended
     */
    @Test
    public void CodeReviewOfBackendDeveloper1() {
        // Init the airport reader
        AirportReaderBD reader = new AirportReaderBD();

        // Init the backend with reader
        FlightManagerBackendBD backend2 = new FlightManagerBackendBD(reader);

        // Use try catch to catch FileNotFoundException
        try {
            // Load the data into the backend
            backend2.loadData("AirportBD.java");
        } catch (FileNotFoundException e) {
            // If error, assert false statement to make test fail
            assertEquals(1, 2);
        }

        AirportInterface airport2 = null;


        for (AirportInterface airport : backend2.getAirports()) {
            if (airport.getAirport().trim().equals("Airport2")) {
                // If so, return airport instance
                airport2 = airport;
            }
        }

        //airport2.setPath("Airport2 -> Airport1");
        //airport2.setPathsWeights("weight: 3.0");

        //System.out.println(backend2.toString());



        backend2.removeAirport(airport2);

        //System.out.println(backend2.toString());

        if (backend2.getAirports().contains(airport2)) {
            fail("Fail");
        }



    }



    //remove edge


    /**
     * Second code review test of the backend, insures that the removeAirportEdge method of the backend works as intended
     */

    @Test
    public void CodeReviewOfBackendDeveloper2() {
        // Init the airport reader
        AirportReaderBD reader = new AirportReaderBD();

        // Init the backend with reader
        FlightManagerBackendBD backend2 = new FlightManagerBackendBD(reader);

        FlightFinderAEBD ae = new FlightFinderAEBD();



        // Use try catch to catch FileNotFoundException
        try {
            // Load the data into the backend
            backend2.loadData("AirportBD.java");
        } catch (FileNotFoundException e) {
            // If error, assert false statement to make test fail
            assertEquals(1, 2);
        }

        AirportInterface airport1 = null;


        for (AirportInterface airport : backend2.getAirports()) {
            if (airport.getAirport().trim().equals("Airport1")) {
                // If so, return airport instance
                airport1 = airport;
            }
        }

        AirportInterface airport3 = null;

        for (AirportInterface airport : backend2.getAirports()) {
            if (airport.getAirport().trim().equals("Airport3")) {
                // If so, return airport instance
                airport3 = airport;
            }
        }

        //System.out.println(backend2.graph.getEdgeCount());

        if (backend2.graph.getEdgeCount() != 4) {
            fail("fail");
        }

        backend2.removeAirportEdge(airport1, airport3);

        if (backend2.graph.getEdgeCount() != 3) {
            fail("fail");
        }

        //System.out.println(backend2.graph.getEdgeCount());



        //if (backend2.getAirports().contains(airport2)) {
            //fail("Fail");
        //}



    }


    /**
     * first integration test, insures that the frontend and backend work together properly when removing
     * an airport
     */
    @Test
    public void IntegrationTest1() {
        try {
            AirportReaderBD reader = new AirportReaderBD();
            FlightManagerBackendBD backend2 = new FlightManagerBackendBD(reader);

            try {
                // Load the data into the backend
                backend2.loadData("AirportBD.java");
            } catch (FileNotFoundException e) {
                // If error, assert false statement to make test fail
                assertEquals(1, 2);
            }
            //System.out.println(backend2.toString());






            TextUITester tester = new TextUITester("R\nAirport2\nQ");
            Scanner scanner = new Scanner(System.in);

            //frontend = new FlightFinderFrontendFD(backend2, scanner);
            //frontend.runCommandLoop();
            FlightFinderFrontendFD frontend2 = new FlightFinderFrontendFD(backend2, scanner);
            frontend2.runCommandLoop();



            String output = tester.checkOutput();

            //System.out.println(backend2.toString());



            String result = "Here is the list of current airports:\nAirport1\nAirport2\nAirport3" +
                    "\nType in the airport you wish to remove:\nAirport removed successfully";



            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("Fail");
            }

            AirportInterface airport2 = null;


            for (AirportInterface airport : backend2.getAirports()) {
                if (airport.getAirport().trim().equals("Airport2")) {
                    // If so, return airport instance
                    airport2 = airport;
                }
            }

            if (backend2.getAirports().contains(airport2)) {
                fail("Fail");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Second integration test, insures that the frontend and backend work together properly when
     * trying to find the shortest path between two airports.
     */
    @Test
    public void IntegrationTest2() {
        try {
            AirportReaderBD reader = new AirportReaderBD();
            FlightManagerBackendBD backend2 = new FlightManagerBackendBD(reader);
            try {
                // Load the data into the backend
                backend2.loadData("AirportBD.java");
            } catch (FileNotFoundException e) {
                // If error, assert false statement to make test fail
                assertEquals(1, 2);
            }
            //System.out.println(backend2.toString());





            TextUITester tester = new TextUITester("F\nAirport1\nAirport2\nQ");
            Scanner scanner = new Scanner(System.in);

            frontend = new FlightFinderFrontendFD(backend2, scanner);
            frontend.runCommandLoop();



            String output = tester.checkOutput();

            //System.out.println(backend2.toString());



            String result = "Here is the list of current airports:\nAirport1\n" +
                    "Airport2\nAirport3\nType in the first airport:\nType in the second " +
                    "airport:\nList of airports in the shortest path:\nAirport1\nAirport2\nCost of the shortest path: 1.0";



            //System.out.println(output);
            //System.out.println(result);

            if (!output.contains(result)) {
                fail("Fail");
            }


            AirportInterface airport1 = null;


            for (AirportInterface airport : backend2.getAirports()) {
                if (airport.getAirport().trim().equals("Airport1")) {
                    // If so, return airport instance
                    airport1 = airport;
                }
            }


            AirportInterface airport2 = null;


            for (AirportInterface airport : backend2.getAirports()) {
                if (airport.getAirport().trim().equals("Airport2")) {
                    // If so, return airport instance
                    airport2 = airport;
                }
            }


            AirportInterface airport3 = null;


            for (AirportInterface airport : backend2.getAirports()) {
                if (airport.getAirport().trim().equals("Airport3")) {
                    // If so, return airport instance
                    airport3 = airport;
                }
            }


            List<AirportInterface> list = new ArrayList<>();

            list.add(airport1);
            list.add(airport2);
            //list.add(airport3);

            List<AirportInterface> actual = backend2.getPath(airport1, airport2);

            if (!list.equals(actual)) {
                fail("Fail");
            }

            assertEquals(list,actual);


            double actualCost = backend2.shortestPathCost(airport1, airport2);

            assertEquals(actualCost,1.0);





        } catch (Exception e) {
            e.printStackTrace();
        }
    }








}
