package model;

// Represents a date that has the Day of the Week, Month, and date number.
public class Date {

    private int day;          // the number of the day of the date
    private String month;     // month of the date
    private String dayName;   // day of the week of the date


    // REQUIRES: dayName to have a non-zero length and an actual day of the week (Mon-Fri)
    //           and month to have a non-zero length and an actual month of the year (Jan-Dec)
    //           and day to have a integer between 1 and 28/30/31 (corresponding last day of month)
    // EFFECTS: Day of the Week is set to dayName, Month is set to month,
    //          day number is set to day.
    public Date(String dayName, String month, int day) {
        this.dayName = dayName;
        this.month = month;
        this.day = day;
    }

    // EFFECTS: creates new string that combines dayName, Month, day into
    //          the format (dayName, month day)
    public String getDate() {
        return "(" + dayName + ", " + month + " " + day + ")";
    }

    //getters
    public String getDayName() {
        return dayName;
    }

    public String getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

}
