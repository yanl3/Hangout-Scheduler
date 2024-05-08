package model.test;

import model.Category;
import model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatusTest {
    private Status testStatus;
    private Status testStatus1;

    @BeforeEach
    void runBefore() {
        testStatus = new Status("9:30", "15:30", Category.AVAILABLE);
        testStatus1 = new Status("10:30", "23:05", Category.TBD);
    }

    @Test
    void testConstructor() {
        assertEquals("9:30", testStatus.getStartTime());
        assertEquals("15:30", testStatus.getEndTime());
        assertEquals(Category.AVAILABLE, testStatus.getCategory());

        assertEquals("10:30", testStatus1.getStartTime());
        assertEquals("23:05", testStatus1.getEndTime());
        assertEquals(Category.TBD, testStatus1.getCategory());
    }

    @Test
    void testGetStatus() {
        assertEquals("({9:30-15:30} : AVAILABLE)", testStatus.getStatus());
        assertEquals("({10:30-23:05} : TBD)", testStatus1.getStatus());
    }

}


