package model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AlunoCursoId implements Serializable {
    private Long alunoId;
    private Long cursoId;

    public AlunoCursoId() {
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AlunoCursoId that = (AlunoCursoId) o;
        return Objects.equals(alunoId, that.alunoId) && Objects.equals(cursoId, that.cursoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alunoId, cursoId);
    }
}
