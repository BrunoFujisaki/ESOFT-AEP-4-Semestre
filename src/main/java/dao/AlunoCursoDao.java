package dao;

import model.Aluno;
import model.AlunoCurso;
import model.AlunoCursoId;
import model.Curso;

import javax.persistence.EntityManager;
import java.util.List;

public class AlunoCursoDao {
    private EntityManager em;

    public AlunoCursoDao(EntityManager em) {
        this.em = em;
    }

    public void save(AlunoCurso alunoCurso) {
        em.persist(alunoCurso);
    }

    public boolean existsById(AlunoCursoId id) {
        try {
            var alunoCurso = em.find(AlunoCurso.class, id);
            return alunoCurso != null;
        } catch (Exception ex) {
            throw new RuntimeException("Aluno não encontrado");
        }
    }

    public List<Curso> cursosAluno(Long id) {
        String jpql = """
                    SELECT ac.curso
                    FROM AlunoCurso ac
                    WHERE ac.aluno.id = :id
                    """;
        return em.createQuery(jpql, Curso.class)
                .setParameter("id", id)
                .getResultList();
    }

    public AlunoCurso findById(AlunoCursoId acId) {
        var matricula =  em.find(AlunoCurso.class, acId);
        if (matricula == null) {
            throw new RuntimeException("Matrícula não encontrada!");
        }
        return matricula;
    }

    public void delete(AlunoCurso alunoCurso) {
        em.remove(alunoCurso);
    }
}
