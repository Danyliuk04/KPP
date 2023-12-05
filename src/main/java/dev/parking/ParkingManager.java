package dev.parking;

import java.util.*;

public class ParkingManager {
    private final Parking parking;
    private final Map<CarStatus, List<Car>> cars;
    private ParkingStatusListener listener;
    private final Random random;

    public ParkingManager(Parking parking) {
        this.parking = parking;
        this.random = new Random();
        this.cars = new HashMap<>(
                Map.of(
                        CarStatus.AWAITS_ENTRY, new ArrayList<>(),
                        CarStatus.ON_PARKING_SLOT, new ArrayList<>(),
                        CarStatus.EXITING, new ArrayList<>()
                )
        );
    }

    public void addCar(Car car) {
        System.out.println("Car " + car.getCarName() + " created");
        cars.get(car.getStatus()).add(car);
        notifyStatusChange();
        tryParkCar();
    }

    private void tryParkCar() {
        System.out.println("Trying to park car");
        Car car = getFreeCar(CarStatus.AWAITS_ENTRY);
        if (car != null && parking.getFreePlaces() > 0) {
            car.addTask(createTask(car));
            notifyStatusChange();
        }
    }

    private ICarTask createTask(Car car) {
        return new ParkingCarTask(() -> {
            notifyStatusChange();
            if (car.getStatus() == CarStatus.EXITING) {
                cars.get(CarStatus.ON_PARKING_SLOT).remove(car);
                cars.get(CarStatus.EXITING).add(car);
                System.out.println("Cars on parking slot after leave:");
                cars.get(CarStatus.ON_PARKING_SLOT).forEach(System.out::println);
                tryParkCar();
            } else if (car.getStatus() == CarStatus.ON_PARKING_SLOT) {
                System.out.println("Car " + car.getCarName() + " entered the parking");
                cars.get(CarStatus.AWAITS_ENTRY).remove(car);
                cars.get(CarStatus.ON_PARKING_SLOT).add(car);
                System.out.println("Cars on parking slot after park car:");
                cars.get(CarStatus.ON_PARKING_SLOT).forEach(System.out::println);
                car.addTask(createTask(car));
            }
        }, 1000+random.nextInt(2000), parking);
    }

    private Car getFreeCar(CarStatus status) {
        return cars.get(status).stream().filter(Car::getFree).findFirst().orElse(null);
    }

    private void notifyStatusChange() {
        if (listener != null) {
            listener.onStatusChange();
        }
    }


    public Parking getParking(){
        return parking;
    }

    public Map<CarStatus,List<Car>> getCars(){
        return cars;
    }

    public void setParkingStatusListener(ParkingStatusListener listener) {
        this.listener = listener;
    }
}
