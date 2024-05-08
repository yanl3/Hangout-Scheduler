package model.test;


import model.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {
    private Date testDate;
    private Date testDate1;


    @BeforeEach
    void runBefore() {
        testDate = new Date("Monday", "January", 30);
        testDate1 = new Date("Friday", "December", 28);
    }

    @Test
    void testConstructor() {
        assertEquals("Monday", testDate.getDayName());
        assertEquals("January", testDate.getMonth());
        assertEquals(30, testDate.getDay());

        assertEquals("Friday", testDate1.getDayName());
        assertEquals("December", testDate1.getMonth());
        assertEquals(28, testDate1.getDay());
    }


    @Test
    void testGetDate() {
        assertEquals("(Monday, January 30)", testDate.getDate());
        assertEquals("(Friday, December 28)", testDate1.getDate());
    }
}
