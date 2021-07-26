package com.mnrc.administration.services.impl;

import com.mnrc.administration.models.User;
import com.mnrc.administration.models.UserProfile;
import com.mnrc.administration.repositories.UserProfileRepository;
import com.mnrc.administration.repositories.UserRepository;
import com.mnrc.administration.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
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
