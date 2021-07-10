package com.mnrc.services;

import com.mnrc.models.UserProfile;

public interface UserProfileService {

    public UserProfile addProfilePicture(String userId, byte[] profilePicture);

}
