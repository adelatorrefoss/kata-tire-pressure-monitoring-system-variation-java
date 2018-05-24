package tirepressuremonitoringsystem;

public class Alarm {
    private final double LowPressureThreshold = 17;
    private final double HighPressureThreshold = 21;

    private Sensor sensor = new Sensor();

    private boolean alarmOn = false;

    public void check() {
        double psiPressureValue = getPressure();

        if (psiPressureValue < LowPressureThreshold || HighPressureThreshold < psiPressureValue) {
            if(!isAlarmOn()) {
                alarmOn = true;
                print("Alarm activated!");
            }
        } else {
            if(isAlarmOn()) {
                alarmOn = false;
                print("Alarm deactivated!");
            }
        }
    }

    protected void print(String message) {
        System.out.println(message);
    }

    protected double getPressure() {
        return sensor.popNextPressurePsiValue();
    }

    private boolean isAlarmOn() {
        return alarmOn;
    }
}
