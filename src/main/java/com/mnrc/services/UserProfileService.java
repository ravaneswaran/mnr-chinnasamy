package com.mnrc.services;

import com.mnrc.models.UserProfile;

public interface UserProfileService {

    public UserProfile saveProfilePicture(String userId, byte[] profilePicture);

}
