package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    @Column(name = "carga_horaria")
    private Integer cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @OneToMany(mappedBy = "curso")
    private List<AlunoCurso> matriculas;

    @OneToMany(mappedBy = "curso")
    private List<Modulo> modulos = new ArrayList<>();

    public Curso() {
    }

    public Curso(String nome, String descricao, Integer cargaHoraria, Professor professor) {
        this.nome = nome;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.matriculas = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<AlunoCurso> getMatriculas() {
        return matriculas;
    }
}
