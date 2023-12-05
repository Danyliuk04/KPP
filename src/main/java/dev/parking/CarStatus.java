package dev.parking;

public enum CarStatus {
    AWAITS_ENTRY("Awaits Entry"),
    ON_PARKING_SLOT("On Parking Slot"),
    EXITING("Exiting");

    private final String prettyString;

    CarStatus(String prettyString) {
        this.prettyString = prettyString;
    }

    @Override
    public String toString() {
        return prettyString;
    }
}
