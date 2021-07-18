package com.mnrc.ui.forms;

import javax.validation.constraints.NotEmpty;

public class UserRoleForm {

    private String userRoleId;

    @NotEmpty(message = "{user.role.name.not.empty.error.message}")
    private String userRoleName;

    private int noOfUsers;

    private String action = "/user/role/add";

    private String operation = "ADD";

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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
