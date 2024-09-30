import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is the FlightFinder Frontend part of the project
 */
public class FlightFinderFrontendFD implements FlightFinderFrontendInterface {

    private Scanner userInput;
    private FlightFinderBackendInterface backend;


    /**
     * Contructor for the FlightFinder frontend
     * @param backend
     * @param userInput
     */
    public FlightFinderFrontendFD(FlightFinderBackendInterface backend, Scanner userInput) {
        this.userInput = userInput;
        this.backend = backend;
    }

    /**
     * private helper method for the display
     */
    private void hr() {
        System.out.println(
                "-------------------------------------------------------------------------------");
    }


    /**
     * The main command loop that controls the functionality of the program and allows the user to interact
     * with different buttons, calls the required methods
     */
    @Override
    public void runCommandLoop() {
        hr();
        System.out.println("Welcome to the FlightFinder App.");
        hr();

        List<AirportInterface> airportsList;
        AirportInterface airport;
        char command = '\0';

        while (command != 'Q') {
            command = this.mainMenuPrompt();

            switch (command) {
                case 'L':
                    loadData();
                    break;
                case 'R':
                    airport = chooseAirportToRemove();
                    removeAirport(airport);
                    break;
                case 'E':
                    airportsList = chooseEdgeToRemove();
                    removeEdge(airportsList);
                    break;
                case 'F':
                    airportsList = chooseAirports();
                    getShortestPath(airportsList);
                    break;
                case 'S':
                    airportsList = chooseAirports();
                    List<AirportInterface> restraintsRestricted = chooseRestrictedRestraints();
                    getShortestPathWithRestrictedRestraint(airportsList, restraintsRestricted);
                    break;
                case 'P':
                    airportsList = chooseAirports();
                    List<AirportInterface> restraintsRequired = chooseRequiredRestraints();
                    getShortestPathWithRequiredRestraint(airportsList, restraintsRequired);
                case 'Q':
                    break;
                default:
                    System.out.println(
                            "Didn't recognize that command.  Please type one of the letters presented within []s to identify the command you would like to choose.");
                    break;
            }
        }

        hr(); // thank user before ending this application
        System.out.println("Thank you for using the FlightFinder App.");
        hr();


    }

    /**
     * Utilizes the functionality of getting the shortest path with required restraints and displays results to the user
     * @param airports - List of Airports that contains the starting and ending Airports
     * @param restraints - List of Airports that make up the required restraints
     */

    public void getShortestPathWithRequiredRestraint(List<AirportInterface> airports, List<AirportInterface> restraints) {
        if (airports.size() == 1 || airports.size() == 0) {
            System.out.println("Please choose valid options");
            return;
        }
        AirportInterface airport1 = airports.get(0);
        AirportInterface airport2 = airports.get(1);

        try {
            List<AirportInterface> list = backend.getPathWithRequiredRestraints(airport1, airport2, restraints);
            System.out.println("List of airports in the shortest path:");
            for (int i = 0; i < list.size(); i ++) {
                System.out.println(list.get(i).getAirport());
            }
            System.out.print("Cost of the shortest path with required restraints: ");
            System.out.println(backend.shortestPathCostWithRequiredRestraints(airport1, airport2, restraints));
        } catch (NoSuchElementException e) {
            System.out.println("Cannot get path with these airports or restraints");
        }

    }

    /**
     * Allows the user to choose the required restraints
     * @return List of Airports that make up the chosen required restraints
     */

    public List<AirportInterface> chooseRequiredRestraints() {
        List<AirportInterface> restraints = new ArrayList<>();
        List<AirportInterface> options = backend.getAirports();
        System.out.println("Here is the list of current airport restraints to choose from:");
        for (int i = 0; i < options.size(); i ++) {
            System.out.println(options.get(i).getAirport());
        }
        while (true) {
            System.out.println("Type in a required restraint airport:");
            String input = userInput.nextLine().trim();
            if (input.length() == 0) { // an empty string ends this loop and method call
                return restraints;
            } else {
                for (int i = 0; i < options.size(); i ++) {
                    if (input.equals(options.get(i).getAirport())) {
                        restraints.add(options.get(i));
                    }
                }
            }
        }
    }

