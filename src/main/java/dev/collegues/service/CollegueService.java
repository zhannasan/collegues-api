package dev.collegues.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import dev.collegues.entite.Collegue;
import dev.collegues.repository.CollegueRepository;

@Service
public class CollegueService {
	private CollegueRepository collegueRepository;

	public List<Collegue> collegueList() {
		return this.collegueRepository.findAll();
	}


	public List<String> collegueByNom(String nom) {
		return this.collegueRepository.findByNom(nom);
	}

	public List<Collegue> collegueByMatricule(String matricule) {
		return this.collegueRepository.findByMatricule(matricule);
	}
	
	public Collegue addCollegue(@RequestBody Collegue collegue){
		if (this.collegueRepository.existsByNomAndPrenoms(collegue.getNom(), collegue.getPrenoms())) {
			throw new EntityExistsException();
		}
		return this.collegueRepository.save(collegue);
	}

}
