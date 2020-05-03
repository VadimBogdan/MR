package Lab3;

import java.util.ArrayList;

public class Route {
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private int Interval;
    private Vehicle activeVehicle;

    public int getInterval() {
        return Interval;
    }

    public void assignVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public Vehicle getActiveVehicle() {
        return activeVehicle;
    }

    public void setActiveVehicle(Vehicle v) {
        if (vehicles.contains(v) && v.IsWorking) {
            activeVehicle = v;
            Interval = v.Interval;
        }
    }

    public void updateRoute() {
        if (activeVehicle.IsWorking) { return; }

        for (Vehicle v: vehicles) {
            if (v != activeVehicle && v.IsWorking) {
                activeVehicle = v;
                Interval = activeVehicle.Interval;
                return;
            }
        }
        Interval += activeVehicle.Interval;
    }
}
