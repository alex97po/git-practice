package com.pogorelov.thymeleaf.repository;

import com.pogorelov.thymeleaf.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
