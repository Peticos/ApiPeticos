package com.example.apipeticos.services;

import com.example.apipeticos.models.Users;
import com.example.apipeticos.repositories.UsersRepository;
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
    public Users addUser(Users user){
        return usersRepository.save(user); // o save serve para salvar o objeto ou alterar
    }

    public Users findbyId(long id){
        return usersRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuario não encontrado"));
    }

    public Users deleteUser(long id){
        Users user = findbyId(id);
        usersRepository.delete(user);
        return user;
    }
}
