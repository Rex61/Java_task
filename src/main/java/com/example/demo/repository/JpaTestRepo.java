package com.example.demo.repository;

import com.example.demo.domain.Test;
import org.springframework.data.repository.CrudRepository;

public interface JpaTestRepo extends CrudRepository<Test, Integer>{

}
