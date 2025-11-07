package services;

import dao.CursoDao;
import model.Curso;
import model.Professor;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CursoService {

    public CursoService() {}

    public void cadastrarCurso(Curso curso) {
        EntityManager em = JPAUtil.getEntityManager();
        CursoDao cursoDao = new CursoDao(em);

        try {
            em.getTransaction().begin();
            cursoDao.save(curso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao cadastrar curso.", ex);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Curso> getCursos() {
        EntityManager em = JPAUtil.getEntityManager();
        CursoDao dao = new CursoDao(em);
        List<Curso> cursos;

        try {
            cursos = dao.findAllCursos();
            return cursos;
        } catch (Exception e) {
            throw new RuntimeException("Falha ao buscar cursos.", e);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Curso buscarCursoPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        CursoDao dao = new CursoDao(em);

        try {
            Curso curso = dao.findById(id);
            if (curso == null) {
                throw new RuntimeException("Curso não encontrado com ID: " + id);
            }
            return curso;
        } catch (Exception e) {
            throw new RuntimeException("Falha ao buscar curso por ID.", e);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Curso> buscarCursosPorProfessor(Professor professor) {
        EntityManager em = JPAUtil.getEntityManager();
        CursoDao dao = new CursoDao(em);

        try {
            return dao.findByProfessor(professor);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao buscar cursos por professor.", e);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}