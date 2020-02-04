package dev.collegues;

import java.time.LocalDate;

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
			Collegue col = new Collegue(1, "56789", "Stark", "Bran", "bran.stark@north.org", LocalDate.of(2001, 3, 12),
					url);
			this.collegueRepository.save(col);
		}
	}

}
