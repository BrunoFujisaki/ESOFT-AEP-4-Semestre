package dao;

import model.Professor;
import javax.persistence.EntityManager;
import java.util.List;

public class ProfessorDao {
    private EntityManager em;

    public ProfessorDao(EntityManager em) {
        this.em = em;
    }

    public void save(Professor professor) {
        em.persist(professor);
    }

    public List<Professor> findAllProfessores() {
        return em.createQuery("SELECT p FROM Professor p", Professor.class)
                .getResultList();
    }

    public Professor findById(Long id) {
        return em.find(Professor.class, id);
    }
}