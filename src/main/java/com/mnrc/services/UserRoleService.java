package com.mnrc.services;

import com.mnrc.ui.forms.UserRoleForm;

public interface UserRoleService {

    public UserRoleForm addUserRole(String name, String userName) throws Exception;

    public void deleteUserRole(String userRoleId);

    public void editUserRole(String userRoleId, String name, String userName);

    public void getUserRole(String userRoleId);

}
