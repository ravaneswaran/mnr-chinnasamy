package com.mnrc.core.repositories;

import com.mnrc.core.entities.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, String> {

    @Query("SELECT ur FROM UserRole ur WHERE ur.name != 'ALMIGHTY'")
    public List<UserRole> findAllExcludingAlmightyRole();

}
