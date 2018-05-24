package unit_tests;

import org.junit.Test;
import tirepressuremonitoringsystem.Alarm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AlarmTest {

    @Test
    public void activates_alarm_when_pressure_is_too_low() {
        FakeAlarm alarm = new FakeAlarm(16.0);
        alarm.check();
        assertThat(alarm.messages.get(0), is("Alarm activated!"));
    }

    @Test
    public void alarm_is_not_fired_when_pressure_is_inside_safety_range() {
        FakeAlarm alarm = new FakeAlarm(17.0);
        alarm.check();
        assertThat(alarm.messages.size(), is(0));

        alarm = new FakeAlarm(21.0);
        alarm.check();
        assertThat(alarm.messages.size(), is(0));
    }

    @Test
    public void activates_alarm_when_pressure_is_too_high() {
        FakeAlarm alarm = new FakeAlarm(22.0);
        alarm.check();
        assertThat(alarm.messages.get(0), is("Alarm activated!"));
    }

    @Test
    public void deactivates_activated_alarm_when_pressure_is_inside_safety_range() {
        FakeAlarm alarm = new FakeAlarm(16.0, 20.0);
        alarm.check();

        alarm.check();

        assertThat(alarm.messages.get(0), is("Alarm activated!"));
        assertThat(alarm.messages.get(1), is("Alarm deactivated!"));
    }


    private class FakeAlarm extends Alarm {
        private List<Double> values;
        private int i = 0;
        public List<String> messages;

        public FakeAlarm(Double... values) {
            this.values = Arrays.asList(values);
            this.messages = new ArrayList<>();
        }

        @Override
        protected void print(String message) {
            messages.add(message);
        }

        @Override
        protected double getPressure() {
            double pressure = this.values.get(i);
            i++;
            return pressure;
        }
    }
}
