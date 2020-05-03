package Lab3;

public class Main {
    public static void main(String[] args) {
        Route route = new Route();
        Bus bus = new Bus(10, true);
        Tram tram = new Tram(12, true);
        Trolleybus trolleybus = new Trolleybus(14, true);
        route.assignVehicle(bus);
        route.assignVehicle(tram);
        route.assignVehicle(trolleybus);
        route.setActiveVehicle(bus);
        System.out.println(route.getActiveVehicle());
        bus.IsWorking = false;
        route.updateRoute();
        System.out.println(route.getActiveVehicle());
        System.out.println("Route interval is " + route.getInterval() + ".");
    }
}
