package com.mnrc.core.services;

import com.mnrc.core.forms.UserRoleForm;

import java.util.List;

public interface UserRoleService {

    public UserRoleForm addUserRole(String name, String userName) throws Exception;

    public UserRoleForm addUserRole(String name, boolean canAccessAdministrationApp, String userName) throws Exception;

    public void deleteUserRole(String userRoleId) throws Exception;

    public UserRoleForm editUserRole(String userRoleId, String name, String userName) throws Exception;

    public UserRoleForm editUserRole(String userRoleId, String name, boolean canAccessAdministrationApp, String userName) throws Exception;

    public UserRoleForm getUserRole(String userRoleId) throws Exception;

    public List<UserRoleForm> getUserRoles();

    public String toggleCanAccessAdministrationApp(String userRoleId, boolean canAccessAdministrationApp) throws Exception;

}
