package smarthome.devices;
public class Light extends SmartDevice {
    private int brightness;
    public Light(String id, String name, String roomName) {
        super(id, name, roomName); this.brightness = 100;
    }
    public int getBrightness() { return brightness; }
    public void setBrightness(int b) {
        if (b < 0 || b > 100) throw new IllegalArgumentException("Brightness must be 0-100");
        this.brightness = b;
    }
    @Override public double getHourlyKwh() {
        return isOn() ? 0.01 + (brightness / 100.0) * 0.09 : 0.0;
    }
    @Override public String getStatus() {
        return isOn() ? "ON (brightness=" + brightness + "%)" : "OFF";
    }
}