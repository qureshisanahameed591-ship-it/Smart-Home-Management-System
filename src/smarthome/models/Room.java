package smarthome.models;
import smarthome.devices.SmartDevice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Room {
    private final String name;
    private final List<SmartDevice> devices = new ArrayList<>();
    public Room(String name) { this.name = name; }
    public String getName() { return name; }
    public void addDevice(SmartDevice d) { devices.add(d); }
    public List<SmartDevice> getDevices() { return Collections.unmodifiableList(devices); }
    public SmartDevice findDeviceById(String id) {
        for (SmartDevice d : devices) if (d.getId().equalsIgnoreCase(id)) return d;
        return null;
    }
    public void listDevices() {
        if (devices.isEmpty()) { System.out.println("  (no devices)"); return; }
        for (SmartDevice d : devices) System.out.println("  " + d);
    }
}