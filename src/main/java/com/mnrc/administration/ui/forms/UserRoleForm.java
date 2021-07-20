package com.mnrc.administration.ui.forms;

import javax.validation.constraints.NotEmpty;

public class UserRoleForm {

    private String userRoleId;

    @NotEmpty(message = "User Role name should not be empty")
    private String userRoleName;

    private int noOfUsers;

    private String action = "/user/role/add";

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public int getNoOfUsers() {
        return noOfUsers;
    }

    public void setNoOfUsers(int noOfUsers) {
        this.noOfUsers = noOfUsers;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
