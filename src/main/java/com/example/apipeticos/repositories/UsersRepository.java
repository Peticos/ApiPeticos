package com.example.apipeticos.repositories;

import com.example.apipeticos.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users, Long> {

}