package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.DayHint;
import com.example.apipeticos.api.repositories.DayHintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayHintService {

    private final DayHintRepository dayHintRepository;

    public DayHintService(DayHintRepository dayHintRepository){
        this.dayHintRepository = dayHintRepository;
    }

    public List<DayHint> findAll(){
        return dayHintRepository.findAll();
    }


}
