package com.rest_webservices.RestFul_web_services.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest_webservices.RestFul_web_services.user.user;

public interface UserRepository extends JpaRepository<user, Integer>{

}
