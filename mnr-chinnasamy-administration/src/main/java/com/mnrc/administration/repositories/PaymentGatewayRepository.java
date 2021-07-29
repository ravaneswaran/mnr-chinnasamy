package com.mnrc.administration.repositories;

import com.mnrc.administration.models.PaymentGateway;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentGatewayRepository extends CrudRepository<PaymentGateway, String> {
}
