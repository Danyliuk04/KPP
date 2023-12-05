package dev.parking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Car extends Thread {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger();
    private final Integer id;
    private final String carName;
    private CarStatus status;
    private final BlockingQueue<ICarTask> tasksQueue;
    private Boolean isFree;

    public Car(String carName) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.carName = carName;
        this.status = CarStatus.AWAITS_ENTRY;
        this.tasksQueue = new LinkedBlockingQueue<>();
        this.isFree = true;
    }

    public void run() {
        while (true) {
            try {
                ICarTask task = tasksQueue.take();
                task.execute(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addTask(ICarTask task) {
        tasksQueue.add(task);
    }

    public Integer getCarId() {
        return id;
    }

    public String getCarName() {
        return carName;
    }

    public CarStatus getStatus() {
        return status;
    }

    public BlockingQueue<ICarTask> getTasksQueue() {
        return tasksQueue;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setNextStatus(){
        if(status == CarStatus.AWAITS_ENTRY) {
            status = CarStatus.ON_PARKING_SLOT;
        } else if(status == CarStatus.ON_PARKING_SLOT) {
            status = CarStatus.EXITING;
        }
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carName='" + carName + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
