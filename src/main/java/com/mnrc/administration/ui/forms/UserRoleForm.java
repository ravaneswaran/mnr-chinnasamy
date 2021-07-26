package com.mnrc.administration.ui.forms;

import javax.validation.constraints.NotEmpty;

public class UserRoleForm {

    private String roleId;

    @NotEmpty(message = "User Role name should not be empty")
    private String roleName;

    private int noOfUsers;

    private String action = "/user/role/add";

    private int canAccessAdministrationApp;

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

    public int getCanAccessAdministrationApp() {
        return canAccessAdministrationApp;
    }

    public void setCanAccessAdministrationApp(int canAccessAdministrationApp) {
        this.canAccessAdministrationApp = canAccessAdministrationApp;
    }
}
