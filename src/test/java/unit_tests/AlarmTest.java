package unit_tests;

import org.junit.Test;
import tirepressuremonitoringsystem.Alarm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AlarmTest {

    @Test
    public void fires_alarm_when_pressure_is_too_low() {
        FakeAlarm alarm = new FakeAlarm(16);
        alarm.check();
        assertThat(alarm.out, is("Alarm activated!"));
    }

    @Test
    public void alarm_is_not_fired_when_pressure_is_inside_safety_range() {
        FakeAlarm alarm = new FakeAlarm(17);
        alarm.check();
        assertThat(alarm.out, is(""));

        alarm = new FakeAlarm(21);
        alarm.check();
        assertThat(alarm.out, is(""));
    }

    @Test
    public void fires_alarm_when_pressure_is_too_high() {
        FakeAlarm alarm = new FakeAlarm(22);
        alarm.check();
        assertThat(alarm.out, is("Alarm activated!"));
    }

    private class FakeAlarm extends Alarm {
        public String out;
        private int pressure;

        public FakeAlarm(int pressure) {
            out = "";
            this.pressure = pressure;
        }

        @Override
        protected void print(String message) {
            out = message;
        }

        @Override
        protected double getPressure() {
            return pressure;
        }
    }
}
