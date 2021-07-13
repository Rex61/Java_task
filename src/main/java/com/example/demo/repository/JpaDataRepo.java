package com.example.demo.repository;

import com.example.demo.domain.Test;
import org.springframework.data.repository.CrudRepository;

public interface JpaDataRepo extends CrudRepository<Test, Integer>{

}
