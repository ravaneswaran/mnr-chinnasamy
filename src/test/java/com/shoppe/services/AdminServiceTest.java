package com.shoppe.services;

import com.shoppe.enums.UserStatus;
import com.shoppe.enums.UserType;
import com.shoppe.ui.forms.Admin;
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
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.adminService);
    }

    @Test
    public void testAddAdmin(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;

        Admin admin = this.adminService.addAdmin(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);

        Assert.assertNotNull(admin);
    }

    @Test
    public void testListAdmins(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        this.adminService.addAdmin(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);

        List<Admin> admins = this.adminService.listAdmins();

        Assert.assertTrue(admins.size() >= 1 );
    }

    @Test
    public void testGetAdmin(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        Admin admin = this.adminService.addAdmin(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);

        Admin response = this.adminService.getAdmin(admin.getAdminId());

        Assert.assertEquals(admin.getAdminId(), response.getAdminId());
    }

    @Test
    public void testBlockAdmin(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        Admin admin = this.adminService.addAdmin(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);

        this.adminService.blockAdmin(admin.getAdminId());
        Admin response = this.adminService.getAdmin(admin.getAdminId());

        Assert.assertEquals(admin.getAdminId(), response.getAdminId());
    }

    @Test
    public void testUnBlockAdmin(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        Admin admin = this.adminService.addAdmin(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);
        this.adminService.blockAdmin(admin.getAdminId());

        this.adminService.unblockAdmin(admin.getAdminId());
        Admin response = this.adminService.getAdmin(admin.getAdminId());

        Assert.assertEquals(admin.getAdminId(), response.getAdminId());
    }

    @Test
    public void testDeleteAdmin(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        Admin admin = this.adminService.addAdmin(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);

        this.adminService.deleteAdmin(admin.getAdminId());
        Admin response = this.adminService.getAdmin(admin.getAdminId());

        Assert.assertNull(response);
    }

}
