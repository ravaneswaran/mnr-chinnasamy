package com.mnrc.core.repositories;

import com.mnrc.core.entities.PaymentGateway;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentGatewayRepository extends CrudRepository<PaymentGateway, String> {
}
