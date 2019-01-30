package com.tdd.example.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void putNewCarReturns201() {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();

            final Car car = new Car();
            car.setMake("Test Make");
            car.setModel("Test Model");
            mvc.perform(MockMvcRequestBuilders.put("/car")
                    .content(objectMapper.writeValueAsString(car))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
