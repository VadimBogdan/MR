package Lab3;

public class Trolleybus extends Vehicle {

    public Trolleybus(int interval, boolean isWorking) {
        super(interval, isWorking);
    }

    @Override
    public String toString() {
        return "Trolleybus with interval: " + Interval + ".";
    }
}
