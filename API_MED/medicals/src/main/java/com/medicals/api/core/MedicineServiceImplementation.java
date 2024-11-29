package com.medicals.api.core;

import java.util.List;

public interface MedicineServiceImplementation {
	
    public Medicine findByCode(String code);
	List<Medicine> findByNom(String nom);
	List<Medicine> findAll();
    
}
