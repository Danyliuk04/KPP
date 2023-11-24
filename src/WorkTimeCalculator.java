import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class WorkTimeCalculator {
    public static long calculateWorkHours(LocalDate startDate, LocalDate endDate, List<Period> vacationPeriods, List<Integer> weekendDays, Duration workDayDuration) {
        long workHours = 0;
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

            if (!isInVacationPeriod(currentDate, vacationPeriods) && !weekendDays.contains(dayOfWeek.getValue())) {
                workHours += workDayDuration.toHours();
            }

            currentDate = currentDate.plusDays(1);
        }
        if (currentDate.isEqual(endDate)) {
            LocalDate finalDate = endDate.plusDays(1);
            DayOfWeek endDayOfWeek = finalDate.getDayOfWeek();
            if (!isInVacationPeriod(endDate, vacationPeriods) && !weekendDays.contains(endDayOfWeek.getValue())) {
                workHours += workDayDuration.toHours();
            }
        }

        return workHours;
    }

//додати зони до визначення зону
    // кількість днів кожного місяця для поточного року
    public static boolean isInVacationPeriod(LocalDate date, List<Period> vacationPeriods) {
        for (Period period : vacationPeriods) {
            if (!date.isBefore(period.getStartDate()) && !date.isAfter(period.getEndDate())) {
                return true;
            }
        }

        return false;
    }
}
