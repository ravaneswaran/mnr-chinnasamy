package com.mnrc.administration.repositories;

import com.mnrc.administration.models.PaymentGateway;
import org.springframework.data.repository.CrudRepository;

public interface PaymentGatewayRepository extends CrudRepository<PaymentGateway, String> {
}
