package com.webshoppe.controllers;

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
    public void test_register_user_when_all_the_request_parameters_are_valid() throws Exception{

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail%s@gmail.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String password = String.format("password-%s", randomNumberString);
        String confirmPassword = String.format("password-%s", randomNumberString);

        this.mockMvc.perform(
                post("/signup-user")
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

    }

    @Test
    public void test_register_user_when_the_firstname_is_empty() throws Exception{

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail%s@gmail.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String password = String.format("password-%s", randomNumberString);
        String confirmPassword = String.format("password-%s", randomNumberString);

        this.mockMvc.perform(
            post("/signup-user")
                    .param("firstName", firstName)
                    .param("middleInitial", middleInitial)
                    .param("lastName", lastName)
                    .param("emailId", emailId)
                    .param("uniqueId", uniqueId)
                    .param("mobileNo", mobileNo)
                    .param("password", password)
                    .param("confirmPassword", confirmPassword)
                    .accept(MediaType.TEXT_HTML)).andExpect(status().is4xxClientError());
    }

    @Test
    public void test_register_user_when_the_email_id_is_empty() throws Exception{

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = "";
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String password = String.format("password-%s", randomNumberString);
        String confirmPassword = String.format("password-%s", randomNumberString);

        this.mockMvc.perform(
                post("/signup-user")
                        .param("firstName", firstName)
                        .param("middleInitial", middleInitial)
                        .param("lastName", lastName)
                        .param("emailId", emailId)
                        .param("uniqueId", uniqueId)
                        .param("mobileNo", mobileNo)
                        .param("password", password)
                        .param("confirmPassword", confirmPassword)
                        .accept(MediaType.TEXT_HTML)).andExpect(status().is4xxClientError());
    }

    @Test
    public void test_register_user_when_the_mobile_number_is_empty() throws Exception{

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail%s@gmail.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = "";
        String password = String.format("password-%s", randomNumberString);
        String confirmPassword = String.format("password-%s", randomNumberString);

        this.mockMvc.perform(
                post("/signup-user")
                        .param("firstName", firstName)
                        .param("middleInitial", middleInitial)
                        .param("lastName", lastName)
                        .param("emailId", emailId)
                        .param("uniqueId", uniqueId)
                        .param("mobileNo", mobileNo)
                        .param("password", password)
                        .param("confirmPassword", confirmPassword)
                        .accept(MediaType.TEXT_HTML)).andExpect(status().is4xxClientError());
    }

    @Test
    public void test_register_user_when_the_password_is_empty() throws Exception{

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail%s@gmail.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String password = "";
        String confirmPassword = String.format("password-%s", randomNumberString);

        this.mockMvc.perform(
                post("/signup-user")
                        .param("firstName", firstName)
                        .param("middleInitial", middleInitial)
                        .param("lastName", lastName)
                        .param("emailId", emailId)
                        .param("uniqueId", uniqueId)
                        .param("mobileNo", mobileNo)
                        .param("password", password)
                        .param("confirmPassword", confirmPassword)
                        .accept(MediaType.TEXT_HTML)).andExpect(status().is4xxClientError());
    }

    @Test
    public void test_register_user_when_the_confirm_password_is_empty() throws Exception{

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail%s@gmail.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String password = String.format("password-%s", randomNumberString);
        String confirmPassword = "";

        this.mockMvc.perform(
                post("/signup-user")
                        .param("firstName", firstName)
                        .param("middleInitial", middleInitial)
                        .param("lastName", lastName)
                        .param("emailId", emailId)
                        .param("uniqueId", uniqueId)
                        .param("mobileNo", mobileNo)
                        .param("password", password)
                        .param("confirmPassword", confirmPassword)
                        .accept(MediaType.TEXT_HTML)).andExpect(status().is4xxClientError());


    }
}
