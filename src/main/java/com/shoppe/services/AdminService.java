package com.shoppe.services;

import com.shoppe.ui.forms.Admin;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminService {

    public Admin addAdmin(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo);

    public List<Admin> listAdmins();

    public Admin getAdmin(String uuid);

    public void blockAdmin(String uuid);

    public void unblockAdmin(String uuid);

    public void deleteAdmin(String uuid);

}
