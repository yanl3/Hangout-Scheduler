package model.test;

import model.Event;
import model.EventList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class EventListTest {
    private EventList testEventList;
    private Event testEvent;
    private Event testEvent1;

    @BeforeEach
    void runBefore() {
        testEventList = new EventList("ok");
        testEvent = new Event("help");
        testEvent1 = new Event("me");
    }

    @Test
    void testConstructor() {
        assertEquals(Arrays.asList(), testEventList.getEventList());
    }

    @Test
    void testAddEvent() {
        testEventList.addEvent(testEvent);
        assertEquals(1, testEventList.getSize());
        assertTrue(testEventList.getEventList().contains(testEvent));
    }

    @Test
    void testAddEvent2() {
        testEventList.addEvent(testEvent);
        testEventList.addEvent(testEvent1);
        assertEquals(2, testEventList.getSize());
        assertTrue(testEventList.getEventList().contains(testEvent));
        assertTrue(testEventList.getEventList().contains(testEvent1));
    }

    @Test
    void testGetEventNames0() {
        assertEquals(Arrays.asList(), testEventList.getEventsNames());
    }

    @Test
    void testGetEventNames() {
        testEventList.addEvent(testEvent);
        assertEquals(Arrays.asList("help"), testEventList.getEventsNames());
    }

    @Test
    void testGetEventNames2() {
        testEventList.addEvent(testEvent);
        testEventList.addEvent(testEvent1);
        assertEquals(Arrays.asList("help", "me"), testEventList.getEventsNames());
    }

    @Test
    void testEventExistsYes() {
        testEventList.addEvent(testEvent);
        assertTrue(testEventList.eventExists(testEventList, testEvent.getEventName()));
    }

    @Test
    void testEventExistsYes2() {
        testEventList.addEvent(testEvent);
        testEventList.addEvent(testEvent1);
        assertTrue(testEventList.eventExists(testEventList, testEvent.getEventName()));
    }

    @Test
    void testEventExistsNo() {
        assertFalse(testEventList.eventExists(testEventList, testEvent.getEventName()));
    }

    @Test
    void testEventExistsNo1() {
        testEventList.addEvent(testEvent1);
        assertFalse(testEventList.eventExists(testEventList, testEvent.getEventName()));
    }

    @Test
    void testEventExistsNo2() {
        testEventList.addEvent(testEvent1);
        testEventList.addEvent(testEvent);
        assertFalse(testEventList.eventExists(testEventList, "bro"));
    }

    @Test
    void testGetEvent0() {
        assertNull(testEventList.getEvent("Munch"));
    }

    @Test
    void testGetEvent1() {
        testEventList.addEvent(testEvent1);
        assertNull(testEventList.getEvent("bro"));
    }

    @Test
    void testGetEvent2() {
        testEventList.addEvent(testEvent1);
        testEventList.addEvent(testEvent);
        assertNull(testEventList.getEvent("boo"));
    }

    @Test
    void testGetEvent3() {
        testEventList.addEvent(testEvent1);
        testEventList.addEvent(testEvent);
        assertEquals(testEvent, testEventList.getEvent("help"));
    }

    @Test
    void testGetEvent4() {
        testEventList.addEvent(testEvent);
        assertEquals(testEvent, testEventList.getEvent("help"));
    }

}
