package com.mnrc.repositories;

import com.mnrc.models.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, String> {

    @Query("SELECT up FROM UserProfile up WHERE up.user.UUID=?1")
    public UserProfile findByUserUUID(String userUUID);

}
