package com.example.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pet.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	@Query("SELECT acc FROM Account acc WHERE acc.username =:username")
	public Account findByUsername(String username);

}
