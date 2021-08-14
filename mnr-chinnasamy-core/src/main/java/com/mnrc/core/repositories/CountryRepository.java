package com.mnrc.core.repositories;

import com.mnrc.core.entities.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {
}
