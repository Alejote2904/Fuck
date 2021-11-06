package com.bike.repository.crud;

import com.bike.model.Bike;
import org.springframework.data.repository.CrudRepository;

public interface BikeCrudRepository extends CrudRepository<Bike,Integer> {

}
