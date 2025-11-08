CREATE TABLE Alunos_Cursos(
    aluno_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    data_matricula DATE NOT NULL,
    FOREIGN KEY (aluno_id) REFERENCES Alunos(id),
    FOREIGN KEY (curso_id) REFERENCES Cursos(id),
    PRIMARY KEY (aluno_id, curso_id)
);