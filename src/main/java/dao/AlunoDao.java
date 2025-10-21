package dao;

import model.Aluno;

import javax.persistence.EntityManager;
import java.util.List;

public class AlunoDao {
    private EntityManager em;

    public AlunoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Aluno aluno) {
        em.persist(aluno);
    }

    public Aluno findById(Long id) {
        try {
           return em.find(Aluno.class, id);
        } catch (Exception ex) {
            throw new RuntimeException("Aluno não encontrado");
        }
    }

    public List<Aluno> findAllAlunos() {
        String jpql = "SELECT a FROM Aluno a";
        return this.em.createQuery(jpql, Aluno.class).getResultList();
    }
}
