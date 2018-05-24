package unit_tests;

import org.junit.Test;
import tirepressuremonitoringsystem.Alarm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AlarmTest {

    @Test
    public void fire_alarm_when_pressure_is_too_low() {
        FakeAlarm alarm = new FakeAlarm();
        alarm.check();
        assertThat(alarm.out, is("Alarm activated!"));
    }

    private class FakeAlarm extends Alarm {
        public String out;

        @Override
        protected void print(String message) {
            out = message;
        }

        @Override
        protected double getPressure() {
            return 16;
        }
    }
}

