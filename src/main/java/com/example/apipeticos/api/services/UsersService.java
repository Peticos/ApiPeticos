package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.Users;
import com.example.apipeticos.api.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    public Users findByUsername(String username){
        return usersRepository.findByUsername(username);
    }

    public Users findbyId(Integer id){
        return usersRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void insertUser(Users tutorRequest) {
        usersRepository.insertUserTutor(tutorRequest.getFullName(), tutorRequest.getUsername(), tutorRequest.getEmail(), tutorRequest.getBairro(), tutorRequest.getPlan(), tutorRequest.getPhone(), tutorRequest.getGender(), tutorRequest.getProfilePicture());
    }

    public void insertUserProfissonal(Users profissionalRequest) throws Exception {
        try {
            usersRepository.insertUserProfissional(
                    profissionalRequest.getFullName(),
                    profissionalRequest.getUsername(),
                    profissionalRequest.getEmail(),
                    profissionalRequest.getBairro(),
                    profissionalRequest.getPlan(),
                    profissionalRequest.getPhone(),
                    profissionalRequest.getCnpj(),
                    profissionalRequest.getProfilePicture()
            );
        } catch (DuplicateKeyException e) {
            // Exceção quando um campo UNIQUE, como username ou email, já existe no banco
            if (e.getMessage().contains("username")) {
                System.err.println("Erro: Nome de usuário já existe: " + e.getMessage());
                throw new Exception("O nome de usuário já está em uso.", e);
            } else if (e.getMessage().contains("email")) {
                System.err.println("Erro: Email já cadastrado: " + e.getMessage());
                throw new Exception("O email já está cadastrado.", e);
            } else {
                System.err.println("Erro de chave duplicada: " + e.getMessage());
                throw new Exception("Já existe um registro com as informações fornecidas.", e);
            }
        } catch (IllegalArgumentException e) {
            // Captura exceções relacionadas a argumentos inválidos
            System.err.println("Argumento in/   válido fornecido: " + e.getMessage());
            throw new Exception("Dados inválidos fornecidos para inserir o profissional.", e);
        } catch (Exception e) {
            // Exceção geral para capturar qualquer outra exceção não prevista
            System.err.println("Erro inesperado ao inserir profissional: " + e.getMessage());
            throw new Exception("Erro desconhecido ao tentar inserir o profissional.", e);
        }
    }

    public void updateUserProfissional(Users profissionalRequest){
        usersRepository.updateUserProfissional(profissionalRequest.getFullName(), profissionalRequest.getUsernameId(), profissionalRequest.getUsername(), profissionalRequest.getEmail(), profissionalRequest.getBairro(), profissionalRequest.getPhone(), profissionalRequest.getCnpj(), profissionalRequest.getProfilePicture());
    }

    public void updateUser(Users users){
        usersRepository.updateUserTutor(users.getFullName(),users.getUsernameId(),users.getUsername(),users.getEmail(),users.getBairro(),users.getPhone(),users.getGender(), users.getProfilePicture());
    }

    public Users deleteUser(Integer id){
        Users user = findbyId(id);
        usersRepository.deleteByUsername(user.getUsername());
        return user;
    }
}
