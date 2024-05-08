package model.test;

import model.Date;
import model.Event;
import model.Friend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private Event testEvent;
    private Event testEvent1;
    private Friend friend;
    private Friend friend1;
    private Date testDate;
    private Date testDate1;


    @BeforeEach
    void runBefore() {
        testEvent = new Event("help");
        testEvent1 = new Event("me");
        friend = new Friend("Munch");
        friend1 = new Friend("Sesame");
        testDate = new Date("Monday", "January", 30);
        testDate1 = new Date("Friday", "December", 28);
    }

    @Test
    void testConstructor() {
        assertEquals("help", testEvent.getEventName());
        assertTrue(testEvent.getFriends().isEmpty());
        assertTrue(testEvent.getDates().isEmpty());

        assertEquals("me", testEvent1.getEventName());
        assertTrue(testEvent1.getFriends().isEmpty());
        assertTrue(testEvent1.getDates().isEmpty());
    }

    @Test
    void testGetFriendNamesNone() {
        assertEquals(Arrays.asList(), testEvent.getFriendNames());
    }

    @Test
    void testGetFriendNames() {

        testEvent.addFriend(friend);
        assertEquals(Arrays.asList("Munch"), testEvent.getFriendNames());
    }

    @Test
    void testGetFriendNames2() {
        testEvent.addFriend(friend);
        testEvent.addFriend(friend1);
        assertEquals(Arrays.asList("Munch", "Sesame"), testEvent.getFriendNames());
    }

    @Test
    void testPersonExistsYes() {
        testEvent.getFriends().add(friend);
        assertTrue(testEvent.personExists(testEvent, "Munch"));
    }

    @Test
    void testPersonExistsYes2() {
        testEvent.getFriends().add(friend);
        testEvent.getFriends().add(friend1);
        assertTrue(testEvent.personExists(testEvent, "Sesame"));
    }

    @Test
    void testPersonExistsNo2() {
        testEvent.getFriends().add(friend);
        testEvent.getFriends().add(friend1);
        assertFalse(testEvent.personExists(testEvent, "Bro"));
    }

    @Test
    void testPersonExistsNo() {
        testEvent.getFriends().add(friend);
        assertFalse(testEvent.personExists(testEvent, "Sesame"));
    }

    @Test
    void testPersonExistsNone() {
        assertFalse(testEvent.personExists(testEvent, "Sesame"));
    }

    @Test
    void testGetFriendNo() {
        assertNull(testEvent.getFriend("Bunny"));
    }

    @Test
    void testGetFriendNo1() {
        testEvent.getFriends().add(friend);
        assertNull(testEvent.getFriend("Bunny"));
    }

    @Test
    void testGetFriendNo2() {
        testEvent.getFriends().add(friend);
        testEvent.getFriends().add(friend1);
        assertNull(testEvent.getFriend("Bunny"));
    }

    @Test
    void testGetFriendYes() {
        testEvent.getFriends().add(friend);
        assertEquals(friend, testEvent.getFriend("Munch"));
    }

    @Test
    void testGetFriendYes2() {
        testEvent.getFriends().add(friend);
        testEvent.getFriends().add(friend1);
        assertEquals(friend, testEvent.getFriend("Munch"));
    }

    @Test
    void testGetEventDatesE() {
        assertEquals(Arrays.asList(), testEvent.getEventDates());
    }

    @Test
    void testGetEventDates1() {
        testEvent.getDates().add(testDate);
        assertEquals(Arrays.asList("(Monday, January 30)"), testEvent.getEventDates());
    }

    @Test
    void testGetEventDates2() {
        testEvent.getDates().add(testDate);
        testEvent.getDates().add(testDate1);
        assertEquals(Arrays.asList("(Monday, January 30)", "(Friday, December 28)"), testEvent.getEventDates());
    }

    @Test
    void testAddFriend() {
        testEvent.addFriend(friend);
        assertEquals(1, testEvent.getFriends().size());
        assertTrue(testEvent.getFriends().contains(friend));
    }

    @Test
    void testAddFriend2() {
        testEvent.addFriend(friend);
        testEvent.addFriend(friend1);
        assertEquals(2, testEvent.getFriends().size());
        assertTrue(testEvent.getFriends().contains(friend));
        assertTrue(testEvent.getFriends().contains(friend1));
    }

    @Test
    void testAddDate() {
        testEvent.addDate(testDate);
        assertEquals(1, testEvent.getDates().size());
        assertTrue(testEvent.getDates().contains(testDate));
    }

    @Test
    void testAddDate2() {
        testEvent.addDate(testDate);
        testEvent.addDate(testDate1);
        assertEquals(2, testEvent.getDates().size());
        assertTrue(testEvent.getDates().contains(testDate));
        assertTrue(testEvent.getDates().contains(testDate1));
    }

    @Test
    void testPrintEvent1() {
        testEvent.addDate(testDate);
        testEvent.addFriend(friend);
        assertEquals("Event Name: " + testEvent.getEventName() + "\nDates: " + testEvent.getEventDates()
                + "\nFriends: " + testEvent.getFriendsList() + "\n", testEvent.printEvent());
    }

    @Test
    void testGetFriendsList() {
        testEvent.addFriend(friend);
        testEvent.addFriend(friend1);
        List<String> list = Arrays.asList("Munch: []", "Sesame: []");
        assertEquals(list, testEvent.getFriendsList());

    }

    @Test
    void testGetFriendsListNo() {
        List<String> list = new ArrayList<>();
        assertEquals(list, testEvent.getFriendsList());
    }

}
