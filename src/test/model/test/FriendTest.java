package model.test;

import model.Category;
import model.Friend;
import model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FriendTest {
    private Friend testFriend;
    private Friend testFriend1;

    @BeforeEach
    void runBefore() {
        testFriend = new Friend("Munch");
        testFriend1 = new Friend("Sesame");
    }

    @Test
    void testConstructor() {
        assertEquals("Munch", testFriend.getName());
        assertTrue(testFriend.getStatuses().isEmpty());

        assertEquals("Sesame", testFriend1.getName());
        assertTrue(testFriend1.getStatuses().isEmpty());
    }

    @Test
    void testAddStatus() {
        Status testStatus = new Status("9:30", "15:30", Category.AVAILABLE);
        testFriend.addStatus(testStatus);
        assertTrue(testFriend.getStatuses().contains(testStatus));
        assertEquals(1, testFriend.getStatuses().size());
    }

    @Test
    void testAddStatus2() {
        Status testStatus = new Status("9:30", "15:30", Category.AVAILABLE);
        Status testStatus1 = new Status("10:30", "23:05", Category.TBD);
        testFriend.addStatus(testStatus);
        testFriend.addStatus(testStatus1);
        assertTrue(testFriend.getStatuses().contains(testStatus));
        assertTrue(testFriend.getStatuses().contains(testStatus1));
        assertEquals(2, testFriend.getStatuses().size());
    }

    @Test
    void testAddStatus3() {
        Status testStatus = new Status("9:30", "15:30", Category.AVAILABLE);
        Status testStatus1 = new Status("10:30", "23:05", Category.TBD);
        Status testStatus3 = new Status("11:30", "23:05", Category.TBD);
        testFriend.addStatus(testStatus);
        testFriend.addStatus(testStatus1);
        testFriend.addStatus(testStatus3);
        assertTrue(testFriend.getStatuses().contains(testStatus));
        assertTrue(testFriend.getStatuses().contains(testStatus1));
        assertTrue(testFriend.getStatuses().contains(testStatus3));
        assertEquals(3, testFriend.getStatuses().size());
    }

    @Test
    void testGetFriendNoS() {
        assertEquals("Munch: []", testFriend.getFriend());
    }

    @Test
    void testGetFriendS1() {
        Status testStatus = new Status("9:30", "15:30", Category.AVAILABLE);
        testFriend.addStatus(testStatus);
        assertEquals("Munch: [({9:30-15:30} : AVAILABLE)]", testFriend.getFriend());
    }

    @Test
    void testGetStatus() {
        Status testStatus = new Status("9:30", "15:30", Category.AVAILABLE);
        Status testStatus1 = new Status("10:30", "23:05", Category.TBD);
        Status testStatus3 = new Status("11:30", "23:05", Category.TBD);
        testFriend.addStatus(testStatus);
        testFriend.addStatus(testStatus1);
        testFriend.addStatus(testStatus3);
        List<String> list = Arrays.asList("({9:30-15:30} : AVAILABLE)",
                "({10:30-23:05} : TBD)", "({11:30-23:05} : TBD)");
        assertEquals(list, testFriend.getStatus());
    }

    @Test
    void testGetStatus1() {
        Status testStatus = new Status("9:30", "15:30", Category.AVAILABLE);
        testFriend.addStatus(testStatus);
        List<String> list = Arrays.asList("({9:30-15:30} : AVAILABLE)");
        assertEquals(list, testFriend.getStatus());
    }

    @Test
    void testGetStatusN0() {
        List<String> list = new ArrayList<>();
        assertEquals(list, testFriend.getStatus());
    }

}
