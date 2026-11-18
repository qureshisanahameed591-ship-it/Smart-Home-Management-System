package smarthome.devices;
public class Fan extends SmartDevice {
    private int speed;
    public Fan(String id, String name, String roomName) {
        super(id, name, roomName); this.speed = 3;
    }
    public int getSpeed() { return speed; }
    public void setSpeed(int s) {
        if (s < 1 || s > 5) throw new IllegalArgumentException("Speed must be 1-5");
        this.speed = s;
    }
    @Override public double getHourlyKwh() {
        return isOn() ? 0.02 + speed * 0.01 : 0.0;
    }
    @Override public String getStatus() {
        return isOn() ? "ON (speed=" + speed + ")" : "OFF";
    }
}