package dao;

import model.Curso;
import model.Professor;
import javax.persistence.EntityManager;
import java.util.List;

public class CursoDao {
    private EntityManager em;

    public CursoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Curso curso) {
        em.persist(curso);
    }

    public List<Curso> findAllCursos() {
        return em.createQuery("SELECT c FROM Curso c", Curso.class)
                .getResultList();
    }

    public Curso findById(Long id) {
        return em.find(Curso.class, id);
    }

    public List<Curso> findByProfessor(Professor professor) {
        return em.createQuery("SELECT c FROM Curso c WHERE c.professor = :professor", Curso.class)
                .setParameter("professor", professor)
                .getResultList();
    }
}