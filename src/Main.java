import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalTime shiftStartTime = TimetableManager.getShiftStartTime(scanner);
        if (shiftStartTime == null) {
            return;
        }
        LocalTime shiftEndTime = TimetableManager.getShiftEndTime(scanner);
        if (shiftEndTime == null) {
            return;
        }

        if (shiftEndTime.isBefore(shiftStartTime)) {
            System.err.println("End shift time cannot be earlier than the start shift time. Please re-enter.");
            scanner.nextLine();
            shiftStartTime = TimetableManager.getShiftStartTime(scanner);
            shiftEndTime = TimetableManager.getShiftEndTime(scanner);
        }

        Duration workDayDuration = Duration.between(shiftStartTime, shiftEndTime);

        while (true) {
            LocalDate startDate = TimetableManager.getStartDateOfWork(scanner);
            if (startDate == null) {
                break;
            }
            LocalDate endDate = TimetableManager.getEndDateOfWork(scanner);
            if (endDate == null) {
                break;
            }

            if (endDate.isBefore(startDate)) {
                System.err.println("End date cannot be earlier than the start date. Please re-enter.");
                continue;
            }

            DateTimeFormatter vacationDateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            List<Period> vacationPeriods = TimetableManager.getVacationPeriods(scanner, vacationDateFormat);
            List<Integer> weekendDays = TimetableManager.getWeekendDays(scanner);

            long workHours = WorkTimeCalculator.calculateWorkHours(startDate, endDate, vacationPeriods, weekendDays, workDayDuration);
            TimetableManager.displayWorkHours(workHours);
            scanner.nextLine();
        }
    }
}