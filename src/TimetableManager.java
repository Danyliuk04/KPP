import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class TimetableManager {
    public static List<Period> getVacationPeriods(Scanner scanner, DateTimeFormatter vacationDateFormat) {
        List<Period> vacationPeriods = new ArrayList<>();

        while (true) {
            System.out.print("Enter the start date of the vacation (dd.MM.yyyy) or 0 to finish input: ");
            String startDateStr = scanner.nextLine();

            if (startDateStr.equals("0")) {
                break;
            }

            System.out.print("Enter the end date of the vacation (dd.MM.yyyy): ");
            String endDateStr = scanner.nextLine();

            try {
                ZonedDateTime startDate = LocalDate.parse(startDateStr, vacationDateFormat);
                ZonedDateTime endDate = LocalDate.parse(endDateStr, vacationDateFormat);

                if (endDate.isBefore(startDate)) {
                    System.err.println("End date cannot be earlier than the start date. Please re-enter.");
                    continue;
                }

                vacationPeriods.add(new Period(startDate, endDate));
            } catch (DateTimeParseException e) {
                System.err.println("Error in date format for the vacation. Use the format 'dd.MM.yyyy'. Please re-enter.");
            }
        }

        return vacationPeriods;
    }

    public static ZonedDateTime getShiftStartTime(Scanner scanner) {
        ZonedDateTime startTime = null;
        System.out.print("Enter the shift start time (HH:mm): ");
        do {
            String startTimeStr = scanner.nextLine();

            try {
                LocalTime localTime = LocalTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
                startTime = localTime.atZone(ZoneId.of(selectedZone)).toLocalTime();
                return startTime;
            } catch (DateTimeParseException e) {
                System.err.println("Error in time format. Use the format 'HH:mm'. Please re-enter.");
            }
        } while (startTime == null);
        return null;
    }

    public static ZonedDateTime getShiftEndTime(Scanner scanner) {
        ZonedDateTime endTime = null;
        System.out.print("Enter the shift end time (HH:mm): ");
        do {
            String endTimeStr = scanner.nextLine();

            try {
                endTime = LocalTime.parse(endTimeStr, DateTimeFormatter.ofPattern("HH:mm"));

                return endTime;
            } catch (DateTimeParseException e) {
                System.err.println("Error in time format. Use the format 'HH:mm'. Please re-enter.");
            }
        } while (endTime == null);
        return null;
    }

    public static List<Integer> getWeekendDays(Scanner scanner) {
        List<Integer> weekendDays = new ArrayList<>();

        System.out.print("Enter the day of the week numbers to be considered as weekends (1-Sun, 2-Mon, ..., 7-Sat). Enter 0 to finish input: ");
        while (true) {
            int dayOfWeek = scanner.nextInt();
            if (dayOfWeek == 0) {
                break;
            }
            if (dayOfWeek < 1 || dayOfWeek > 7) {
                System.err.println("The day of the week number should be from 1 to 7. Please re-enter.");
            } else {
                weekendDays.add(dayOfWeek);
            }
        }

        return weekendDays;
    }

    public static ZonedDateTime getStartDateOfWork(Scanner scanner) {
        ZonedDateTime startDate = null;

        while (startDate == null) {
            System.out.print("Enter the start date of work (dd.MM.yyyy) or 0 to finish input: ");
            String startDateStr = scanner.nextLine();

            if (startDateStr.equals("0")) {
                return null;
            }

            try {
                startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeParseException e) {
                System.err.println("Error in date format for work. Use the format 'dd.MM.yyyy'. Please re-enter.");
            }
        }

        return startDate;
    }

    public static ZonedDateTime getEndDateOfWork(Scanner scanner) {
        ZonedDateTime endDate = null;

        while (endDate == null) {
            System.out.print("Enter the end date of work (dd.MM.yyyy): ");
            String endDateStr = scanner.nextLine();

            try {
                endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeParseException e) {
                System.err.println("Error in date format for work. Use the format 'dd.MM.yyyy'. Please re-enter.");
            }
        }

        return endDate;
    }


    public static void displayWorkHours(long workHours) {
        System.out.println("Total work hours: " + workHours);
    }
}
