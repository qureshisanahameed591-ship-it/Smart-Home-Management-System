package smarthome.services;
import smarthome.devices.AlarmDevice;
public class AlarmService {
    private final AlarmDevice alarm;
    public AlarmService(AlarmDevice alarm) { this.alarm = alarm; }
    public void activate() { alarm.turnOn(); System.out.println("[!] Alarm ACTIVATED"); }
    public void deactivate() { alarm.turnOff(); System.out.println("[ ] Alarm DEACTIVATED"); }
}