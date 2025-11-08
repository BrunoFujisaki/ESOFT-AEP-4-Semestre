package controller;

import dto.AlunoCursoDTO;
import model.Aluno;
import services.AlunoCursoService;
import util.ScannerUtil;

public class AlunoController {
    private static final AlunoCursoService alunoCursoService = new AlunoCursoService();

    private AlunoController() {}

    public static void exibirTelaInicialAluno(Aluno aluno) {
        int opcao;

        do {
            System.out.println("\n=== MENU DO ALUNO ===");
            System.out.printf("Bem-vindo, %s!\n", aluno.getNome());
            System.out.println("=========================");
            System.out.println("1. Ver cursos disponíveis");
            System.out.println("2. Matricular-se em um curso");
            System.out.println("3. Ver meus cursos");
            System.out.println("4. Sair de um curso");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = ScannerUtil.lerInt();
            switch (opcao) {
                case 1 -> CursoController.listarCursos();
                case 2 -> matricular(aluno);
                case 3 -> verMeusCursos(aluno);
                case 4 -> sairDeCurso(aluno);
                case 0 -> System.out.println("\nSaindo do menu do aluno...");
                default -> System.err.println("❌ Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void matricular(Aluno aluno) {
        System.out.println("\n--- MATRÍCULA EM CURSO ---");
        System.out.print("Digite o ID do curso em que deseja se matricular: ");
        long cursoId = ScannerUtil.lerInt();
        alunoCursoService.matricularAlunoEmCurso(new AlunoCursoDTO(aluno.getId(), cursoId));
    }

    private static void verMeusCursos(Aluno aluno) {
        System.out.println("\n--- SEUS CURSOS ---");
        var cursos = alunoCursoService.getCursosPorAlunoId(aluno.getId());
        cursos.forEach(c -> {
            System.out.println("- Id: "+c.getId()+", Nome: "+c.getNome()+", Carga Horária: "+c.getCargaHoraria());
        });
        ScannerUtil.lerLinha();
    }

    private static void sairDeCurso(Aluno aluno) {
        System.out.println("\n--- SAIR DE UM CURSO ---");
        System.out.println("Digite o ID do curso que voçe deseja sair:");
        long cursoId = ScannerUtil.lerInt();
        alunoCursoService.cancelarMatricula(aluno.getId(), cursoId);
    }


}
