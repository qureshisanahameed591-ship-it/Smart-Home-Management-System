# Smart Home Management System (Java OOP)

**Submitted To:** Sir Gulshair Lagari

**Group Members:**
-Sana Hameed (Roll No:2k26/csm/111 )
- [Yahan Dusri Ladki Ka Naam Likhein] (Roll No: 2k26/csm/114)

---
*(Description)*
Console-based Smart Home Management System built using **Core Java** and **Object-Oriented Programming**. It runs in the terminal and simulates home devices and sensors. It does not connect to real hardware.
# Smart Home Management System (Java OOP)

Console-based Smart Home Management System built using Core Java and Object-Oriented Programming.
Runs entirely in the terminal; simulates home devices and sensors (no real hardware).

> Academic simulation only. Not a real security / fire / electrical system.

## Features
- View rooms and devices
- Toggle devices ON/OFF
- Adjust light brightness, fan speed, AC temperature
- Lock / unlock simulated smart door
- Simulated temperature, motion, smoke, water-level readings
- Activate / deactivate test alarm
- Scenes: Morning / Night / Study / Away
- Schedule device to turn ON/OFF after N seconds
- Estimate 1-hour energy use and cost
- Save text report to data/home-report.txt

## Technologies
- Java JDK 17+
- Standard library only

## How to Run (Windows PowerShell)

```powershell
javac -d out (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp out smarthome.app.Main

pwd
Get-Content README.md -TotalCount 5
$readme = @'
# Smart Home Management System (Java OOP)

Console-based Smart Home Management System built using Core Java and Object-Oriented Programming.
Runs entirely in the terminal; simulates home devices and sensors (no real hardware).

> Academic simulation only. Not a real security / fire / electrical system.

## Features
- View rooms and devices
- Toggle devices ON/OFF
- Adjust light brightness, fan speed, AC temperature
- Lock / unlock simulated smart door
- Simulated temperature, motion, smoke, water-level readings
- Activate / deactivate test alarm
- Scenes: Morning / Night / Study / Away
- Schedule device to turn ON/OFF after N seconds
- Estimate 1-hour energy use and cost
- Save text report to data/home-report.txt

## Technologies
- Java JDK 17+
- Standard library only

## How to Run (Windows PowerShell)

javac -d out (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp out smarthome.app.Main

## Project Structure

src/smarthome/
  app/         Main.java
  devices/     SmartDevice, Light, Fan, AirConditioner, SmartDoor, AlarmDevice
  interfaces/  Switchable, Lockable
  models/      Room, SmartHome
  sensors/     Sensor, Temperature/Motion/Smoke/WaterLevel sensors
  services/    Alarm, Automation, Energy, Scheduler, Report
  utils/       Console
data/          home-report.txt (generated at runtime)

## OOP Concepts Demonstrated

- Encapsulation - private fields with getters/setters
- Abstraction - SmartDevice, Sensor abstract classes
- Inheritance - Light, Fan, AC, Door, Alarm extend SmartDevice
- Polymorphism - devices managed via SmartDevice parent type
- Interfaces - Switchable, Lockable
- Composition - Room has-a list of devices
- Aggregation - SmartHome has-a list of rooms
- Method Overriding - getStatus(), read(), getHourlyKwh()
- Collections - List, ArrayList
- Exception Handling - try/catch, IllegalArgumentException
- File Handling - ReportService writes data/home-report.txt
- Threads (light) - SchedulerService uses java.util.Timer

## Sample Devices
- Lights: L1 (Living Room), L2 (Bedroom)
- Fans: F1 (Living Room), F2 (Bedroom)
- AC: AC1 (Living Room)
- Door: D1 (Hall)
- Alarms: AL1, AL2

## Menu Options
1. View rooms and devices
2. Toggle device ON/OFF
3. Adjust device (brightness/speed/temp)
4. Lock / Unlock door
5. Read sensors
6. Activate alarm
7. Deactivate alarm
8. Apply scene
9. Schedule device ON/OFF
10. Energy and cost estimate
11. Save report
12. View notifications
13. Exit
