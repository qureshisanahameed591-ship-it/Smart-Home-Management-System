package smarthome.devices;
import smarthome.interfaces.Switchable;
public abstract class SmartDevice implements Switchable {
    private final String id;
    private final String name;
    private final String roomName;
    private boolean on;
    protected SmartDevice(String id, String name, String roomName) {
        this.id = id; this.name = name; this.roomName = roomName; this.on = false;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getRoomName() { return roomName; }
    @Override public boolean isOn() { return on; }
    @Override public void turnOn() { this.on = true; }
    @Override public void turnOff() { this.on = false; }
    @Override public void toggle() { this.on = !this.on; }
    public abstract String getStatus();
    public double getHourlyKwh() { return on ? 0.05 : 0.0; }
    @Override public String toString() {
        return String.format("[%s] %s (%s) - %s", id, name, roomName, getStatus());
    }
}