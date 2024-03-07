package com.aliments.spotifydashboard.data;

import com.aliments.spotifydashboard.model.Artist;
import com.aliments.spotifydashboard.model.Song;
import com.aliments.spotifydashboard.repository.ArtistRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager entityManager;
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public JobCompletionNotificationListener(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            /*jdbcTemplate
                    .query("SELECT artist, track FROM song", new DataClassRowMapper<>(Song.class))
                    .forEach(song -> log.info("Found <{{}}> in the database.", song));*/
            Map<String, Artist> artistMap = new HashMap<>();
            entityManager.createQuery("select s.artist, count(*) from Song s group by s.artist",Object[].class)
                    .getResultList().stream().map(a-> new Artist((String) a[0], (long) a[1]))
                    .forEach(artist -> artistMap.put(artist.getName(),artist));
            entityManager.createQuery("select s.artist, count(*) from Song s where s.views > 10000000 group by s.artist",Object[].class)
                    .getResultList().stream().forEach(a -> {
                        Artist artist = artistMap.get((String) a[0]);
                        artist.setTotalTenMilViews((long) a[1]);
                    });
            log.info("Artists in the database {}", artistMap.size());
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            artistMap.values().forEach(em::persist);
            em.getTransaction().commit();
        }
    }
}
