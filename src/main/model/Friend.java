package model;

import java.util.ArrayList;
import java.util.List;

// Represents a friend with their name, and time periods with availability status
public class Friend {

    private String name;                 // friend name
    private final List<Status> statuses; // entered time periods and their availability type


    // REQUIRES: name has a non-zero length
    // EFFECTS: creates a friend that has their name (set to name)
    //          with no times (status) entered
    public Friend(String name) {
        this.name = name;
        statuses = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds status/time period to list of specified person's
    //          list of statuses (time periods)
    public void addStatus(Status s) {
        statuses.add(s);
    }

    //getters
    public String getName() {
        return name;
    }

    public List<Status> getStatuses() {
        return statuses;
    }


    public String getFriend() {
        return name + ": " + getStatus();
    }

    // MODIFIES: this
    // EFFECTS: creates a string representation list of statuses
    public List<String> getStatus() {
        List<String> statusList = new ArrayList<>();
        for (Status s : statuses) {
            statusList.add(s.getStatus());
        }
        return statusList;
    }

}
