package com.mnrc.core.services;


import com.mnrc.core.entities.UserProfile;

public interface UserProfileService {

    public UserProfile saveProfilePicture(String userId, byte[] profilePicture);

}
