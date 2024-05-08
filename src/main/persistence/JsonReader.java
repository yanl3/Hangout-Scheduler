package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;


// Represents a reader that reads event from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    public EventList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEventList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses eventList from JSON object and returns it
    private EventList parseEventList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        EventList el = new EventList(name);
        addEvents(el, jsonObject);
        return el;
    }

    // MODIFIES: el
    // EFFECTS: parses events from JSON object and adds them to eventlist
    private void addEvents(EventList el, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Events");
        for (Object json : jsonArray) {
            JSONObject nextEvent = (JSONObject) json;
            addEvent(el, nextEvent);
        }
    }

    // MODIFIES: el
    // EFFECTS: parses events from JSON object and adds them to eventlist
    private void addEvent(EventList el, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Event event = new Event(name);
        JSONArray friends = jsonObject.getJSONArray("friends");
        for (Object json : friends) {
            JSONObject nextFriend = (JSONObject) json;
            addFriend(event, nextFriend);
        }
        JSONArray dates = jsonObject.getJSONArray("date");
        for (Object json : dates) {
            JSONObject nextDate = (JSONObject) json;
            addDate(event, nextDate);
        }
        el.addEvent(event);
    }

    // MODIFIES: el
    // EFFECTS: parses friend info from JSON object and adds them to eventlist
    private void addFriend(Event event, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Friend friend = new Friend(name);
        JSONArray statuses = jsonObject.getJSONArray("statuses");
        for (Object json : statuses) {
            JSONObject nextStatus = (JSONObject) json;
            addStatus(friend, nextStatus);
        }
        event.addFriend(friend);
    }

    // MODIFIES: el
    // EFFECTS: parses status info from JSON object and adds them to eventlist
    private void addStatus(Friend friend, JSONObject jsonObject) {
        String startTime = jsonObject.getString("startTime");
        String endTime = jsonObject.getString("endTime");
        Category category = Category.valueOf(jsonObject.getString("category"));
        Status status = new Status(startTime, endTime, category);
        friend.addStatus(status);
    }

    // MODIFIES: el
    // EFFECTS: parses date info from JSON object and adds them to eventlist
    private void addDate(Event event, JSONObject jsonObject) {
        String month = jsonObject.getString("month");
        String dayName = jsonObject.getString("dayName");
        int day = jsonObject.getInt("day");
        Date date = new Date(dayName, month, day);
        event.addDate(date);
    }



}
