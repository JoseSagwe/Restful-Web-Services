package com.joseph.rest.webservices.restfulwebservices.jpa;

import com.joseph.rest.webservices.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
