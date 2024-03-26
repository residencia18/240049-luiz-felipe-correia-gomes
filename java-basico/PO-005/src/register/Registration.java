package register;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import entities.Conductor;
import entities.Boarding;
import entities.Driver;
import entities.Journey;
import entities.Passenger;
import entities.Stop;
import entities.Route;
import entities.Section;
import entities.Vehicle;

public class Registration {

    private Scanner scanner = new Scanner(System.in);

    private ArrayList<Vehicle> vehicleList = new ArrayList<>();
    private ArrayList<Driver> driverList = new ArrayList<>();
    private ArrayList<Conductor> conductorList = new ArrayList<>();
    private ArrayList<Passenger> passengerList = new ArrayList<>();
    private ArrayList<Stop> stopList = new ArrayList<>();
    private ArrayList<Section> sectionList = new ArrayList<>();
    private ArrayList<Route> routeList = new ArrayList<>();
    private ArrayList<Route> routeListJourney = new ArrayList<>();
    private ArrayList<Journey> journeyList = new ArrayList<>();
    private ArrayList<Boarding> boardingList = new ArrayList<>();

    public void registerVehicle() {
        while (true) {
            System.out.println("Enter the vehicle model (or enter 'x' to finish):");
            String model = scanner.nextLine();

            if (model.equalsIgnoreCase("x")) {
                break;
            }

            System.out.println("Enter the vehicle plate:");
            String plate = scanner.nextLine();

            Vehicle vehicle = new Vehicle(model, plate);
            vehicleList.add(vehicle);

            System.out.println("Vehicle registered successfully!");
        }
    }

