package com.mnrc.administration.services;

import com.mnrc.administration.models.UserProfile;

public interface UserProfileService {

    public UserProfile saveProfilePicture(String userId, byte[] profilePicture);

}
