package com.mnrc.core.services;

import com.mnrc.core.entities.UserProfile;
import org.springframework.stereotype.Service;

@Service
public interface UserProfileService {

    public UserProfile saveProfilePicture(String userId, byte[] profilePicture);

}
