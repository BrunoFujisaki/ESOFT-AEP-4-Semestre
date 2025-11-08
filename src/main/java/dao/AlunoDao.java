package dao;

import model.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
       var aluno = em.find(Aluno.class, id);
       if (aluno == null) {
           throw new RuntimeException("Aluno não encontrado");
       }
       return aluno;
    }

    public List<Aluno> findAllAlunos() {
        String jpql = "SELECT a FROM Aluno a";
        return this.em.createQuery(jpql, Aluno.class).getResultList();
    }

    public Aluno getAlunoByCredenciais(String email, String senha) {
        String jpql = """
        SELECT a FROM Aluno a
        WHERE a.email = :email AND a.senha = :senha
        """;

        try {
            return em.createQuery(jpql, Aluno.class)
                    .setParameter("email", email)
                    .setParameter("senha", senha)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new RuntimeException("Credenciais inválidas");
        }
    }
}
