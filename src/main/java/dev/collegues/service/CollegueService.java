package dev.collegues.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.collegues.entite.Collegue;
import dev.collegues.repository.CollegueRepository;

@Service
public class CollegueService {
	private CollegueRepository collegueRepository;

	// private NullAwareBeanUtilsBean nullAwareBeanUtilsBean;
	/**
	 * @param collegueRepository
	 */
	public CollegueService(CollegueRepository collegueRepository) {
		super();
		this.collegueRepository = collegueRepository;
	}

	public List<Collegue> collegueAll() {
		return this.collegueRepository.findAll();
	}

	public List<String> collegueByNom(String nom) {
		return this.collegueRepository.findByNom(nom);
	}

	public ResponseEntity<?> collegueByMatricule(String matricule) {
		if(this.collegueRepository.existsByMatricule(matricule)){
			return ResponseEntity.status(HttpStatus.OK).body(this.collegueRepository.findByMatricule(matricule));
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Collegue non trouvé");		
	}
	
	public Collegue addCollegue(Collegue collegue) {
		if (this.collegueRepository.existsByNomAndPrenoms(collegue.getNom(), collegue.getPrenoms())) {
			throw new EntityExistsException();
		}
		return this.collegueRepository.save(collegue);
	}

	/*
	 * TODO: re-try with BeanUtils! public ResponseEntity patchCollegue(String
	 * matricule, Map<String, String> json) { try { if
	 * (this.collegueRepository.existsByMatricule(matricule)) { ObjectMapper
	 * objectMapper = new ObjectMapper(); Collegue patch =
	 * objectMapper.convertValue(json, Collegue.class);
	 * System.out.println(patch.toString()); Collegue existing =
	 * this.collegueRepository.findByMatricule(matricule); BeanUtilsBean notNull
	 * = new NullAwareBeanUtilsBean();
	 * BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
	 * BeanUtils.copyProperties(existing, patch);
	 * 
	 * System.out.println(patch.toString()); return ResponseEntity
	 * .ok(this.collegueRepository.update(patch.getEmail(), patch.getPhotoUrl(),
	 * matricule)); } throw new EntityNotFoundException(); } catch
	 * (IllegalAccessException | InvocationTargetException e) {
	 * 
	 * e.printStackTrace(); return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error"); } }
	 */
	public ResponseEntity patchCollegue(String matricule, Map<String, String> json) {
	if (this.collegueRepository.existsByMatricule(matricule)) {
		ObjectMapper objectMapper = new ObjectMapper();
		Collegue patch = objectMapper.convertValue(json, Collegue.class);
		Collegue existing = this.collegueRepository.findByMatricule(matricule);
		if(patch.getEmail()!=null){
			existing.setEmail(patch.getEmail());
		}
		if(patch.getPhotoUrl()!=null){
			existing.setEmail(patch.getPhotoUrl());
		}
		return ResponseEntity
				.ok(this.collegueRepository.update(existing.getEmail(), existing.getPhotoUrl(), matricule));
		}throw new EntityNotFoundException();
}
}
