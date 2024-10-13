package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.Users;
import com.example.apipeticos.api.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public List<Users> findAll(){
        return usersRepository.findAll();
    }

    @Transactional
    public Users saveUser(Users user){
        return usersRepository.save(user);
    }

    public Users findByUsername(String username){
        return usersRepository.findByUsername(username);
    }

    public Users findbyId(Integer id){
        return usersRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found"));
    }

    public void insertUser(Users tutorRequest) {
        usersRepository.insertUserTutor(tutorRequest.getFullName(), tutorRequest.getUsername(), tutorRequest.getEmail(), tutorRequest.getBairro(), tutorRequest.getPlan(), tutorRequest.getPhone(), tutorRequest.getGender());
    }

    public void insertUserProfissonal(Users profissionalRequest) {
        usersRepository.insertUserProfissional(profissionalRequest.getFullName(), profissionalRequest.getUsername(), profissionalRequest.getEmail(), profissionalRequest.getBairro(), profissionalRequest.getPlan(), profissionalRequest.getPhone(), profissionalRequest.getCnpj());
    }

    public Users deleteUser(Integer id){
        Users user = findbyId(id);
        usersRepository.delete(user);
        return user;
    }
}
