package services;

import dao.AlunoDao;
import dto.AlunoDTO;
import model.Aluno;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AlunoService {

    public AlunoService() {}

    public void cadastrarAluno(AlunoDTO alunoDTO) {
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao alunoDao = new AlunoDao(em);
        try {
            var dataConvertida = LocalDate.parse(alunoDTO.dataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            em.getTransaction().begin();
            alunoDao.save(new Aluno(
                    alunoDTO.nome(),
                    alunoDTO.email(),
                    alunoDTO.senha(),
                    dataConvertida
                    )
            );
            em.getTransaction().commit();
            System.out.println("✅ Aluno cadastrado com sucesso!");
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("❌ Erro ao cadastrar: " + ex.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Aluno getAlunoPorCredenciais(String email, String senha) {
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao alunoDao = new AlunoDao(em);

        try {
            return alunoDao.getAlunoByCredenciais(email, senha);
        }catch (Exception e) {
            System.err.println("❌ Credenciais inválidas. Tente novamente.");
            return null;
        }finally {
            if (em.isOpen())
                em.close();
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
        }finally {
            if (em.isOpen())
                em.close();
        }
    }
}
