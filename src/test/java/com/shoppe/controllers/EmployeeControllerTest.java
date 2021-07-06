package com.shoppe.controllers;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void test_home_page_of_employee() throws Exception{

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        this.mockMvc.perform(
                get("/employee/home")
                        .accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
                .andExpect(content().contentType(String.format("%s;charset=UTF-8",MediaType.TEXT_HTML)));

    }

    @Test
    public void test_creating_an_employee() throws Exception{

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail%s@gmail.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;

        this.mockMvc.perform(
                post("/employee/create")
                        .param("firstName", firstName)
                        .param("middleInitial", middleInitial)
                        .param("lastName", lastName)
                        .param("emailId", emailId)
                        .param("uniqueId", uniqueId)
                        .param("mobileNo", mobileNo)
                        .accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
                .andExpect(content().contentType(String.format("%s;charset=UTF-8",MediaType.TEXT_HTML)));

    }

}
