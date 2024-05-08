package model.test;


import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            EventList el = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyEventList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyEventList.json");
        try {
            EventList el = reader.read();
            assertEquals("Events", el.getName());
            assertEquals(0, el.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEventList() {
        JsonReader reader = new JsonReader("./data/testReaderEventList.json");
        try {
            EventList el = reader.read();
            assertEquals("Events", el.getName());
            List<Event> eventList = el.getEventList();
            assertEquals(1, eventList.size());
            checkEvent("event1", eventList.get(0));
            List<Friend> friends = el.getEvent("event1").getFriends();
            assertEquals(1, friends.size());
            assertEquals("friend1", friends.get(0).getName());
            List<Status> statuses = friends.get(0).getStatuses();
            assertEquals(1, statuses.size());
            assertEquals("4040", statuses.get(0).getStartTime());
            assertEquals("4040", statuses.get(0).getEndTime());
            assertEquals(Category.NOT_AVAILABLE, statuses.get(0).getCategory());
            List<Date> dates = el.getEvent("event1").getDates();
            assertEquals(1, dates.size());
            assertEquals("july", dates.get(0).getMonth());
            assertEquals("mon",dates.get(0).getDayName());
            assertEquals(4, dates.get(0).getDay());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