    public void registerDriver() {
        while (true) {
            System.out.println("Enter the name: (or enter 'x' to finish):");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("x")) {
                break;
            }

            System.out.println("Enter the CPF:");
            String cpf = scanner.nextLine();

            Driver driver = new Driver(name, cpf);
            driverList.add(driver);

            System.out.println("Driver registered successfully!");
        }
    }

    public void registerConductor() {
        while (true) {
            System.out.println("Enter the name: (or enter 'x' to finish):");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("x")) {
                break;
            }

            System.out.println("Enter the CPF:");
            String cpf = scanner.nextLine();

            Conductor conductor = new Conductor(name, cpf);
            conductorList.add(conductor);

            System.out.println("Conductor registered successfully!");
        }
    }

    public void registerPassenger() {
        while (true) {
            System.out.println("Enter the name: (or enter 'x' to finish):");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("x")) {
                break;
            }

            System.out.println("Enter the CPF:");
            String cpf = scanner.nextLine();

            System.out.println("----- Card Type -----");
            System.out.println("[1] - Student Card");
            System.out.println("[2] - Elderly Card");
            System.out.println("[3] - Transportation Card");
            System.out.println("----------------------");

            System.out.println("Choose an option: ");
            int cardType = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            Passenger passenger = new Passenger(name, cpf, cardType);
            passengerList.add(passenger);

            System.out.println("Passenger registered successfully!");
        }
    }

    public void registerStops() {
        while (true) {
            System.out.println("Enter the stop name: (or enter 'x' to finish):");
            String stopName = scanner.nextLine();

            if (stopName.equalsIgnoreCase("x")) {
                break;
            }

            Stop stop = new Stop(stopName);
            stopList.add(stop);

            System.out.println("Stop registered successfully!");
        }
    }

    public void registerSection() {
        while (true) {
            System.out.println("----- Available Stops -----");

            // Displaying available stops
            ArrayList<Stop> stops = getBusStopList();
            for (int i = 0; i < stops.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + stops.get(i).getPoint());
            }

            System.out.println("Enter the option for the departure stop:");
            int departurePoint = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            System.out.println("Enter the option for the destination stop:");
            int destinationPoint = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            // Check if the options are valid
            if (departurePoint < 1 || departurePoint > stops.size() || destinationPoint < 1 || destinationPoint > stops.size()) {
                System.out.println("Invalid option.");
                break; // Exit the loop if the option is invalid
            }

            Stop departureStop = stops.get(departurePoint - 1);
            Stop destinationStop = stops.get(destinationPoint - 1);

            String points = departureStop.getPoint() + " - " + destinationStop.getPoint();

            System.out.println("Enter the estimated duration of the section (in minutes):");
            int interval = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            Section section = new Section(points, interval);
            sectionList.add(section);

            System.out.println("Section registered successfully!");
            System.out.println(" ");

            System.out.println("Enter 'x' to exit or any other key to register another section: ");
            String exit = scanner.nextLine();

            if (exit.equalsIgnoreCase("x")) {
                break; // Exit the loop if the user enters 'x'
            }
        }
    }

    public void registerRoute() {
        while (true) {
            registerSection();

            System.out.println("Enter any other key to continue or 'x' to exit: ");

            String exit = scanner.nextLine();

            if (exit.equalsIgnoreCase("x")) {
                break;
            }

            System.out.println("----- Available Sections -----");

            // Displaying available sections
            ArrayList<Section> sections = getSectionList();
            for (int i = 0; i < sections.size(); i++) {
                System.out.println(
                        "[" + (i + 1) + "] " + sections.get(i).getSection() + ", " + sections.get(i).getInterval() + " minutes");
            }

            Route route = new Route(sections);
            routeList.add(route);

            System.out.println("Route registered successfully!");
            System.out.println(" ");
        }
    }

    public void recordWorkday() {

        // Displaying available routes
        ArrayList<Route> routes = getRouteList();
        for (int i = 0; i < routes.size(); i++) {
            System.out.println("----------------------------------");
            System.out.println("[" + (i + 1) + "] " + routes.get(i).getSections());
        }

        while (true) {

            System.out.println("Select the desired route (or enter '0' to exit):");
            int selectedRoute = scanner.nextInt();
            scanner.nextLine();

            if (selectedRoute == 0) {
                break;
            }

            Route route = routes.get(selectedRoute - 1);
            routeListJourney.add(route);

            // Displaying available drivers
            ArrayList<Driver> drivers = getDriverList();
            for (int i = 0; i < drivers.size(); i++) {
                System.out.println("----------------------------------");
                System.out.println("[" + (i + 1) + "] " + drivers.get(i).getName() + "," + drivers.get(i).getCpf());
            }

            System.out.println("Select the desired driver (or enter '0' to exit):");
            int selectedDriver = scanner.nextInt();
            scanner.nextLine();

            if (selectedDriver == 0) {
                break;
            }

            Driver driverWorkday = drivers.get(selectedDriver - 1);

            // Displaying available vehicles
            ArrayList<Vehicle> vehicles = getVehicleList();
            for (int i = 0; i < vehicles.size(); i++) {
                System.out.println("----------------------------------");
                System.out.println(
                        "[" + (i + 1) + "] " + vehicles.get(i).getModel() + " - Plate: " + vehicles.get(i).getLicensePlate());
            }

            System.out.println("Select the bus (or enter '0' to exit):");
            int selectedVehicle = scanner.nextInt();
            scanner.nextLine();

            if (selectedVehicle == 0) {
                break;
            }

            Vehicle vehicleWorkday = vehicles.get(selectedVehicle - 1);

            // Ask if there will be a conductor or not
            System.out.println("Will there be a conductor for this workday? (y/n) ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("y")) {

                // Displaying available conductors
                ArrayList<Conductor> conductors = getConductorList();
                for (int i = 0; i < conductors.size(); i++) {
                    System.out.println("----------------------------------");
                    System.out.println(
                            "[" + (i + 1) + "] " + conductors.get(i).getName() + "," + conductors.get(i).getCpf());
                }

                System.out.println("Select the desired conductor (or enter '0' to exit):");
                int selectedConductor = scanner.nextInt();
                scanner.nextLine();

                if (selectedConductor == 0) {
                    break;
                }

                Conductor conductorWorkday = conductors.get(selectedConductor - 1);

                Journey journey = new Journey(routes, driverWorkday, conductorWorkday, vehicleWorkday);
                journeyList.add(journey);

            } else {
                Journey journey = new Journey(routes, driverWorkday, vehicleWorkday);
                journeyList.add(journey);
            }

        }

    }

    public void recordTripStart() {
        // Displaying available workdays
        ArrayList<Journey> journeys = getJourneyList();
        for (int i = 0; i < journeys.size(); i++) {
            System.out.println("----------------------------------");
            System.out.println("[" + (i + 1) + "] " + journeys.get(i).getRoutes());
        }

        while (true) {

            System.out.println("Select the desired workday (or enter '0' to exit):");
            int chosenWorkday = scanner.nextInt();
            scanner.nextLine();

            if (chosenWorkday > 0 && chosenWorkday <= journeys.size()) {
                Journey selectedWorkday = journeys.get(chosenWorkday - 1);
                ArrayList<Route> routes = selectedWorkday.getRoutes();

                // Checking if there are routes in the selected workday
                if (!routes.isEmpty()) {
                    System.out.println("Select the desired route:");
                    for (int i = 0; i < routes.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + routes.get(i).getStartRoute());
                    }

                    int chosenRoute = scanner.nextInt();

                    if (chosenRoute > 0 && chosenRoute <= routes.size()) {
                        Route selectedRoute = routes.get(chosenRoute - 1);

                        // Here you can modify the startOfSection of the selected route
                        System.out.println("Enter the start date of the route (format: dd/MM/yyyy HH:mm:ss):");
                        scanner.nextLine(); // Clear the buffer
                        String startDate = scanner.nextLine();

                        // Parse from String to Date (assuming the format dd/MM/yyyy HH:mm:ss)
                        try {
                            Date newDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(startDate);
                            selectedRoute.setStartRoute(newDate);
                            System.out.println("Start date of the route modified successfully to: " + newDate);
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please enter the date in the correct format.");
                        }
                    } else {
                        System.out.println("Invalid option. Please select a valid route.");
                        break;
                    }
                } else {
                    System.out.println("This workday has no routes.");
                    break;
                }
            } else {
                System.out.println("Invalid option. Please select a valid workday.");
                break;
            }
        }

    }

    public void displayInformation() {
        System.out.println("Content of the boarding list:");
        for (Boarding boarding : boardingList) {
            System.out.println("Passenger: " + boarding.getPassenger().getName());
            System.out.println("Boarding Point: " + boarding.getBoardingStop().getPoint());
            System.out.println("Card Type: " + boarding.getPassenger().getCardType());
            System.out.println("Boarding Date and Time: " + boarding.getBoardingDateTime());
            System.out.println("----------------------------------");
        }
    }

    public void recordBoardingWithCard() {

        // Displaying available passengers
        ArrayList<Passenger> passengers = getPassengerList();
        for (int i = 0; i < passengers.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + passengers.get(i).getName());
            System.out.println(passengers.get(i).getCpf());
            System.out.println("Card Type: " + passengers.get(i).getCardType());
        }

        System.out.println("Select the passenger:");
        int position = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        Passenger passenger = new Passenger(passengerList.get(position - 1).getName());
        Passenger cardType = new Passenger(passengerList.get(position - 1).getCardType());

        // Displaying available stops
        ArrayList<Stop> points = getBusStopList();
        for (int i = 0; i < points.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + points.get(i).getPoint());
        }

        System.out.println("Select the point:");
        int point = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        Stop boardingPoint = stopList.get(point - 1);

        Boarding record = new Boarding(passenger, boardingPoint, cardType);
        boardingList.add(record);

        displayInformation();
    }

	public void recordCheckpoint() {
		// Displaying available journeys
		ArrayList<Journey> journeys = getJourneyList();
		for (int i = 0; i < journeys.size(); i++) {
			System.out.println("----------------------------------");
			System.out.println("[" + (i + 1) + "] " + journeys.get(i).getRoutes());
		}
	
		while (true) {
			System.out.println("Select the desired journey (or enter '0' to exit):");
			int chosenJourney = scanner.nextInt();
	
			if (chosenJourney > 0 && chosenJourney <= journeys.size()) {
				Journey selectedJourney = journeys.get(chosenJourney - 1);
				ArrayList<Route> routes = selectedJourney.getRoutes();
	
				// Checking if there are routes in the selected journey
				if (!routes.isEmpty()) {
					System.out.println("Select the desired route:");
	
					for (int i = 0; i < routes.size(); i++) {
						System.out.println("[" + (i + 1) + "] " + routes.get(i).getCheckpoint());
					}
	
					int chosenRoute = scanner.nextInt();
	
					if (chosenRoute > 0 && chosenRoute <= routes.size()) {
						Route selectedRoute = routes.get(chosenRoute - 1);
	
						// Here you can modify the Checkpoint of the selected route
						System.out.println("Enter the checkpoint for the route (format: dd/MM/yyyy HH:mm:ss):");
						scanner.nextLine(); // Clear the buffer
						String checkpoint = scanner.nextLine();
	
						// Parsing the String to Date (assuming the format is dd/MM/yyyy HH:mm:ss)
						try {
							Date newDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(checkpoint);
							selectedRoute.setCheckpoint(newDate);
							System.out.println("Checkpoint successfully recorded for: " + newDate);
						} catch (ParseException e) {
							System.out.println("Invalid date format. Please enter the date in the correct format.");
						}
					} else {
						System.out.println("Invalid option. Please select a valid route.");
						break;
					}
				} else {
					System.out.println("This journey has no routes.");
					break;
				}
			} else {
				System.out.println("Invalid option. Please select a valid journey.");
				break;
			}
		}
	}
		
	public void closeScanner() {
		scanner.close();
	}
	
	// Getters to access the list of vehicles
	public ArrayList<Vehicle> getVehicleList() {
		return vehicleList;
	}
	
	// Getters to access the list of drivers
	public ArrayList<Driver> getDriverList() {
		return driverList;
	}
	
	// Getters to access the list of conductors
	public ArrayList<Conductor> getConductorList() {
		return conductorList;
	}
	
	// Getters to access the list of passengers
	public ArrayList<Passenger> getPassengerList() {
		return passengerList;
	}
	
	// Getters to access the list of bus stops
	public ArrayList<Stop> getBusStopList() {
		return stopList;
	}
	
	// Getters to access the list of sections
	public ArrayList<Section> getSectionList() {
		return sectionList;
	}
	
	// Getters to access the list of routes
	public ArrayList<Route> getRouteList() {
		return routeList;
	}
	
	// Getters to access the list of journeys
	public ArrayList<Journey> getJourneyList() {
		return journeyList;
	}
	
	// Getters to access the list of embarkations
	public ArrayList<Boarding> getBoardingList() {
		return boardingList;
	}  
}