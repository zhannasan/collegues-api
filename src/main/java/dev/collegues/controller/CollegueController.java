package dev.collegues.controller;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	/**
	 * @param collegueService
	 */
	public CollegueController(CollegueService collegueService) {
		super();
		this.collegueService = collegueService;
	}

	@GetMapping(params = "nom")
	public List<String> collegueByNom(@RequestParam("nom") String nom) {
		return this.collegueService.collegueByNom(nom);
	}

	@CrossOrigin
	@GetMapping(value = "{matricule}")
	public ResponseEntity<?> collegueByMatricule(@PathVariable String matricule) {
		return this.collegueService.collegueByMatricule(matricule);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collegue> addCollegue(@RequestBody @Valid Collegue collegue) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.collegueService.addCollegue(collegue));
	}

	@PatchMapping(value = "{matricule}")
	public ResponseEntity patchCollegue(@PathVariable String matricule,
			@RequestBody Map<String, String> json) {
		return this.collegueService.patchCollegue(matricule, json);
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
