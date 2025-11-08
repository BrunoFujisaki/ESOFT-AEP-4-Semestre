package services;

import dao.CursoDao;
import dto.CursoDTO;
import model.Curso;
import model.Professor;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CursoService {
    private static final ProfessorService professorService = new ProfessorService();

    public CursoService() {}

    public void cadastrarCurso(CursoDTO cursoDTO) {
        EntityManager em = JPAUtil.getEntityManager();
        CursoDao cursoDao = new CursoDao(em);

        try {
            em.getTransaction().begin();
            var professor = professorService.buscarProfessorPorId(cursoDTO.professorId());
            cursoDao.save(new Curso(
                    cursoDTO.nome(),
                    cursoDTO.descricao(),
                    cursoDTO.cargaHoraria(),
                    professor
                    )
            );
            em.getTransaction().commit();
            System.out.println("✅ Curso cadastrado com sucesso!");
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao cadastrar curso. "+ex.getMessage());
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
            return dao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
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