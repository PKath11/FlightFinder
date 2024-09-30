import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirportReader implements AirportReaderInterface {

  public AirportReader() {
  }



  @Override
  public List<AirportInterface> readFlightsFromFile(String filename)
      throws FileNotFoundException {
    System.out.println(filename);
    // create initial data structures
    ArrayList<AirportInterface> airports = new ArrayList<>();
    Scanner in = new Scanner(new File(filename));
    ArrayList<String> entireFile = new ArrayList<>();
    ArrayList<String> airportNames = new ArrayList<>();
    ArrayList<String> paths1 = new ArrayList<>();
    ArrayList<String> paths2 = new ArrayList<>();
    ArrayList<String> pathWeightsWhole = new ArrayList<>();
    ArrayList<String> pathWeightsString = new ArrayList<>();


    // if filename is null throw exception
    if (filename == null) {

      throw new FileNotFoundException("file was not found");
    }


    // create entire file with " " as delimiter
    while (in.hasNextLine()) {
      String line = in.nextLine();
      for (int i = 0; i < line.split(" ").length; i++) {
        entireFile.add(line.split(" ")[i]);


      }



    }
    // iterate through the file
    for (int i = 0; i < entireFile.size() - 1; i++) {
      if (i >= 6) {
        //System.out.println(entireFile.get(i));
        if (entireFile.get(i).equals("->")) {

          //find airport names
          airportNames.add(entireFile.get(i - 1));

          //find path
          String path =
              entireFile.get(i - 1) + " " + entireFile.get(i) + " " + entireFile.get(i + 1);
          paths1.add(path);
        }
        // get whole weights
        if (entireFile.get(i).contains("weight")) {

          pathWeightsWhole.add(entireFile.get(i));

        }

      }
    }


    // check if multiple airports of same name were added
    for (int a = 0; a < airportNames.size() - 1; a++) {
      //  System.out.println(airportNames.get(i));
      if (airportNames.get(a).equals(airportNames.get(a + 1))) {

        airportNames.remove(a);



      }

    }

    // check if paths has extra paths that need to be put together
    for (int b = 0; b < paths1.size() - 1; b++) {
      //  System.out.println(airportNames.get(i));
      if (paths1.get(b).split(" ")[0].equals(paths1.get(b + 1).split(" ")[0])) {

        paths2.add(paths1.get(b) + ", " + paths1.get(b + 1));

        //paths1.remove(i);

      }

      // paths2.add(last);
    }
    // remove all paths from paths1
    for (int j = 0; j < paths1.size() - 2; j++) {

      paths1.remove(j);

    }

    //add last item to paths2
    paths2.add(paths1.get(paths1.size() - 1));

    int index = 0; // initialize index

    // iterate through paths2
    for (int c = 0; c < paths2.size() - 1; c++) {
      //  System.out.println(Arrays.toString(paths2.get(c).split(" ")[0].toCharArray()));
      // System.out.println(Arrays.toString(paths2.get(c).split(" ")[3].toCharArray()));


      // if the first word in the paths equals the fourth word concatenate the weights together in pathWeightsString
      if (paths2.get(c).split(" ")[0].equals(paths2.get(c).split(" ")[3])) {
        // System.out.println(pathWeightsWhole.get(index));
        // System.out.println(pathWeightsWhole.get(index + 1));

        pathWeightsString.add(pathWeightsWhole.get(index) + ", " + pathWeightsWhole.get(index + 1));
        index = index + 2;
      }
      // otherwise just add to pathWeightsString
      else {
        pathWeightsString.add(pathWeightsWhole.get(c));
      }
    }
    // add last path to pathWeightsString
    pathWeightsString.add(pathWeightsWhole.get(pathWeightsWhole.size() - 1));
/*
    System.out.println(airportNames.size());
     System.out.println(airportNames);
    System.out.println(paths2.size());
    System.out.println(paths2);
    System.out.println(pathWeightsString.size());
    System.out.println(pathWeightsString);
*/

    // create airport objects into list
    for (int d = 0; d < airportNames.size(); d++) {


      airports.add(new Airport(airportNames.get(d), paths2.get(d), pathWeightsString.get(d)));
    }
    // System.out.println(airports);

    return airports; // return list of airports
  }

}
