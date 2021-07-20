package com.mnrc.administration.services;

import com.mnrc.administration.ui.forms.UserForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.employeeService);
    }

    @Test
    public void testAddEmployee(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;

        UserForm admin = this.employeeService.addEmployee(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);

        Assert.assertNotNull(admin);
    }

    @Test
    public void testListEmployees(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        this.employeeService.addEmployee(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);

        List<UserForm> admins = this.employeeService.listEmployees();

        Assert.assertTrue(admins.size() >= 1 );
    }

    @Test
    public void testGetEmployee(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        UserForm admin = this.employeeService.addEmployee(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);

        UserForm response = this.employeeService.getEmployee(admin.getEmployeeId());

        Assert.assertEquals(admin.getEmployeeId(), response.getEmployeeId());
    }

    @Test
    public void testBlockEmployee(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        UserForm admin = this.employeeService.addEmployee(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);

        this.employeeService.lockEmployee(admin.getEmployeeId());
        UserForm response = this.employeeService.getEmployee(admin.getEmployeeId());

        Assert.assertEquals(admin.getEmployeeId(), response.getEmployeeId());
    }

    @Test
    public void testUnBlockEmployee(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        UserForm admin = this.employeeService.addEmployee(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);
        this.employeeService.lockEmployee(admin.getEmployeeId());

        this.employeeService.unLockEmployee(admin.getEmployeeId());
        UserForm response = this.employeeService.getEmployee(admin.getEmployeeId());

        Assert.assertEquals(admin.getEmployeeId(), response.getEmployeeId());
    }

    @Test
    public void testDeleteEmployee(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        UserForm admin = this.employeeService.addEmployee(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);

        this.employeeService.deleteEmployee(admin.getEmployeeId());
        UserForm response = this.employeeService.getEmployee(admin.getEmployeeId());

        Assert.assertNull(response);
    }

}
