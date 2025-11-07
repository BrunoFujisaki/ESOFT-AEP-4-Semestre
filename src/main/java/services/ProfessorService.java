package services;

import dao.ProfessorDao;
import model.Professor;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ProfessorService {

    public ProfessorService() {}

    public void cadastrarProfessor(Professor professor) {
        EntityManager em = JPAUtil.getEntityManager();
        ProfessorDao professorDao = new ProfessorDao(em);

        try {
            em.getTransaction().begin();
            professorDao.save(professor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao cadastrar professor.", ex);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Professor> getProfessores() {
        EntityManager em = JPAUtil.getEntityManager();
        ProfessorDao dao = new ProfessorDao(em);
        List<Professor> professores;

        try {
            professores = dao.findAllProfessores();
            return professores;
        } catch (Exception e) {
            throw new RuntimeException("Falha ao buscar professores.", e);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Professor buscarProfessorPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        ProfessorDao dao = new ProfessorDao(em);

        try {
            Professor professor = dao.findById(id);
            if (professor == null) {
                throw new RuntimeException("Professor não encontrado com ID: " + id);
            }
            return professor;
        } catch (Exception e) {
            throw new RuntimeException("Falha ao buscar professor por ID.", e);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}