import java.time.*;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the zone (e.g. Europe/Kyiv): ");
        String selectedZone = scanner.nextLine();

        if (!zoneIds.contains(selectedZone)) {
            System.out.println("Wrong zone. The default system zone is used.");
            selectedZone = ZoneId.systemDefault().toString();
        }

        LocalDate currentDate = LocalDate.now(ZoneId.of(selectedZone));

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(selectedZone));

        int currentYear = currentDate.getYear();

        for (int month = 1; month <= 12; month++) {
            YearMonth yearMonth = YearMonth.of(currentYear, month);
            int daysInMonth = yearMonth.lengthOfMonth();
            System.out.println("Month " + month + ": " + daysInMonth + " days");
        }

        System.out.println("Current date and time in selected zone : " + zonedDateTime);
        System.out.println("Current date and time in local zone : " + LocalDateTime.now());

        while (true) {
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