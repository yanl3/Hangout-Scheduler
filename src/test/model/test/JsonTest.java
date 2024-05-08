package model.test;


import model.*;
import static org.junit.jupiter.api.Assertions.assertEquals;



// checks that it is the same event
public class JsonTest {
    protected void checkEvent(String name, Event event) {
        assertEquals(name, event.getEventName());
    }
}
