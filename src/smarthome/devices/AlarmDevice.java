package smarthome.devices;
public class AlarmDevice extends SmartDevice {
    public AlarmDevice(String id, String name, String roomName) { super(id, name, roomName); }
    @Override public String getStatus() { return isOn() ? "ALARM ACTIVE" : "SILENT"; }
}