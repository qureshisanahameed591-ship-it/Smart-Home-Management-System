package smarthome.services;
import smarthome.devices.*;
import smarthome.models.SmartHome;
public class AutomationService {
    private final SmartHome home;
    public AutomationService(SmartHome home) { this.home = home; }
    public void applyScene(String scene) {
        switch (scene.toLowerCase()) {
            case "morning" -> applyMorning();
            case "night"   -> applyNight();
            case "study"   -> applyStudy();
            case "away"    -> applyAway();
            default -> System.out.println("Unknown scene.");
        }
    }
    private void applyMorning() {
        for (SmartDevice d : home.getAllDevices()) {
            if (d instanceof Light l) { l.turnOn(); l.setBrightness(60); }
            if (d instanceof AirConditioner ac) { ac.turnOn(); ac.setTemperature(22); }
        }
        System.out.println("Morning scene applied.");
    }
    private void applyNight() {
        for (SmartDevice d : home.getAllDevices()) {
            if (d instanceof Light l) { l.turnOff(); l.setBrightness(10); }
            if (d instanceof Fan f) f.turnOff();
            if (d instanceof SmartDoor door) door.lock();
        }
        System.out.println("Night scene applied.");
    }
    private void applyStudy() {
        for (SmartDevice d : home.getAllDevices()) {
            if (d instanceof Light l) { l.turnOn(); l.setBrightness(90); }
            if (d instanceof Fan f) { f.turnOn(); f.setSpeed(2); }
        }
        System.out.println("Study scene applied.");
    }
    private void applyAway() {
        for (SmartDevice d : home.getAllDevices()) {
            d.turnOff();
            if (d instanceof SmartDoor door) door.lock();
        }
        System.out.println("Away scene applied.");
    }
}