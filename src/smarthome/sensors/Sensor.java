package smarthome.sensors;
import java.util.Random;
public abstract class Sensor {
    private final String id;
    private final String name;
    private final String roomName;
    protected final Random random = new Random();
    protected Sensor(String id, String name, String roomName) {
        this.id = id; this.name = name; this.roomName = roomName;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getRoomName() { return roomName; }
    public abstract String read();
    public abstract String getUnit();
}