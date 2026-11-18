package smarthome.services;
import smarthome.devices.SmartDevice;
import java.util.Timer;
import java.util.TimerTask;
public class SchedulerService {
    private final Timer timer = new Timer(true);
    public void schedule(SmartDevice device, boolean turnOn, int seconds) {
        TimerTask task = new TimerTask() {
            @Override public void run() {
                if (turnOn) device.turnOn(); else device.turnOff();
                System.out.println("\n[SCHEDULE] " + device.getName()
                        + " turned " + (turnOn ? "ON" : "OFF"));
            }
        };
        timer.schedule(task, seconds * 1000L);
        System.out.println("Scheduled " + device.getName() + " to turn "
                + (turnOn ? "ON" : "OFF") + " in " + seconds + "s.");
    }
    public void shutdown() { timer.cancel(); }
}