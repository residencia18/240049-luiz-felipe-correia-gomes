package entities;

import java.util.ArrayList;

public class Journey {
    private ArrayList<Route> journeyRoutes;
    private Driver driver;
    private Conductor conductor; // Can be null if there is no conductor
    private Vehicle vehicle;

    public Journey(ArrayList<Route> routes, Driver driver, Vehicle vehicle) {
        super();
        this.journeyRoutes = routes;
        this.driver = driver;
        this.vehicle = vehicle;
    }

    public Journey(ArrayList<Route> routes, Driver driver, Conductor conductor, Vehicle vehicle) {
        super();
        this.journeyRoutes = routes;
        this.driver = driver;
        this.conductor = conductor;
        this.vehicle = vehicle;
    }

    public ArrayList<Route> getRoutes() {
        return journeyRoutes;
    }

    public Driver getDriver() {
        return driver;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Routes: ").append(getRoutes());

        if (driver != null) {
            sb.append(" - Driver: ").append(driver.getName()); // Add driver details if available
        } else {
            sb.append(" - Driver: null");
        }

        if (conductor != null) {
            sb.append(" - Conductor: ").append(conductor.getName()); // Add conductor details if available
        } else {
            sb.append(" - Conductor: null");
        }

        if (vehicle != null) {
            sb.append(" - Vehicle: ").append(vehicle.getModel()); // Add vehicle details if available
        } else {
            sb.append(" - Vehicle: null");
        }

        return sb.toString();
    }
}
