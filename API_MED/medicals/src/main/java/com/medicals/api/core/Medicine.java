package com.medicals.api.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "medicine")
public class Medicine {
	
	@Id
	private String id;
	@JsonProperty("CODE")
    private String code;  // CODE
    
    @JsonProperty("NOM")
    private String nom;  // NOM
    
    @JsonProperty("DCI1")
    private String dci1;  // DCI1
    
    @JsonProperty("DOSAGE1")
    private String dosage1;  // DOSAGE1
    
    @JsonProperty("UNITE_DOSAGE1")
    private String uniteDosage1;  // UNITE_DOSAGE1
    
    @JsonProperty("FORME")
    private String forme;  // FORME
    
    @JsonProperty("PRESENTATION")
    private String presentation;  // PRESENTATION
    
    @JsonProperty("PPV")
    private Double ppv;  // PPV
    
    @JsonProperty("PH")
    private Double ph;  // PH
    
    @JsonProperty("PRIX_BR")
    private Double prixBr;  // PRIX_BR
    
    @JsonProperty("PRINCEPS_GENERIQUE")
    private String princepsGenerique;  // PRINCEPS_GENERIQUE
    
    @JsonProperty("TAUX_REMBOURSEMENT")
    private String tauxRemboursement;  // TAUX_REMBOURSEMENT
    

	public Medicine(String id, String code, String nom, String dci1, String dosage1, String uniteDosage1, String forme,
			String presentation, Double ppv, Double ph, Double prixBr, String princepsGenerique,
			String tauxRemboursement) {
		super();
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.dci1 = dci1;
		this.dosage1 = dosage1;
		this.uniteDosage1 = uniteDosage1;
		this.forme = forme;
		this.presentation = presentation;
		this.ppv = ppv;
		this.ph = ph;
		this.prixBr = prixBr;
		this.princepsGenerique = princepsGenerique;
		this.tauxRemboursement = tauxRemboursement;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDci1() {
		return dci1;
	}

	public void setDci1(String dci1) {
		this.dci1 = dci1;
	}

	public String getDosage1() {
		return dosage1;
	}

	public void setDosage1(String dosage1) {
		this.dosage1 = dosage1;
	}

	public String getUniteDosage1() {
		return uniteDosage1;
	}

	public void setUniteDosage1(String uniteDosage1) {
		this.uniteDosage1 = uniteDosage1;
	}

	public String getForme() {
		return forme;
	}

	public void setForme(String forme) {
		this.forme = forme;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public Double getPpv() {
		return ppv;
	}

	public void setPpv(Double ppv) {
		this.ppv = ppv;
	}

	public Double getPh() {
		return ph;
	}

	public void setPh(Double ph) {
		this.ph = ph;
	}

	public Double getPrixBr() {
		return prixBr;
	}

	public void setPrixBr(Double prixBr) {
		this.prixBr = prixBr;
	}

	public String getPrincepsGenerique() {
		return princepsGenerique;
	}

	public void setPrincepsGenerique(String princepsGenerique) {
		this.princepsGenerique = princepsGenerique;
	}

	public String getTauxRemboursement() {
		return tauxRemboursement;
	}

	public void setTauxRemboursement(String tauxRemboursement) {
		this.tauxRemboursement = tauxRemboursement;
	}

}
