public class NaiveCalendar implements MyCalendar {

    private static final int[] DAYS_OF_MONTH = {0, 31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31};
    private static final String[] WEEK_DAY = {"SUN", "MON", "TUE", "WED",
            "THU", "FRI", "SAT"};
    private static final int FIRST_DAY_OF_YEAR_ONE = 1;

    private int year;
    private int month;
    private int dayNumOfMonth;
    private boolean leap;
    private int leapCount;
    private int firstDayOYear;
    private int firstDayOfMonth;

    public NaiveCalendar(int year, int month) { // constructor
        if (year <= 0) {
            throw new RuntimeException("Invalid year");
        } else if (month <= 0 || month > 12) {
            throw new RuntimeException("Invalid month");
        }
        this.year = year;
        this.month = month;

        leap = calculateLeap();
        dayNumOfMonth = calculateDayNumOfMonth();

        leapCount = calculateLeapCount();
        firstDayOYear = calculateFirstDayOfYear();
        firstDayOfMonth = calculateFirstDayOfMonth();
    }


    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public int getDayNumOfMonth() {
        return dayNumOfMonth;
    }

    @Override
    public boolean isLeap() {
        return leap;
    }

    private boolean calculateLeap() {
        if ((this.year % 4 == 0 && this.year % 100 != 0) || (this.year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    private int calculateLeapCount() {

        // todo
        return this.year / 4;
    }

    private int calculateDayNumOfMonth() {
        int dayNum = DAYS_OF_MONTH[month];

        if (month == 2 && leap) {
            dayNum++;
        }
        return dayNum;
    }

    private int calculateFirstDayOfYear() {

        // todo
        return ZellerCon(1);
    }

    private int calculateFirstDayOfMonth() {

        return ZellerCon(month);
    }

    private int ZellerCon(int m) {
        int y = year % 100;
        int c = year / 100;
        int d = 1;
        if (m == 1 || m == 2) {
            m += 12;
            y = (year - 1) % 100;
            c = (year - 1) / 100;
        }

        int day = (y + y / 4 + c / 4 - 2 * c + 26 * (m + 1) / 10 + d - 1) % 7;
        return day;
    }

    @Override
    public void printCalendar() {

        for (String day : WEEK_DAY) {
            System.out.print(formatEntry(day));
        }
        System.out.println();


//		  todo: use function formatEntry and System.out.print to print the calendar
        for (int i = 0; i < 42; i++) {
            if (i < firstDayOfMonth) {
                System.out.print(formatEntry(""));
            } else if (i < (dayNumOfMonth + firstDayOfMonth)) {
                System.out.print(formatEntry(String.valueOf(i - firstDayOfMonth + 1)));
            }

            if (i % 7 == 6) {
                System.out.println();
            }

        }
        // System.out.println();
    }

    private static String formatEntry(String entry) {
        return String.format("%1$4s", entry);
    }

}