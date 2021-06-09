package com.shoppe.repositories;

import com.shoppe.models.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {

    public Token findSignUpVerificationTokenByCreatorUUID(String creatorUUID);

}
