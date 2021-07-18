package com.mnrc.services;

import com.mnrc.models.UserRole;
import com.mnrc.ui.forms.UserRoleForm;

import java.util.List;

public interface UserRoleService {

    public UserRoleForm addUserRole(String name, String userName) throws Exception;

    public void deleteUserRole(String userRoleId) throws Exception;

    public UserRoleForm editUserRole(String userRoleId, String name, String userName) throws Exception;

    public UserRoleForm getUserRole(String userRoleId) throws Exception;

    public List<UserRoleForm> getUserRoles();

}
