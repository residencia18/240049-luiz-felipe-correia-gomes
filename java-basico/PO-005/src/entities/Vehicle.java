package entities;

public class Vehicle {
    
    private String model;
    private String licensePlate;

    public Vehicle(String model, String licensePlate) {
        super();
        this.model = model;
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}
