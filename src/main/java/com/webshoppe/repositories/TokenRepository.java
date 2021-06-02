package com.webshoppe.repositories;

import com.webshoppe.models.Token;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {

    public Token findSignUpVerificationTokenByCreatorUUID(String creatorUUID);

}
