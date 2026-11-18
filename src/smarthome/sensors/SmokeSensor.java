package smarthome.sensors;
public class SmokeSensor extends Sensor {
    public SmokeSensor(String id, String name, String roomName) { super(id, name, roomName); }
    @Override public String read() { return random.nextInt(300) + " ppm"; }
    @Override public String getUnit() { return "ppm"; }
}