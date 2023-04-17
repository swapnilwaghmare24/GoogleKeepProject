package com.bridgelabz.googlekeep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.googlekeep.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

	@Query(value="select * from user where email=:email", nativeQuery=true)
	User findByEmail(String email);

}
