import java.time.LocalDate;
import java.time.ZonedDateTime;

public class Period {
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;

    public Period(ZonedDateTime startDate, ZonedDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }
}
