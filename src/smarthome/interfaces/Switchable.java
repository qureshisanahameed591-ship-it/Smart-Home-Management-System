package smarthome.interfaces;
public interface Switchable {
    void turnOn();
    void turnOff();
    void toggle();
    boolean isOn();
}