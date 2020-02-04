package dev.collegues.controller;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.collegues.entite.Collegue;
import dev.collegues.service.CollegueService;

@RestController
@RequestMapping("collegues")
public class CollegueController {
	private CollegueService collegueService;

	@GetMapping
	public List<Collegue> collegueList() {
		return this.collegueService.collegueList();
	}

	@GetMapping(params = "nom")
	public List<String> collegueByNom(@RequestParam("nom") String nom) {
		return this.collegueService.collegueByNom(nom);
	}

	@GetMapping(value = "matricule")
	public List<Collegue> collegueByMatricule(@RequestParam("matricule") String matricule) {
		return this.collegueService.collegueByMatricule(matricule);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collegue> addCollegue(@RequestBody @Valid Collegue collegue) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.collegueService.addCollegue(collegue));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationException(MethodArgumentNotValidException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	@ExceptionHandler(value = { EntityExistsException.class })
	public ResponseEntity<String> clientPresent() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The collegue with this name already exists.");
	}
}
