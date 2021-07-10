package com.mnrc.repositories;


import com.mnrc.models.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {
}
