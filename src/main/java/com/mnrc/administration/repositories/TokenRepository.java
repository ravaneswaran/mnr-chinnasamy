package com.mnrc.administration.repositories;

import com.mnrc.administration.models.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {

    public Token findSignUpVerificationTokenByCreatorUUID(String creatorUUID);

}
