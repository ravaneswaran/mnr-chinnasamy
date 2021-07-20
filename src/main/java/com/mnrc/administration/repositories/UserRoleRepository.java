package com.mnrc.administration.repositories;

import com.mnrc.administration.models.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, String> {
}
