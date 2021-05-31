package com.store.core.controllers;

import com.store.core.enums.UserStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRegistrationControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void registerUserTest() throws Exception{

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));
        Date newDate = new Date();

        String firstName = "";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String password = String.format("password-%s", randomNumberString);
        String confirmPassword = String.format("password-%s", randomNumberString);

        try {
            this.mockMvc.perform(
                    post("/register-user")
                            .param("firstName", firstName)
                            .param("middleInitial", middleInitial)
                            .param("lastName", lastName)
                            .param("emailId", emailId)
                            .param("uniqueId", uniqueId)
                            .param("mobileNo", mobileNo)
                            .param("password", password)
                            .param("confirmPassword", confirmPassword)
                            .accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
                    .andExpect(content().contentType(String.format("%s;charset=UTF-8",MediaType.TEXT_HTML)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
