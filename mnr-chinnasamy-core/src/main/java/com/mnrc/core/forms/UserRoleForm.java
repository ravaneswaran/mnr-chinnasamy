package com.mnrc.core.forms;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
public class UserRoleForm {

    private String roleId;

    @NotEmpty(message = "User Role name should not be empty")
    private String roleName;

    private int noOfUsers;

    private String action = "/administration/user/role/add";

    private boolean canAccessAdministrationApp;

    public int getNoOfUsers() {
        return noOfUsers;
    }

    public void setNoOfUsers(int noOfUsers) {
        this.noOfUsers = noOfUsers;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean getCanAccessAdministrationApp() {
        return canAccessAdministrationApp;
    }

    public void setCanAccessAdministrationApp(boolean canAccessAdministrationApp) {
        this.canAccessAdministrationApp = canAccessAdministrationApp;
    }
}