    /**
     * Utilizes the functionality of getting the shortest path with restricted restraints and displays results to the user
     * @param airports - List of Airports that contains the starting and ending Airports
     * @param restraints - List of Airports that make up the restricted restraints
     */
    public void getShortestPathWithRestrictedRestraint(List<AirportInterface> airports, List<AirportInterface> restraints) {
        if (airports.size() == 1 || airports.size() == 0) {
            System.out.println("Please choose valid options");
            return;
        }
        AirportInterface airport1 = airports.get(0);
        AirportInterface airport2 = airports.get(1);
        try {
            List<AirportInterface> list = backend.getPathWithRestrictedRestraints(airport1, airport2, restraints);
            System.out.println("List of airports in the shortest path:");
            for (int i = 0; i < list.size(); i ++) {
                System.out.println(list.get(i).getAirport());
            }
            System.out.print("Cost of the shortest path with restricted restraints: ");
            System.out.println(backend.shortestPathCostWithRestrictedRestraints(airport1, airport2, restraints));
        } catch (NoSuchElementException e) {
            System.out.println("Cannot get path with these airports or restraints");
        }

    }

    /**
     * Allows the user to choose the restricted restraints
     * @return List of Airports that make up the chosen restricted restraints
     */

    public List<AirportInterface> chooseRestrictedRestraints() {
        List<AirportInterface> restraints = new ArrayList<>();
        List<AirportInterface> options = backend.getAirports();
        System.out.println("Here is the list of current airport restraints to choose from:");
        for (int i = 0; i < options.size(); i ++) {
            System.out.println(options.get(i).getAirport());
        }
        while (true) {
            System.out.println("Type in a restricted restraint airport:");
            String input = userInput.nextLine().trim();
            if (input.length() == 0) { // an empty string ends this loop and method call
                return restraints;
            } else {
                for (int i = 0; i < options.size(); i ++) {
                    if (input.equals(options.get(i).getAirport())) {
                        restraints.add(options.get(i));
                    }
                }
            }
        }
    }

    /**
     * The main menu that is displayed to the user that allows them to choose a command to run the program
     * @return a character that represents a command which will be inputted into the runCommandLoop() method
     */
    @Override
    public char mainMenuPrompt() {
        System.out.println("Choose a command from the list below:");
        System.out.println("    [L]oad data from file");
        System.out.println("    [R]emove an Aiport");
        System.out.println("    Remove an [E]dge Between 2 Airports");
        System.out.println("    [F]ind shortest path between 2 Airports");
        System.out.println("    Find [S]hortest path between 2 Airports Restricted Restraint");
        System.out.println("    Find shortest [P]ath between 2 Airports Required Restraint");
        System.out.println("    [Q]uit");

        // read in user's choice, and trim away any leading or trailing whitespace
        System.out.println("Choose command: ");
        String input = userInput.nextLine().trim();
        if (input.length() == 0) // if user's choice is blank, return null character
            return '\0';
        // otherwise, return an uppercase version of the first character in input
        return Character.toUpperCase(input.charAt(0));


    }

    /**
     * Allows the user to load a file with data
     */

