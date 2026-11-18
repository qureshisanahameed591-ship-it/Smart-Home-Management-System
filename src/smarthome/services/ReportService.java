package smarthome.services;
import smarthome.devices.SmartDevice;
import smarthome.models.Room;
import smarthome.models.SmartHome;
import smarthome.sensors.Sensor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class ReportService {
    private final SmartHome home;
    private final List<Sensor> sensors;
    public ReportService(SmartHome home, List<Sensor> sensors) {
        this.home = home; this.sensors = sensors;
    }
    public void saveReport() {
        List<String> lines = new ArrayList<>();
        lines.add("===== SMART HOME REPORT =====");
        lines.add("Generated: " + LocalDateTime.now());
        lines.add("Home: " + home.getName());
        lines.add("");
        for (Room room : home.getRooms()) {
            lines.add("Room: " + room.getName());
            for (SmartDevice d : room.getDevices()) lines.add("  - " + d);
            lines.add("");
        }
        lines.add("Sensors:");
        for (Sensor s : sensors)
            lines.add("  - " + s.getName() + " (" + s.getRoomName() + "): " + s.read());
        try {
            Path dir = Path.of("data");
            if (!Files.exists(dir)) Files.createDirectories(dir);
            Path file = dir.resolve("home-report.txt");
            Files.write(file, lines);
            System.out.println("Report saved to: " + file.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to write report: " + e.getMessage());
        }
    }
}