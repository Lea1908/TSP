package test;

import EntityManager.CityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tsp.model.CityEntity;

import static org.junit.jupiter.api.Assertions.*;

class CityManagerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findExistingOrCreateNewCity() {
        CityManager tester = new CityManager();
        assertEquals(-1, tester.findExistingOrCreateNewCity(null, 12.3,3.3));
        assertNotNull(tester.findExistingOrCreateNewCity(new CityEntity("TestCity", 0.0, 0.0)));
    }
}