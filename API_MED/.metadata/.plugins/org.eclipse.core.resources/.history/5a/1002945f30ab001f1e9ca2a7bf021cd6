package com.medicals.api.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineService implements MedicineServiceImplementation {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    @Override
    public List<Medicine> findByNom(String nom) {
        return medicineRepository.findByNom(nom);
    }

    @Override
    public Medicine findByCode(String code) {
        return medicineRepository.findByCode(code); 
    }
}
