package com.mnrc.services;

import com.mnrc.ui.forms.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeService {

    public Employee addEmployee(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo);

    public List<Employee> listEmployees();

    public Employee getEmployee(String uuid);

    public void lockEmployee(String uuid);

    public void unLockEmployee(String uuid);

    public void deleteEmployee(String uuid);

}
