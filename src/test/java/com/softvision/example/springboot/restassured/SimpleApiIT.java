package com.softvision.example.springboot.restassured;

import com.softvision.example.springboot.JugToursApplication;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SimpleApiIT {

    private final Logger logger = LoggerFactory.getLogger(SimpleApiIT.class);
    private String testURL = System.getProperty("testURL") == null ? "http://localhost:8180" : System.getProperty("testURL");



    /**
     * make a request for the list of groups and it should equal to 4 which is our default count
     * if you want to test this outside of maven you need to run an external server
     */
    @Test
    public void
    getListReturnsGroups() {
        String jsonendpoint = testURL + "/api/groups/";

        logger.debug("SimpleAPIIT:groups");
        when().
                get(jsonendpoint).
                then().
                assertThat().
                body("size()", is(4));
    }


    @Test
    public void
    resource_returns_200_with_expected_id_and_name() {
        String jsonendpoint = testURL + "/api/groups/{id}";
//        String jsonendpoint
//                = "http://localhost:8180/api/groups/{id}";

        logger.debug("SimpleAPIIT:id:1");
        when().
                get(jsonendpoint, 1).
                then().
                statusCode(200).
                body("id", equalTo(1),
                "name", equalTo("Denver JUG"))
        ;
    }

    @Test
    public void contextLoads() {
        logger.debug("SimpleAPIIT:contextLoads:" + testURL);
    }

}