    @Override
    public void loadData() {
        System.out.println("Enter the name of the file to load: ");
        String filename = userInput.nextLine().trim();
        try {
            backend.loadData(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find or load file: " + filename);
            return;
        }

        //System.out.println("\n");
        System.out.println("Data loaded successfully");

    }

    /**
     * Allows the user to choose an aiport the wish to remove
     * @return Airport that user wants to remove
     */
    @Override
    public AirportInterface chooseAirportToRemove() {
        AirportInterface airport = null;
        List<AirportInterface> options = backend.getAirports();
        System.out.println("Here is the list of current airports:");
        for (int i = 0; i < options.size(); i ++) {
            System.out.println(options.get(i).getAirport());
        }
        while (true) {
            System.out.println("Type in the airport you wish to remove:");
            String input = userInput.nextLine().trim();
            if (input.length() == 0) { // an empty string ends this loop and method call
                return airport;
            } else {
                for (int i = 0; i < options.size(); i ++) {
                    if (input.equals(options.get(i).getAirport())) {
                        return options.get(i);
                    }
                }
            }
        }
    }

    /**
     * Removes the airport the user wishes to remove, displays outcome to the user
     * @param airport the Airport that is inputted to remove
     */

    //add the nonvalid option here too
    @Override
    public void removeAirport(AirportInterface airport) {

        if (airport == null) {
            System.out.println("Please choose a valid option");
            return;
        }
        try {
            backend.removeAirport(airport);
            System.out.println("Airport removed successfully");
        } catch (NoSuchElementException e) {
            System.out.println("Airport couldn't be removed");
        }




    }

    /**
     * Allows the user to choose an edge to remove
     * @returns a list of Airports, the first one in the list is the starting airport of the edge, the second on in the list
     * is the ending airport
     */
    @Override
    public List<AirportInterface> chooseEdgeToRemove() {
        List<AirportInterface> airportList = new ArrayList<>();
        List<AirportInterface> options = backend.getAirports();
        int count = 0;
        while (count != 2) {
            System.out.println("Here is the list of current airports and edges:");
            for (int i = 0; i < options.size(); i ++) {
                System.out.println(options.get(i).getAirport() + " | path: " + options.get(i).getPaths());
            }
            System.out.println("Type in the first airport:");
            String input = userInput.nextLine().trim();
            count ++;
            if (input.length() == 0) { // an empty string ends this loop and method call
                return airportList;
            } else {
                for (int i = 0; i < options.size(); i ++) {
                    if (input.equals(options.get(i).getAirport())) {
                        airportList.add(options.get(i));
                    }
                }
            }


            System.out.println("Type in the second airport:");
            input = userInput.nextLine().trim();
            count ++;
            if (input.length() == 0) { // an empty string ends this loop and method call
                return airportList;
            } else {
                for (int i = 0; i < options.size(); i ++) {
                    if (input.equals(options.get(i).getAirport())) {
                        airportList.add(options.get(i));
                    }
                }
            }
        }
        return airportList;
    }

    /**
     * Removes an edge, displays outcome to the user
     * @param EdgeToRemove - a list of inputted Airports, with the first one in the list being
     *                     the starting airport of the edge and the second being the ending one
     */
    @Override
    public void removeEdge(List<AirportInterface> EdgeToRemove) {
        if (EdgeToRemove.size() == 1 || EdgeToRemove.size() == 0) {
            System.out.println("Please choose valid options");
            return;
        }
        AirportInterface airport1 = EdgeToRemove.get(0);
        AirportInterface airport2 = EdgeToRemove.get(1);



        try {
            backend.removeAirportEdge(airport1, airport2);
            System.out.println("Edge removed successfully");
        } catch (NoSuchElementException e) {
            System.out.println("Edge couldn't be removed");
            return;
        }




    }

    /**
     * Prompts and allows the user to choose airports for various methods
     * @return a List of Airports
     */
    @Override
    public List<AirportInterface> chooseAirports() {
        List<AirportInterface> airportList = new ArrayList<>();
        List<AirportInterface> options = backend.getAirports();
        int count = 0;
        while (count != 2) {
            System.out.println("Here is the list of current airports:");
            for (int i = 0; i < options.size(); i ++) {
                System.out.println(options.get(i).getAirport() );
            }
            System.out.println("Type in the first airport:");
            String input = userInput.nextLine().trim();
            count ++;
            if (input.length() == 0) { // an empty string ends this loop and method call
                return airportList;
            } else {
                for (int i = 0; i < options.size(); i ++) {
                    if (input.equals(options.get(i).getAirport())) {
                        airportList.add(options.get(i));
                    }
                }
            }


            System.out.println("Type in the second airport:");
            input = userInput.nextLine().trim();
            count ++;
            if (input.length() == 0) { // an empty string ends this loop and method call
                return airportList;
            } else {
                for (int i = 0; i < options.size(); i ++) {
                    if (input.equals(options.get(i).getAirport())) {
                        airportList.add(options.get(i));
                    }
                }
            }
        }
        return airportList;
    }


    /**
     * Gets the shortest path between two airports, displays outcome to the user
     * @param airports - list of airports to get path from, first one in the list is starting airport, second one
     *                 is ending airport
     */
    @Override
    public void getShortestPath(List<AirportInterface> airports) {
        if (airports.size() == 1 || airports.size() == 0) {
            System.out.println("Please choose valid options");
            return;
        }
        AirportInterface airport1 = airports.get(0);
        AirportInterface airport2 = airports.get(1);

        try {
            List<AirportInterface> list = backend.getPath(airport1, airport2);
            System.out.println("List of airports in the shortest path:");
            for (int i = 0; i < list.size(); i ++) {
                System.out.println(list.get(i).getAirport());
            }

            System.out.print("Cost of the shortest path: ");
            System.out.println(backend.shortestPathCost(airport1, airport2));
        } catch (NoSuchElementException e) {
            System.out.println("No such path");
        }



    }







}
