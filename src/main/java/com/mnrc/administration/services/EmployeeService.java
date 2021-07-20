package com.mnrc.administration.services;

import com.mnrc.administration.ui.forms.UserForm;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeService {

    public UserForm addEmployee(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo);

    public List<UserForm> listEmployees();

    public UserForm getEmployee(String uuid);

    public void lockEmployee(String uuid);

    public void unLockEmployee(String uuid);

    public void deleteEmployee(String uuid);

}
