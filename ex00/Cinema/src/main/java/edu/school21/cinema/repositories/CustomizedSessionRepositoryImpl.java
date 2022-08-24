package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomizedSessionRepositoryImpl implements CustomizedSessionRepository<Session> {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Session> getAll() {
        return entityManager.createQuery(
                "SELECT s FROM Session s JOIN FETCH s.film f LEFT JOIN FETCH f.poster JOIN FETCH s.hall h", Session.class)
                .getResultList();
    }
}
