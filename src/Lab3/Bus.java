package Lab3;

public class Bus extends Vehicle {

    public Bus(int interval, boolean isWorking) {
        super(interval, isWorking);
    }

    @Override
    public String toString() {
        return "Bus with interval: " + Interval + ".";
    }
}
