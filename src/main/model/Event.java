package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import org.json.JSONObject;
import persistence.Writable;

// Represents an event that has a name, list of friends invited/attending,
// and the choice of potential event dates
public class Event implements Writable {

    private String eventName;  // the event name
    private List<Friend> friends;  // friends that are coming and their availability
    private List<Date> dates; // the date(s) of the event


    // REQUIRES: eventName to have a non-zero length
    // EFFECTS: name of event is set to eventName and the event initially has no friends,
    //          no potential dates.
    public Event(String eventName) {
        this.eventName = eventName;
        friends = new ArrayList<>();
        dates = new ArrayList<>();
    }

    // EFFECTS: returns a string representation list of friend names only
    public List<String> getFriendNames() {
        List<String> friendNames = new ArrayList<>();
        for (Friend f : friends) {
            friendNames.add(f.getName());
        }
        return friendNames;
    }

    // REQUIRES: event != null and friendName is a non-zero length
    // EFFECTS: returns true if the event's list of friends contains friendName,
    //          otherwise false
    public boolean personExists(Event event, String friendName) {
        return event.getFriendNames().contains(friendName);
    }

    // REQUIRES: friendName is a non-zero length
    // EFFECTS: if event friend list contains friendName, then return matching friend,
    //          otherwise return null.
    public Friend getFriend(String friendName) {
        for (Friend f : friends) {
            if (Objects.equals(friendName, f.getName())) {
                return f;
            }
        }
        return null;
    }

    // EFFECTS: returns a formatted string representation of all existing
    //          dates in event date list
    public List<String> getEventDates() {
        List<String> eventDates = new ArrayList<>();
        for (Date d : dates) {
            eventDates.add(d.getDate());
        }
        return eventDates;
    }

    // REQUIRES: friend != null
    // MODIFIES: this
    // EFFECTS: adds friend to list of friends of event and add action to eventLog
    public void addFriend(Friend friend) {
        friends.add(friend);
        EventLog.getInstance().logEvent(new Event1("Friend:" + friend.getName() + " added to Event:" + eventName
                + "\n"));
    }

    // REQUIRES: date != null
    // MODIFIES: this
    // EFFECTS: adds date to list of dates of event and add action to eventLog
    public void addDate(Date date) {
        dates.add(date);
        EventLog.getInstance().logEvent(new Event1("Date:" + date.getDate() + " added to Event:" + eventName + "\n"));
    }

    // EFFECT: returns string representation of the event
    public String printEvent() {
        return "Event Name: " + eventName + "\nDates: " + getEventDates() + "\nFriends: " + getFriendsList() + "\n";

    }

    // MODIFIES: this
    // EFFECTS: returns list of String
    public List<String> getFriendsList() {
        List<String> friendsList = new ArrayList<>();
        for (Friend f : friends) {
            friendsList.add(f.getFriend());
        }
        return friendsList;
    }


    // getters
    public String getEventName() {
        return eventName;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public List<Date> getDates() {
        return dates;
    }


    // Puts event info in its own jsonObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", eventName);
        json.put("date", dates);
        json.put("friends", friends);
        return json;
    }
}
