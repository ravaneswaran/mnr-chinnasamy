package com.mnrc.core.services.impl;

import com.mnrc.core.entities.User;
import com.mnrc.core.entities.UserProfile;
import com.mnrc.core.repositories.UserProfileRepository;
import com.mnrc.core.repositories.UserRepository;
import com.mnrc.core.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserProfile saveProfilePicture(String userId, byte[] profilePicture) {

        String uuid = UUID.randomUUID().toString();
        User user = this.userRepository.findById(userId).get();

        UserProfile userProfile = this.userProfileRepository.findByUserUUID(userId);

        if(null == userProfile){
            Date now = new Date();
            userProfile = new UserProfile();
            userProfile.setUUID(uuid);
            userProfile.setProfilePicture(profilePicture);
            userProfile.setCreatedDate(now);
            userProfile.setModifiedDate(now);
            userProfile.setUser(user);
        } else {
            Date now = new Date();
            userProfile.setProfilePicture(profilePicture);
            userProfile.setModifiedDate(now);
        }

        return this.userProfileRepository.save(userProfile);
    }
}
