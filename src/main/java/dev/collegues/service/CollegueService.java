package dev.collegues.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import dev.collegues.entite.Collegue;
import dev.collegues.entite.PatchData;
import dev.collegues.repository.CollegueRepository;

@Service
public class CollegueService {
	private CollegueRepository collegueRepository;

	/**
	 * @param collegueRepository
	 */
	public CollegueService(CollegueRepository collegueRepository) {
		super();
		this.collegueRepository = collegueRepository;
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
	
	public Collegue addCollegue(@RequestBody Collegue collegue){
		if (this.collegueRepository.existsByNomAndPrenoms(collegue.getNom(), collegue.getPrenoms())) {
			throw new EntityExistsException();
		}
		return this.collegueRepository.save(collegue);
	}

	public ResponseEntity patchCollegue(String matricule, PatchData patchData) {
		// Collegue existing =
		// this.collegueRepository.findByMatricule(matricule);
		if (this.collegueRepository.existsByMatricule(matricule)) {
			String email = patchData.getEmail();
			String url = patchData.getPhotoUrl();
			return ResponseEntity.ok(this.collegueRepository.update(email, url, matricule));
			}
		throw new EntityNotFoundException();
	}

}
