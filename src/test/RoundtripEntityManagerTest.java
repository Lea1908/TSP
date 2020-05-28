package test;

import EntityManager.RoundtripEntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class RoundtripEntityManagerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        RoundtripEntityManager tester = new RoundtripEntityManager();
        assertEquals(-1, tester.create(null, null, 1.2));
        java.sql.Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        assertNotNull(tester.create(timestamp, "testRoundtrip", 0.0));
    }
}