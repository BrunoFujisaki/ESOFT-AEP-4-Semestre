package controller;

import dto.CursoDTO;
import services.CursoService;
import util.ScannerUtil;

public class CursoController {
    private static final CursoService cursoService = new CursoService();


    public static void exibirMenuCurso() {
        int opcao;

        do {
            System.out.println("\n=== MENU DE CURSOS ===");
            System.out.println("1. Cadastrar novo curso");
            System.out.println("2. Ver cursos disponíveis");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = ScannerUtil.lerInt();

            switch (opcao) {
                case 1 -> cadastrarCurso();
                case 2 -> listarCursos();
                case 0 -> System.out.println("\nVoltando ao menu anterior...");
                default -> System.out.println("❌ Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarCurso() {
        System.out.println("\n--- CADASTRAR CURSO ---");
        System.out.print("Nome do curso: ");
        String nome = ScannerUtil.lerLinha();

        System.out.print("Descrição: ");
        String descricao = ScannerUtil.lerLinha();

        System.out.print("Carga horária (em horas): ");
        int cargaHoraria = ScannerUtil.lerInt();

        System.out.print("ID do professor responsável: ");
        long professorId = ScannerUtil.lerInt();

        cursoService.cadastrarCurso(new CursoDTO(nome, descricao, cargaHoraria, professorId));
    }

    public static void listarCursos() {
        var cursos = cursoService.getCursos();

        if (cursos == null || cursos.isEmpty()) {
            System.out.println("\nNenhum curso cadastrado ainda.");
            return;
        }

        System.out.println("\n--- LISTA DE CURSOS ---");
        cursos.forEach(c -> {
            System.out.println(
                    "Id: "+c.getId()+"\nNome: "+c.getNome()+"\nProfessor: "
                    +c.getProfessor().getNome()+"\nCarga horária: "+c.getCargaHoraria()
                    +"\nDescrição: "+c.getDescricao()
            );
            System.out.println("===============================");
        });

        ScannerUtil.lerLinha();
    }
}
