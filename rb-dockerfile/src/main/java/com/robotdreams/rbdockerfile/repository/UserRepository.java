package com.robotdreams.rbdockerfile.repository;


import com.robotdreams.rbdockerfile.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {
}
