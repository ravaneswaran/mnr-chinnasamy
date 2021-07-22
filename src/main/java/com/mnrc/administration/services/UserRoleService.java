package com.mnrc.administration.services;

import com.mnrc.administration.ui.forms.UserRoleForm;

import java.util.List;

public interface UserRoleService {

    public UserRoleForm addUserRole(String name, String userName) throws Exception;

    public void deleteUserRole(String userRoleId) throws Exception;

    public UserRoleForm editUserRole(String userRoleId, String name, String userName) throws Exception;

    public UserRoleForm getUserRole(String userRoleId) throws Exception;

    public List<UserRoleForm> getUserRoles();

}
