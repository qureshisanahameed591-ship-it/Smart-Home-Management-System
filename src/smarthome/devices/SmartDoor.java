package smarthome.devices;
import smarthome.interfaces.Lockable;
public class SmartDoor extends SmartDevice implements Lockable {
    private boolean locked;
    public SmartDoor(String id, String name, String roomName) {
        super(id, name, roomName); this.locked = true;
    }
    @Override public void lock() { this.locked = true; turnOff(); }
    @Override public void unlock() { this.locked = false; }
    @Override public boolean isLocked() { return locked; }
    @Override public String getStatus() { return locked ? "LOCKED" : "UNLOCKED"; }
}