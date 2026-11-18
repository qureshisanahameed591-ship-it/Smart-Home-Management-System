package smarthome.sensors;
public class TemperatureSensor extends Sensor {
    public TemperatureSensor(String id, String name, String roomName) { super(id, name, roomName); }
    @Override public String read() {
        double v = 18 + random.nextDouble() * 14;
        return String.format("%.1f %s", v, getUnit());
    }
    @Override public String getUnit() { return "C"; }
}