package conference.calendar;

import util.exception.DoubleBookingException;

import java.util.Set;
import java.util.UUID;

public class CalendarManager {

    private Calendar calendar;

    public CalendarManager(Calendar calendar) {
        this.calendar = calendar;
    }

    public Set<Pair<UUID, TimeRange>> getTimeRanges() {
        return calendar.getEventUUIDTimeRangesPairs();
    }

    /**
     * @param t TimeRange that is compared with existing TimeRange objects on this calendar
     * @return true iff t conflicts with an existing TimeRange in c
     */
    public boolean timeRangeOccupied(TimeRange t) {
        return calendar.hasConflict(t);
    }

    /**
     * adds the UUID and TimeRange of a Calendarable object to Calendar
     *
     * @param calendarableUUID the UUID of the calendarable object
     * @param timeRange the TimeRange of the calendarable object
     */
    public void addTimeBlock(UUID calendarableUUID, TimeRange timeRange) {
        if (this.timeRangeOccupied(timeRange)) {
            throw new DoubleBookingException();
        } else {
            Pair<UUID, TimeRange> p = new Pair<>(calendarableUUID, timeRange);
            calendar.bookCalendarable(p);
        }
    }
}
