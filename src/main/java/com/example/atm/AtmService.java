package com.example.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtmService {

    private final AtmRepository atmRepository;

    @Autowired
    public AtmService(AtmRepository atmRepository) {
        this.atmRepository = atmRepository;
    }

    public List<Atm> getAtm() {
        return atmRepository.findAll();
    }

}
