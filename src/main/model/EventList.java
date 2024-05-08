package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Represents a list of events
public class EventList implements Writable {
    private String name;
    private List<Event> eventList;



    // EFFECTS: makes an initial empty event list
    public EventList(String name) {
        this.name = name;
        eventList = new ArrayList<>();

    }

    // REQUIRES: event != null
    // MODIFIES: this
    // EFFECTS: adds event to eventList
    public void addEvent(Event event) {
        eventList.add(event);
        EventLog.getInstance().logEvent(new Event1("Event:" + event.getEventName() + " created\n"));
    }

    public void removeEvent(int place) {
        Event removedEvent = eventList.get(place);
        eventList.remove(place);
        EventLog.getInstance().logEvent(new Event1("Event:" + removedEvent.getEventName() + " removed\n"));
    }

    // EFFECTS: returns string representation of all event names in eventList
    public List<String> getEventsNames() {
        List<String> eventNames = new ArrayList<>();
        for (Event e : eventList) {
            eventNames.add(e.getEventName());
        }
        return eventNames;
    }


    // REQUIRES: eventName to be a non-zero length
    // EFFECTS: returns true if eventName is in the list of names of events in eventList,
    //          otherwise false.
    public boolean eventExists(EventList eventList, String eventName) {
        return eventList.getEventsNames().contains(eventName);
    }

    // REQUIRES: eventName to be a non-zero length
    // EFFECTS: if event name list contains eventName, then return matching event,
    //    //          otherwise return null.
    public Event getEvent(String eventName) {
        for (Event e : eventList) {
            if (Objects.equals(eventName, e.getEventName())) {
                return e;
            }
        }
        return null;
    }

    //getter
    public int getSize() {
        return eventList.size();
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public String getName() {
        return name;
    }


    // Puts events in eventList info in its own jsonObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Events", eventsToJson());
        return json;
    }

    // EFFECTS: returns things in this EventList as a JSON array
    private JSONArray eventsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Event e : eventList) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}
