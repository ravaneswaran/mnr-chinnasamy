package com.shoppe.services;

import com.shoppe.ui.forms.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeService {

    public Employee addEmployee(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo);

    public List<Employee> listEmployees();

    public Employee getEmployee(String uuid);

    public void blockEmployee(String uuid);

    public void unblockEmployee(String uuid);

    public void deleteEmployee(String uuid);

}
