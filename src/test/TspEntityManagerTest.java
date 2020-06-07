package test;

import EntityManager.RoundtripEntityManager;
import EntityManager.TspEntityManager;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class TspEntityManagerTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void create() {
        TspEntityManager tester = new TspEntityManager();
        // check if returns when name == null
        assertEquals(-1, tester.create(null, null));
        assertNotNull(tester.create(null, "testTSP"));
    }

    @org.junit.jupiter.api.Test
    void updateTsp() {
    }
    @org.junit.jupiter.api.Test
    void createTspRoundtrip() {
        TspEntityManager tester = new TspEntityManager();
        java.sql.Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        assertNull(tester.createTspRoundtrip(null, null));
        var tspId = tester.create(null, "testTSP" + timestamp);
        assertNotNull(tspId);

        RoundtripEntityManager roundtripTester = new RoundtripEntityManager();
        var roundtripId = roundtripTester.create(timestamp, "testRoundtrip", 0.0);
        assertNotNull(roundtripId);
        assertNotNull(tester.createTspRoundtrip(tspId, roundtripId));
    }

    @Test
    void listAllTSPs() {
        TspEntityManager tester = new TspEntityManager();
        assertNotNull(tester.listAllTSPs());
    }
}