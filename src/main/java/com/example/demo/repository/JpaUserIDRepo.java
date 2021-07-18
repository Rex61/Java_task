package com.example.demo.repository;

import com.example.demo.domain.UserID;
import org.springframework.data.repository.CrudRepository;

public interface JpaUserIDRepo extends CrudRepository<UserID, Integer> {
}
