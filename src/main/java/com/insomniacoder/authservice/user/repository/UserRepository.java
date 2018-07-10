package com.insomniacoder.authservice.user.repository;

import com.insomniacoder.authservice.user.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {


}
