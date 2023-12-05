package dev.parking;

public class ParkingApplication {
    public static void run(){
        System.out.println("Welcome to the Parking Application!");
        Parking parking = new Parking("Parking", 5);
        ParkingManager parkingManager = new ParkingManager(parking);
        CarsGenerator carsGenerator = new CarsGenerator(parkingManager);
        ParkingGui gui = new ParkingGui(parkingManager);
        parkingManager.setParkingStatusListener(gui);
        carsGenerator.generate(40);
    }
}
