package com.mnrc.repositories;

import com.mnrc.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.type='ADMIN'")
    public List<User> findAllAdminUsers();

    @Query("SELECT u FROM User u WHERE u.type=?1")
    public List<User> findAllUsersByType(String type);

    @Query("SELECT u FROM User u WHERE u.status=?1")
    public List<User> findAllUsersByStatus(String status);

    @Query("SELECT u FROM User u WHERE u.emailId=?1 AND u.password=?2")
    public User findByEmailIdAndPassword(String emailId, String password);

    @Query("SELECT u FROM User u WHERE u.emailId=?1")
    public User findByEmailId(String emailId);

}
