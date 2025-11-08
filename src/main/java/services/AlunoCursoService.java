package services;

import dao.AlunoCursoDao;
import dao.AlunoDao;
import dao.CursoDao;
import dto.AlunoCursoDTO;
import model.AlunoCurso;
import model.AlunoCursoId;
import model.Curso;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlunoCursoService {
    public AlunoCursoService() {}

    public void matricularAlunoEmCurso(AlunoCursoDTO alunoCursoDTO) {
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao alunoDao = new AlunoDao(em);
        CursoDao cursoDao = new CursoDao(em);
        AlunoCursoDao acDao = new AlunoCursoDao(em);
        try {
            em.getTransaction().begin();

            var aluno = alunoDao.findById(alunoCursoDTO.alunoId());
            var curso = cursoDao.findById(alunoCursoDTO.cursoId());

            var acId = new AlunoCursoId();
            acId.setAlunoId(aluno.getId());
            acId.setCursoId(curso.getId());

            if (acDao.existsById(acId)) {
                throw new RuntimeException("Aluno já está matriculado neste curso.");
            }

            acDao.save(new AlunoCurso(acId, aluno, curso, LocalDate.now()));

            em.getTransaction().commit();
            System.out.println("Matricula realizada com sucesso!");
        }catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao se matricular no curso. "+ex.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    public List<Curso> getCursosPorAlunoId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        AlunoCursoDao acDao = new AlunoCursoDao(em);
        List<Curso> cursos = new ArrayList<>();
        try {
            cursos = acDao.cursosAluno(id);
        }catch (Exception e) {
            System.err.println("Falha ao buscar os seus cursos");
        }finally {
            if (em.isOpen())
                em.close();
        }
        return cursos;
    }

    public void cancelarMatricula(Long alunoId, long cursoId) {
        EntityManager em = JPAUtil.getEntityManager();
        AlunoCursoDao acDao = new AlunoCursoDao(em);

        try {
            em.getTransaction().begin();

            var acId = new AlunoCursoId();
            acId.setAlunoId(alunoId);
            acId.setCursoId(cursoId);

            var matricula = acDao.findById(acId);
            acDao.delete(matricula);

            System.out.println("✅ Matrícula removida com sucesso!");

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            System.err.println("❌ Erro ao sair do curso: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }
}
