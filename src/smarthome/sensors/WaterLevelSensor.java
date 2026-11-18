package smarthome.sensors;
public class WaterLevelSensor extends Sensor {
    public WaterLevelSensor(String id, String name, String roomName) { super(id, name, roomName); }
    @Override public String read() { return String.format("%.1f %%", random.nextDouble() * 100); }
    @Override public String getUnit() { return "%"; }
}