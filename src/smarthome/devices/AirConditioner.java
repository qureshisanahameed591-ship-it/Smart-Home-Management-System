package smarthome.devices;
public class AirConditioner extends SmartDevice {
    private int temperature;
    public AirConditioner(String id, String name, String roomName) {
        super(id, name, roomName); this.temperature = 24;
    }
    public int getTemperature() { return temperature; }
    public void setTemperature(int t) {
        if (t < 16 || t > 30) throw new IllegalArgumentException("AC temperature must be 16-30");
        this.temperature = t;
    }
    @Override public double getHourlyKwh() { return isOn() ? 1.2 : 0.0; }
    @Override public String getStatus() {
        return isOn() ? "ON (set=" + temperature + " C)" : "OFF";
    }
}