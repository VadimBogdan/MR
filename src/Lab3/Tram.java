package Lab3;

public class Tram extends Vehicle {

    public Tram(int interval, boolean isWorking) {
        super(interval, isWorking);
    }

    @Override
    public String toString() {
        return "Tram with interval: " + Interval + ".";
    }
}
