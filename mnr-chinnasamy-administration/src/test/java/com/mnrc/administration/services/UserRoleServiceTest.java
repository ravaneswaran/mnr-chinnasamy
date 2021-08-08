package com.mnrc.administration.services;

import com.mnrc.administration.models.UserRole;
import com.mnrc.administration.repositories.UserRoleRepository;
import com.mnrc.administration.ui.forms.UserRoleForm;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleServiceTest {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.userRoleService);
    }

    @Test
    public void testAddUserRole() throws Exception {
        String userRoleName = RandomString.make();
        String userName = "Ravaneswaran Chinnasamy";
        UserRoleForm userRoleForm = this.userRoleService.addUserRole(userRoleName, userName);

        UserRole response = this.userRoleRepository.findById(userRoleForm.getRoleId()).get();

        Assert.assertEquals(userRoleName.toUpperCase(), response.getName());
    }

    @Test
    public void testAddUserRole_When_UserRole_Name_Is_Null() {
        String userRoleName = null;
        String userName = "Ravaneswaran Chinnasamy";
        try {
            this.userRoleService.addUserRole(userRoleName, userName);
        } catch (Exception e) {
            Assert.assertEquals("User Role name cannot be null...",  e.getMessage());
        }
    }

    @Test
    public void testAddUserRole_When_UserRole_Name_Is_Empty() {
        String userRoleName = "";
        String userName = "Ravaneswaran Chinnasamy";
        try {
            this.userRoleService.addUserRole(userRoleName, userName);
        } catch (Exception e) {
            Assert.assertEquals("User Role name cannot be a empty string...",  e.getMessage());
        }
    }

    @Test
    public void testAddUserRole_When_UserRole_Name_Contains_Space() {
        String userRoleName = RandomString.make() + " " + RandomString.make();
        String userName = "Ravaneswaran Chinnasamy";
        try {
            this.userRoleService.addUserRole(userRoleName, userName);
        } catch (Exception e) {
            Assert.assertEquals("User Role name should not have any white space...",  e.getMessage());
        }
    }

    @Test
    public void testAddUserRole_When_User_Name_Is_Null() {
        String userRoleName = RandomString.make();
        String userName = null;
        try {
            this.userRoleService.addUserRole(userRoleName, userName);
        } catch (Exception e) {
            Assert.assertEquals("User name cannot be null...",  e.getMessage());
        }
    }

    @Test
    public void testAddUserRole_When_User_Name_Is_Empty() {
        String userRoleName = RandomString.make();
        String userName = "";
        try {
            this.userRoleService.addUserRole(userRoleName, userName);
        } catch (Exception e) {
            Assert.assertEquals("User name cannot be a empty string...",  e.getMessage());
        }
    }

    @Test
    public void testDeleteUserRole() throws Exception {
        String userRoleName = RandomString.make();
        String userName = "Ravaneswaran Chinnasamy";
        UserRoleForm userRoleForm = this.userRoleService.addUserRole(userRoleName, userName);

        this.userRoleService.deleteUserRole(userRoleForm.getRoleId());
        Optional<UserRole> response = this.userRoleRepository.findById(userRoleForm.getRoleId());

        Assert.assertFalse(response.isPresent());
    }

    @Test
    public void testDeleteUserRole_When_UserRole_Id_Is_Null() {
        try {
            this.userRoleService.deleteUserRole(null);
        } catch (Exception e) {
            Assert.assertEquals("User role id cannot be null...",  e.getMessage());
        }
    }

    @Test
    public void testDeleteUserRole_When_UserRole_Id_Is_Empty() {
        try {
            this.userRoleService.deleteUserRole("");
        } catch (Exception e) {
            Assert.assertEquals("User role id cannot be a empty string...",  e.getMessage());
        }
    }


    @Test
    public void testGetUserRole() throws Exception {
        String userRoleName = RandomString.make();
        String userName = "Ravaneswaran Chinnasamy";
        UserRoleForm userRoleForm = this.userRoleService.addUserRole(userRoleName, userName);

        UserRoleForm response = this.userRoleService.getUserRole(userRoleForm.getRoleId());

        Assert.assertNotNull(response);
    }

    @Test
    public void testGetUserRole_When_UserRole_Id_Is_Null() {
        try {
            this.userRoleService.getUserRole(null);
        } catch (Exception e) {
            Assert.assertEquals("User role id cannot be null...",  e.getMessage());
        }
    }

    @Test
    public void testGetUserRole_When_UserRole_Id_Is_Empty() {
        try {
            this.userRoleService.getUserRole("");
        } catch (Exception e) {
            Assert.assertEquals("User role id cannot be a empty string...",  e.getMessage());
        }
    }

    @Test
    public void testToggleCanAccessAdministrationApp_Active() throws Exception {
        String userRoleName = RandomString.make();
        String userName = "Ravaneswaran Chinnasamy";
        UserRoleForm userRoleForm = this.userRoleService.addUserRole(userRoleName, userName);

        String respose = this.userRoleService.toggleCanAccessAdministrationApp(userRoleForm.getRoleId(), true);

        Assert.assertTrue("1".equals(respose));
    }

    @Test
    public void testToggleCanAccessAdministrationApp_InActive() throws Exception {
        String userRoleName = RandomString.make();
        String userName = "Ravaneswaran Chinnasamy";
        UserRoleForm userRoleForm = this.userRoleService.addUserRole(userRoleName, userName);

        String respose = this.userRoleService.toggleCanAccessAdministrationApp(userRoleForm.getRoleId(), false);

        Assert.assertTrue("0".equals(respose));
    }

    @Test
    public void testToggleCanAccessAdministrationApp_When_User_Role_Id_Is_Null(){
        try {
            this.userRoleService.toggleCanAccessAdministrationApp(null, false);
        } catch (Exception exception) {
            Assert.assertTrue("User role id cannot be null...".equals(exception.getMessage()));
        }
    }

    @Test
    public void testToggleCanAccessAdministrationApp_When_User_Role_Id_Is_Empty(){
        try {
           this.userRoleService.toggleCanAccessAdministrationApp("", false);
        } catch (Exception exception) {
            Assert.assertTrue("User role id cannot be a empty string...".equals(exception.getMessage()));
        }
    }

    @Test
    public void testToggleCanAccessAdministrationApp_When_User_Role_Id_Is_Not_In_The_System() {
        String roleId = "989876";
        try {
            this.userRoleService.toggleCanAccessAdministrationApp(roleId, false);
        } catch (Exception exception) {
            Assert.assertTrue(String.format("User role is not found for the id (%s)", roleId).equals(exception.getMessage()));
        }
    }
}
