package com.shoppe.repositories;

import com.shoppe.models.ItemRangePrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRangePriceRepository extends CrudRepository<ItemRangePrice, String> {
}
