package dev.collegues.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.collegues.entite.Collegue;

@Repository
public interface CollegueRepository extends JpaRepository<Collegue, Integer> {
	@Query("Select matricule from Collegue c where c.nom=?1")
	List<String> findByNom(String nom);

	boolean existsByNomAndPrenoms(String nom, String prenoms);

	@Query("Select c from Collegue c where c.matricule=?1")
	Collegue findByMatricule(String matricule);

	@Query(value = "Update Collegue c set c.email=?1, c.photo_url=?2 where c.matricule=?3", nativeQuery = true)
	Collegue update(String email, String url, String matricule);

	boolean existsByMatricule(String matricule);

	@Query("Select c.matricule, c.photoUrl from Collegue c")
	List<Object> findAllByMatriculePhotoUrl();

}
