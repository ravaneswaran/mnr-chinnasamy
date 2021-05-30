package com.store.core.repositories;

import com.store.core.models.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, String> {
}
