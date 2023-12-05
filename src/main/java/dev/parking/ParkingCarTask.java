package dev.parking;

public class ParkingCarTask implements ICarTask {
    private final ITaskCallback callback;
    private final Integer executionTime;
    private final Parking parking;

    public ParkingCarTask(ITaskCallback callback, Integer executionTime, Parking parking) {
        this.callback = callback;
        this.executionTime = executionTime;
        this.parking = parking;
    }

    @Override
    public void execute(Car car) {
        car.setFree(false);
        if(car.getStatus() == CarStatus.AWAITS_ENTRY) {
            parking.parkCar(car);
        }

        try {
            Thread.sleep(executionTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            if(car.getStatus() == CarStatus.ON_PARKING_SLOT) {
                parking.leaveParkingSlot(car);
            }
            else if(car.getStatus() == CarStatus.EXITING) {
                throw new IllegalStateException("Car is not in the right state");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            car.setFree(true);
            return;
        }
        car.setNextStatus();
        car.setFree(true);
        callback.onTaskCompleted();
    }
}
