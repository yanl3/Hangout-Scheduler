package model;


// Represents a friend's entered time period and the availability status of that slot
public class Status {

    private String startTime; // start of the time slot
    private String endTime;   // end of the time slot
    private Category category;     // the type of time slot (3 types: available, not available, TBD)

    public Status(String startTime, String endTime, Category category) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;

    }

    // EFFECTS: creates new string that combines startTime, endTime, type into
    // the format (startTime-endTime) : type)
    public String getStatus() {
        return "(" + "{" + startTime + "-" + endTime + "}" + " : " + category + ")";
    }


    // getters
    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Category getCategory() {
        return category;
    }

}
