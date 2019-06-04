package com.softvision.example.springboot;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class JugToursApplicationIT {

    private final Logger logger = LoggerFactory.getLogger(JugToursApplicationIT.class);
    @Test
    public void contextLoads() {
        logger.error("contextLoads");
    }

}
