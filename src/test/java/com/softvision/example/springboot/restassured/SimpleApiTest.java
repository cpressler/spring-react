package com.softvision.example.springboot.restassured;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class SimpleApiTest {

    @Test
    @Ignore
    public void
    lotto_resource_returns_200_with_expected_id_and_winners() {
        String jsonendpoint
                = "http://localhost:8180/api/groups/{id}";

        when().
                get(jsonendpoint, 1).
                then().
                statusCode(200).
                body("id", equalTo(1),
                "name", equalTo("Denver JUG"))
        ;

    }
}


