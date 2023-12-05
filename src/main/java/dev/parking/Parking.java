package dev.parking;

public class Parking {
    private final String name;
    private int capacity;
    private int freePlaces;
    public Parking(String name, int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.name = name;
        this.capacity = capacity;
        this.freePlaces = capacity;
    }

    public synchronized void parkCar(Car car) {
        if (freePlaces == 0) {
            throw new IllegalStateException("Parking is full");
        }
        freePlaces--;
    }

    public synchronized void leaveParkingSlot(Car car) {
        freePlaces++;
    }

    public synchronized int getFreePlaces() {
        return freePlaces;
    }
    public synchronized int getCapacity() {
        return capacity;
    }
}
