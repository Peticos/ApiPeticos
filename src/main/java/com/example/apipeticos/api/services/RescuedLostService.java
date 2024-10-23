package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.RescuedLost;
import com.example.apipeticos.api.repositories.RescuedLostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RescuedLostService {

    @Autowired
    private RescuedLostRepository rescuedLostRepository;

    public void insertRescuedLost(RescuedLost rescuedLost) {
        try {
            rescuedLostRepository.insertRescuedLost(rescuedLost.getIdPet(), rescuedLost.getIdUser(), rescuedLost.getBairro(), rescuedLost.getTitle(), rescuedLost.getDescription(), rescuedLost.getPostTime(),rescuedLost.getPicture(),rescuedLost.getLocation(),rescuedLost.getLostDate());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir os dados na tabela rescued_lost", e);
        }
    }

    public List<RescuedLost> findAll(){
        return rescuedLostRepository.findAll();
    }

    public List<RescuedLost> findById(Integer id){
        return rescuedLostRepository.findByIdUser(id);
    }

    public void findPet(Integer id, LocalDate rescuedDate) {
        Optional<RescuedLost> rescuedLostOptional = rescuedLostRepository.findById(id);

        if (rescuedLostOptional.isPresent()) {
            RescuedLost rescuedLost = rescuedLostOptional.get();
            rescuedLost.setRescuedDate(rescuedDate);
            rescuedLostRepository.save(rescuedLost);
        } else {
            throw new RuntimeException("Registro não encontrado com o ID: " + id);
        }
    }
}
