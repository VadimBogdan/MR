package Lab3;

import java.util.Objects;

public abstract class Vehicle {
    protected int Interval;
    protected boolean IsWorking;

    public Vehicle(int interval, boolean isWorking) {
        Interval = interval;
        IsWorking = isWorking;
    }

    public void setIsWorking(boolean v) {
        IsWorking = v;
    }

    public boolean getIsWorking() {
        return IsWorking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Interval == vehicle.Interval &&
                IsWorking == vehicle.IsWorking;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Interval, IsWorking);
    }
}

