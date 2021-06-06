package com.webshoppe.repositories;

import com.webshoppe.models.ItemRangePrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRangePriceRepository extends CrudRepository<ItemRangePrice, String> {
}
