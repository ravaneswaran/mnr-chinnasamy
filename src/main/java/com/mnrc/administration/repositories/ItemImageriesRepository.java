package com.mnrc.administration.repositories;

import com.mnrc.administration.models.ItemImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemImageriesRepository extends CrudRepository<ItemImage, String> {
}
