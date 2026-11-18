package smarthome.services;
import smarthome.devices.SmartDevice;
import smarthome.models.SmartHome;
public class EnergyService {
    private final SmartHome home;
    private final double ratePerKwh;
    public EnergyService(SmartHome home, double ratePerKwh) {
        this.home = home; this.ratePerKwh = ratePerKwh;
    }
    public void printEstimate() {
        double total = 0;
        System.out.println("------ Energy Estimate (1 hour) ------");
        for (SmartDevice d : home.getAllDevices()) {
            double kwh = d.getHourlyKwh();
            if (kwh > 0) System.out.printf("%-25s %.3f kWh%n", d.getName(), kwh);
            total += kwh;
        }
        System.out.printf("TOTAL  : %.3f kWh%n", total);
        System.out.printf("COST   : %.2f (at %.2f per kWh)%n", total * ratePerKwh, ratePerKwh);
    }
}