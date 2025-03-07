package com.medicals.api.core;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MedicineRepository extends MongoRepository<Medicine, String> {
	
	public List<Medicine> findAll();

    public List<Medicine> findByNom(String nom);
    
    public Medicine findByCode(String code);

    
}
