package smarthome.models;
import smarthome.devices.SmartDevice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class SmartHome {
    private final String name;
    private final List<Room> rooms = new ArrayList<>();
    public SmartHome(String name) { this.name = name; }
    public String getName() { return name; }
    public void addRoom(Room r) { rooms.add(r); }
    public List<Room> getRooms() { return Collections.unmodifiableList(rooms); }
    public SmartDevice findDeviceById(String id) {
        for (Room r : rooms) {
            SmartDevice d = r.findDeviceById(id);
            if (d != null) return d;
        }
        return null;
    }
    public List<SmartDevice> getAllDevices() {
        List<SmartDevice> all = new ArrayList<>();
        for (Room r : rooms) all.addAll(r.getDevices());
        return all;
    }
}