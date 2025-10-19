package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cursos")
public class Curso {
    private Long id;
    private String nome;
    private String descricao;
    private Integer cargaHoraria;
    private Professor professorId;
}
