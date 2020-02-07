package dev.collegues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.collegues.entite.Collegue;
import dev.collegues.repository.CollegueRepository;

@Component
public class StartUp {
	private static final Logger LOG = LoggerFactory.getLogger(StartUp.class);
	private CollegueRepository collegueRepository;

	/**
	 * @param collegueRepository
	 */
	public StartUp(CollegueRepository collegueRepository) {
		super();
		this.collegueRepository = collegueRepository;
	}


	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		LOG.info("Démarrage de l'application");
		if (this.collegueRepository.count() == 0) {

			String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/768px-Circle-icons-profile.svg.png";
			List<Collegue> collegues = new ArrayList<Collegue>();
			collegues.add(new Collegue(1, UUID.randomUUID().toString(), "Stark", "Bran", "bran.stark@north.org",
					LocalDate.of(2001, 3, 12), url));
			collegues.add(new Collegue(2, UUID.randomUUID().toString(), "Snow", "Jon", "jon.snow@north.org",
					LocalDate.of(1987, 5, 27), url));
			collegues.add(new Collegue(3, UUID.randomUUID().toString(), "Stark", "Eddard", "eddard.stark@north.org",
					LocalDate.of(1956, 4, 3), url));
			collegues.add(new Collegue(4, UUID.randomUUID().toString(), "Stark", "Sansa", "sansa.stark@north.org",
					LocalDate.of(1989, 9, 26), url));
			collegues.add(new Collegue(5, UUID.randomUUID().toString(), "Stark", "Arya", "arya.stark@north.org",
					LocalDate.of(1996, 6, 14), url));
			this.collegueRepository.saveAll(collegues);
			System.out.println("Added");
		}
	}

}
