run: Main.class junit5.jar BackendDeveloperTests.class FlightFinderAE.class AlgorithmEngineerTests.class FrontendDeveloperTests.class DataWranglerTests.class
	java Main

Main.class: Main.java Airport.class AirportReader.class
	javac Main.java

Airport.class: Airport.java AirportInterface.class
	javac Airport.java

AirportInterface.class: AirportInterface.java
	javac AirportInterface.java

AirportReader.class: AirportReader.java AirportReaderInterface.class
	javac AirportReader.java

AirportReaderInterface.class: AirportReaderInterface.java
	javac AirportReaderInterface.java

runTests: junit5.jar BackendDeveloperTests.class FlightFinderAE.class AlgorithmEngineerTests.class FrontendDeveloperTests.class DataWranglerTests.class
	java -jar junit5.jar -cp . --select-class=FrontendDeveloperTests
	java -jar junit5.jar -cp . --select-class=DataWranglerTests
	java -jar junit5.jar -cp . --select-class=AlgorithmEngineerTests
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests

runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar -cp . --select-class=DataWranglerTests
DataWranglerTests.class: AirportInterfaceDW.java AirportDW.java AirportReaderInterfaceDW.java AirportReaderDW.java DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java
runAlgorithmEngineerTests: FlightFinderAE.class  AlgorithmEngineerTests.class
	java -jar junit5.jar -cp . -c AlgorithmEngineerTests
AlgorithmEngineerTests.class: AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
FlightFinderAE.class: FlightFinderAE.java FlightFinderAEInterface.class DijkstraGraph.class
	javac FlightFinderAE.java
FlightFinderAEInterface.class: FlightFinderAEInterface.java
	javac FlightFinderAEInterface.java
DijkstraGraph.class: DijkstraGraph.java BaseGraph.class GraphADT.class
	javac DijkstraGraph.java
BaseGraph.class: BaseGraph.java
	javac BaseGraph.java
runFrontendDeveloperTests: FrontendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java FlightFinderFrontendFD.java
	javac AirportFD.java
	javac AirportInterfaceFD.java
	javac FlightFinderBackendFD.java
	javac FlightFinderBackendInterface.java
	javac FlightFinderFrontendFD.java
	javac FlightFinderFrontendInterface.java
	javac -cp .:junit5.jar FrontendDeveloperTests.java

FrontendDeveloperTests.java: 

runBackendDeveloperTests: junit5.jar BackendDeveloperTests.class
	java -jar junit5.jar -cp . -c BackendDeveloperTests

junit5.jar:

BackendDeveloperTests.class: BackendDeveloperTests.java FlightManagerBackendBD.class DataWranglerTests.class
	javac -cp .:junit5.jar BackendDeveloperTests.java

FlightManagerBackendBD.class: FlightManagerBackendBD.java FlightManagerBackendInterfaceBD.class
	javac FlightManagerBackendBD.java

FlightManagerBackendInterfaceBD.class: FlightManagerBackendInterfaceBD.java AirportBD.class AirportReaderBD.class FlightFinderAEBD.class
	javac FlightManagerBackendInterfaceBD.java

FlightManagerBackendBD.java: FlightManagerBackendInterfaceBD.class

AirportBD.class: AirportBD.java AirportInterfaceBD.class
	javac AirportBD.java

AirportInterfaceBD.class: AirportInterfaceBD.java
	javac AirportInterfaceBD.java

AirportReaderBD.class: AirportReaderBD.java AirportBD.class
	javac AirportReaderBD.java

AirportReaderInterfaceBD.class: AirportReaderInterfaceBD.java
	javac AirportReaderInterfaceBD.java

FlightFinderAEBD.class: FlightFinderAEBD.java FlightFinderAEInterfaceBD.class GraphADT.class
	javac FlightFinderAEBD.java

FlightFinderAEInterfaceBD.class: FlightFinderAEInterfaceBD.java
	javac FlightFinderAEInterfaceBD.java

GraphADT.class: GraphADT.java
	javac GraphADT.java
clean:
	rm *.class
