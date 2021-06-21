package com.nextline.repositories;

import com.nextline.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsersRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

}