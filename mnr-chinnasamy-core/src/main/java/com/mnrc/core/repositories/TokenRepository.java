package com.mnrc.core.repositories;

import com.mnrc.core.entities.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {

    public Token findSignUpVerificationTokenByCreatorUUID(String creatorUUID);

}
