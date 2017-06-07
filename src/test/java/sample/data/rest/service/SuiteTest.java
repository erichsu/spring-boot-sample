package sample.data.rest.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import sample.data.rest.service.CityRepositoryIntegrationTests;
import sample.data.rest.service.HotelRepositoryIntegrationTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        HotelRepositoryIntegrationTests.class,
        CityRepositoryIntegrationTests.class
})
@Ignore
public class SuiteTest {
    @BeforeClass
    public static void setUpClass() {
        System.out.println("=== Master setup ===");

    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("=== Master tearDown ===");
    }
}
