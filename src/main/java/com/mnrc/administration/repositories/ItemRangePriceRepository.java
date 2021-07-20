package com.mnrc.administration.repositories;

import com.mnrc.administration.models.ItemRangePrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRangePriceRepository extends CrudRepository<ItemRangePrice, String> {
}
