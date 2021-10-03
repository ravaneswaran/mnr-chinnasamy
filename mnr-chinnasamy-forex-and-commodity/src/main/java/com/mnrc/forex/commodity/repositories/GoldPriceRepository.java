package com.mnrc.forex.commodity.repositories;

import com.mnrc.forex.commodity.entities.GoldPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoldPriceRepository extends CrudRepository<GoldPrice, String> {
}
