package dev.parking;

import java.util.Random;

public class CarsGenerator {
    private static final String[] CARS_BRANDS = {"BMW", "Audi", "Mercedes", "Toyota", "Lexus",
            "Honda", "Mazda", "Nissan", "Ford", "Volkswagen"};
    private final ParkingManager parkingManager;

    public CarsGenerator(ParkingManager manager){
        this.parkingManager = manager;
    }
    public void generate(int carsCount) {
        for (int i = 0; i < carsCount; i++) {
            Car car = new Car(getRandomCarName());
            car.start();
            parkingManager.addCar(car);
        }
    }

    private static String getRandomCarName() {
        return CARS_BRANDS[(int) (Math.random() * CARS_BRANDS.length)] + " " +
                (char)(new Random().nextInt(26) + 'a') + (int) (Math.random() * 1000);
    }
}
