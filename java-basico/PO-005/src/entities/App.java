package entities;

import register.Registration;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Registration registration = new Registration();

        int option;

        do {
            System.out.println("======= VEHICULAR TRANSPORT CONTROL PANEL =======");
            System.out.println("");
            System.out.println("[1] - Vehicle Registration");
            System.out.println("[2] - Driver Registration");
            System.out.println("[3] - Conductor Registration");
            System.out.println("[4] - Passenger Registration");
            System.out.println("[5] - Stop Registration");
            System.out.println("[6] - Route Registration");
            System.out.println("[7] - Workday Record");
            System.out.println("[8] - Trip Start Record");
            System.out.println("[9] - Passenger Boarding Record with Card");
            System.out.println("[10] - Checkpoint Record");
            System.out.println("[0] - Exit.");
            System.out.println("");
            System.out.println("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {

                case 1:
                    registration.registerVehicle();
                    break;

                case 2:
                    registration.registerDriver();
                    break;

                case 3:
                    registration.registerConductor();
                    break;

                case 4:
                    registration.registerPassenger();
                    break;

                case 5:
                    registration.registerStops();
                    break;

                case 6:
                    registration.registerRoute();
                    break;

                case 7:
                    registration.recordWorkday();
                    break;

                case 8:
                    registration.recordTripStart();
                    break;

                case 9:
                    registration.recordBoardingWithCard();
                    break;

                case 10:
                    registration.recordCheckpoint();

                case 0:
                    System.out.println("Program terminated!");
                    return;

                default:
                    System.out.println("Invalid option.");
                    break;
            }

        } while (option != 0);

        scanner.close(); // Closes the scanner.
    }
}
