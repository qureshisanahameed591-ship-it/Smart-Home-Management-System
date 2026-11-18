package smarthome.app;
import smarthome.devices.*;
import smarthome.models.*;
import smarthome.sensors.*;
import smarthome.services.*;
import smarthome.utils.Console;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        SmartHome home = buildSampleHome();
        List<Sensor> sensors = buildSensors();
        AlarmDevice alarm = new AlarmDevice("AL1", "Main Alarm", "Hall");
        AlarmService alarmService = new AlarmService(alarm);
        AutomationService automation = new AutomationService(home);
        EnergyService energy = new EnergyService(home, 0.30);
        SchedulerService scheduler = new SchedulerService();
        ReportService reportService = new ReportService(home, sensors);
        Console console = new Console();
        try (console) {
            boolean running = true;
            while (running) {
                printMenu();
                int choice = console.integer("Choose: ", 1, 13);
                try {
                    switch (choice) {
                        case 1  -> listRoomsAndDevices(home);
                        case 2  -> toggleDevice(console, home);
                        case 3  -> adjustDevice(console, home);
                        case 4  -> lockDoor(console, home);
                        case 5  -> readSensors(sensors);
                        case 6  -> alarmService.activate();
                        case 7  -> alarmService.deactivate();
                        case 8  -> applyScene(console, automation);
                        case 9  -> scheduleDevice(console, home, scheduler);
                        case 10 -> energy.printEstimate();
                        case 11 -> reportService.saveReport();
                        case 12 -> viewNotifications();
                        case 13 -> { running = false; System.out.println("Goodbye!"); }
                        default -> System.out.println("Invalid choice.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("[!] " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("[!] Unexpected: " + e.getMessage());
                }
            }
        } finally { scheduler.shutdown(); }
    }
    private static SmartHome buildSampleHome() {
        SmartHome home = new SmartHome("My Home");
        Room living = new Room("Living Room");
        living.addDevice(new Light("L1", "Ceiling Light", "Living Room"));
        living.addDevice(new Fan("F1", "Ceiling Fan", "Living Room"));
        living.addDevice(new AirConditioner("AC1", "AC Unit", "Living Room"));
        Room bedroom = new Room("Bedroom");
        bedroom.addDevice(new Light("L2", "Bedside Lamp", "Bedroom"));
        bedroom.addDevice(new Fan("F2", "Ceiling Fan", "Bedroom"));
        Room hall = new Room("Hall");
        hall.addDevice(new SmartDoor("D1", "Main Door", "Hall"));
        hall.addDevice(new AlarmDevice("AL2", "Hall Alarm", "Hall"));
        home.addRoom(living); home.addRoom(bedroom); home.addRoom(hall);
        return home;
    }
    private static List<Sensor> buildSensors() {
        List<Sensor> list = new ArrayList<>();
        list.add(new TemperatureSensor("T1", "Living Temp", "Living Room"));
        list.add(new MotionSensor("M1", "Hall Motion", "Hall"));
        list.add(new SmokeSensor("S1", "Kitchen Smoke", "Kitchen"));
        list.add(new WaterLevelSensor("W1", "Tank Level", "Roof"));
        return list;
    }
    private static void printMenu() {
        System.out.println("\n===== SMART HOME MENU =====");
        System.out.println(" 1. View rooms & devices");
        System.out.println(" 2. Toggle device ON/OFF");
        System.out.println(" 3. Adjust device");
        System.out.println(" 4. Lock / Unlock door");
        System.out.println(" 5. Read sensors");
        System.out.println(" 6. Activate alarm");
        System.out.println(" 7. Deactivate alarm");
        System.out.println(" 8. Apply scene");
        System.out.println(" 9. Schedule device ON/OFF");
        System.out.println("10. Energy & cost estimate");
        System.out.println("11. Save report");
        System.out.println("12. View notifications");
        System.out.println("13. Exit");
    }
    private static void listRoomsAndDevices(SmartHome home) {
        for (Room room : home.getRooms()) {
            System.out.println("Room: " + room.getName());
            room.listDevices();
        }
    }
    private static void toggleDevice(Console console, SmartHome home) {
        String id = console.line("Device ID: ");
        SmartDevice d = home.findDeviceById(id);
        if (d == null) { System.out.println("Not found."); return; }
        d.toggle();
        System.out.println("Toggled -> " + d);
    }
    private static void adjustDevice(Console console, SmartHome home) {
        String id = console.line("Device ID: ");
        SmartDevice d = home.findDeviceById(id);
        if (d == null) { System.out.println("Not found."); return; }
        if (d instanceof Light l) {
            l.setBrightness(console.integer("Brightness (0-100): ", 0, 100));
            System.out.println("Brightness set.");
        } else if (d instanceof Fan f) {
            f.setSpeed(console.integer("Speed (1-5): ", 1, 5));
            System.out.println("Speed set.");
        } else if (d instanceof AirConditioner ac) {
            ac.setTemperature(console.integer("Temperature (16-30): ", 16, 30));
            System.out.println("Temperature set.");
        } else {
            System.out.println("No adjustable setting.");
        }
    }
    private static void lockDoor(Console console, SmartHome home) {
        String id = console.line("Door ID: ");
        SmartDevice d = home.findDeviceById(id);
        if (!(d instanceof SmartDoor door)) { System.out.println("Not a door."); return; }
        String op = console.line("lock/unlock? ");
        if (op.equalsIgnoreCase("lock")) door.lock();
        else if (op.equalsIgnoreCase("unlock")) door.unlock();
        else { System.out.println("Invalid option."); return; }
        System.out.println("Door -> " + door.getStatus());
    }
    private static void readSensors(List<Sensor> sensors) {
        System.out.println("------ Sensor Readings ------");
        for (Sensor s : sensors)
            System.out.println(s.getName() + " (" + s.getRoomName() + ") : " + s.read());
    }
    private static void applyScene(Console console, AutomationService automation) {
        System.out.println("Scenes: morning / night / study / away");
        automation.applyScene(console.line("Scene: "));
    }
    private static void scheduleDevice(Console console, SmartHome home, SchedulerService scheduler) {
        String id = console.line("Device ID: ");
        SmartDevice d = home.findDeviceById(id);
        if (d == null) { System.out.println("Not found."); return; }
        String state = console.line("Turn on/off? ");
        int secs = console.integer("After seconds: ", 1, 3600);
        scheduler.schedule(d, state.equalsIgnoreCase("on"), secs);
    }
    private static void viewNotifications() {
        System.out.println("Notifications:");
        System.out.println("  - All systems normal.");
        System.out.println("  - No alerts pending.");
    }
}