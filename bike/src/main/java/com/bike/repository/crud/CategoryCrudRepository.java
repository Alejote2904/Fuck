package com.bike.repository.crud;

import com.bike.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryCrudRepository extends CrudRepository<Category,Integer> {

}
