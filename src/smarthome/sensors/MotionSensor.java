package smarthome.sensors;
public class MotionSensor extends Sensor {
    public MotionSensor(String id, String name, String roomName) { super(id, name, roomName); }
    @Override public String read() { return random.nextBoolean() ? "MOTION DETECTED" : "NO MOTION"; }
    @Override public String getUnit() { return ""; }
}