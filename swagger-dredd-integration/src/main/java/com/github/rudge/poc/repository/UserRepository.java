package com.github.rudge.poc.repository;

import com.github.rudge.poc.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{

    User findById(long id);
}
