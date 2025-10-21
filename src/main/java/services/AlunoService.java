package services;

import dao.AlunoDao;
import model.Aluno;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class AlunoService {

    public AlunoService() {}

    public void cadastrarAluno(Aluno aluno) {
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao alunoDao = new AlunoDao(em);

        try {
            em.getTransaction().begin();
            alunoDao.save(aluno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao cadastrar aluno.", ex);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Aluno> getAlunos() {
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao dao = new AlunoDao(em);
        List<Aluno> alunos;

        try {
            alunos = dao.findAllAlunos();
            return alunos;
        } catch (Exception e) {

            throw new RuntimeException("Falha ao buscar alunos.", e);
        }
    }
}
