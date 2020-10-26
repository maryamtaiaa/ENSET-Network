package org.sid.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.sid.entities.Login;



public interface LoginRepository extends JpaRepository<Login, Long>{
	public Login findByUsername(String username);

}
