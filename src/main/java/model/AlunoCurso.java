package model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "alunos_cursos")
public class AlunoCurso {
    @EmbeddedId
    private AlunoCursoId alunoCursoId;

    @MapsId("alunoId")
    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    @MapsId("cursoId")
    private Curso curso;

//    private LocalDate dataMatricula;
//
//    private String status;

    public AlunoCurso() {
    }

    public AlunoCurso(AlunoCursoId alunoCursoId, Aluno aluno, Curso curso/*, LocalDate dataMatricula, String status*/) {
        this.alunoCursoId = alunoCursoId;
        this.aluno = aluno;
        this.curso = curso;
//        this.dataMatricula = dataMatricula;
//        this.status = status;
    }

    public AlunoCursoId getAlunoCursoId() {
        return alunoCursoId;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Curso getCurso() {
        return curso;
    }

//    public LocalDate getDataMatricula() {
//        return dataMatricula;
//    }
//
//    public String getStatus() {
//        return status;
//    }
}
