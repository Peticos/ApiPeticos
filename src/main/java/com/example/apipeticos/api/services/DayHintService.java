package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.DayHint;
import com.example.apipeticos.api.repositories.DayHintRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DayHintService {

    private final DayHintRepository dayHintRepository;

    public DayHintService(DayHintRepository dayHintRepository){
        this.dayHintRepository = dayHintRepository;
    }

    public List<DayHint> findAll(){
        return dayHintRepository.findAll();
    }

    public List<DayHint> findRandomDayHints() {

        int minimo= dayHintRepository.findMinId();
        int maximo = dayHintRepository.findMaxId();
        List<Integer> randomIds = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(minimo, maximo+1))
                .distinct()
                .limit(3)
                .boxed()
                .collect(Collectors.toList());

        return dayHintRepository.findRandom(randomIds);
    }


}
