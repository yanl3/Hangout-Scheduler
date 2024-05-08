package model.test;


import model.Event;
import model.EventList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            EventList el = new EventList("Events");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyEventList() {
        try {
            EventList el = new EventList("Events");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyEventList.json");
            writer.open();
            writer.write(el);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyEventList.json");
            el = reader.read();
            assertEquals("Events", el.getName());
            assertEquals(0, el.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEventList() {
        try {
            EventList el = new EventList("Events");
            el.addEvent(new Event("Birthday"));
            el.addEvent(new Event("Party"));
            JsonWriter writer = new JsonWriter("./data/testWriterEventList.json");
            writer.open();
            writer.write(el);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEventList.json");
            el = reader.read();
            assertEquals("Events", el.getName());
            List<Event> eventList = el.getEventList();
            assertEquals(2, eventList.size());
            checkEvent("Birthday", eventList.get(0));
            checkEvent("Party", eventList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
