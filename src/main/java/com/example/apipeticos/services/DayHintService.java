package com.example.apipeticos.services;

import com.example.apipeticos.models.DayHint;
import com.example.apipeticos.repositories.DayHintRepository;
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
