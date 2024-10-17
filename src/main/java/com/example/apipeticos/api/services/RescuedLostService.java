package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.RescuedLost;
import com.example.apipeticos.api.repositories.RescuedLostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RescuedLostService {

    @Autowired
    private RescuedLostRepository rescuedLostRepository;

    public void insertRescuedLost(RescuedLost rescuedLost) {
        try {
            rescuedLostRepository.insertRescuedLost(rescuedLost.getIdPet(), rescuedLost.getIdUser(), rescuedLost.getBairro(), rescuedLost.getTitle(), rescuedLost.getDescription(), rescuedLost.getPostTime(), rescuedLost.getPicture(), rescuedLost.getStreet(), rescuedLost.getStreetNum(), rescuedLost.getLostDate());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir os dados na tabela rescued_lost", e);
        }
    }

    public List<RescuedLost> findAll(){
        return rescuedLostRepository.findAll();
    }
}
