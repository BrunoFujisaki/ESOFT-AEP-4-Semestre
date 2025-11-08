package controller;

import model.Professor;
import services.AlunoService;
import services.ProfessorService;
import util.ScannerUtil;

public class AdminController {
    private static final AlunoService alunoService = new AlunoService();
    private static final ProfessorService professorService = new ProfessorService();

    private AdminController() {}

    public static void exibirTelaInicialAdmin() {
        int opcao = 0;

        do {
            System.out.println("\n==================================");
            System.out.println("       SISTEMA ACADÊMICO JAVA       ");
            System.out.println("==================================");
            System.out.println("1. Menu Alunos");
            System.out.println("2. Menu Professores");
            System.out.println("3. Menu Cursos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = ScannerUtil.lerInt();

            switch (opcao) {
                case 1 -> menuAlunos();
                case 2 -> menuProfessores();
                case 3 -> CursoController.exibirMenuCurso();
                case 0 -> System.out.println("\nEncerrando a aplicação...");
                default -> System.out.println("Opção desconhecida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void menuAlunos() {
        int opcao = 0;
        do {
            System.out.println("\n--- MENU ALUNOS ---");
            System.out.println("1. Listar Alunos");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = ScannerUtil.lerInt();

            switch (opcao) {
                case 1 -> listarAlunos();
            }
        } while (opcao != 0);
    }

    private static void menuProfessores() {
        int opcao = 0;
        do {
            System.out.println("\n--- MENU PROFESSORES ---");
            System.out.println("1. Listar Professores");
            System.out.println("2. Cadastrar Professor");
            System.out.println("3. Acessar Menu do Professor");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = ScannerUtil.lerInt();

            switch (opcao) {
                case 1 -> listarProfessores();
                case 2 -> cadastrarProfessor();
                case 3 -> acessarMenuProfessor();
                case 0 -> System.out.println("\nVoltando ao menu principal...");
                default -> System.out.println("Opção desconhecida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void listarAlunos() {
        var alunos = alunoService.getAlunos();

        if (alunos == null || alunos.isEmpty()) {
            System.out.println("\nNenhum aluno cadastrado.");
            return;
        }

        System.out.println("\n--- LISTA DE ALUNOS ---");
        alunos.forEach(a -> {
            System.out.println("- Id: "+a.getId()+", Nome: "+a.getNome()+", Email: "+a.getEmail()+", Data Nascimento: "+a.getDataNascimento());
        });

        ScannerUtil.lerLinha();
    }

    private static void listarProfessores() {
        var professores = professorService.getProfessores();

        if (professores == null || professores.isEmpty()) {
            System.out.println("\nNenhum professor cadastrado.");
            return;
        }

        System.out.println("\n--- LISTA DE PROFESSORES ---");
        professores.forEach(p -> {
            System.out.println("- Id: "+p.getId()+", Nome: "+p.getNome()+", Email: "+p.getEmail()+", Especialidade: "+p.getEspecialidade());
        });

        ScannerUtil.lerLinha();
    }

    private static void cadastrarProfessor() {
        System.out.println("\n--- CADASTRAR PROFESSOR ---");
        System.out.print("Nome: ");
        String nome = ScannerUtil.lerLinha();
        System.out.print("Email: ");
        String email = ScannerUtil.lerLinha();
        System.out.print("Especialidade: ");
        String especialidade = ScannerUtil.lerLinha();

        Professor professor = new Professor();
        professor.setNome(nome);
        professor.setEmail(email);
        professor.setEspecialidade(especialidade);

        professorService.cadastrarProfessor(professor);
        System.out.println("Professor cadastrado com sucesso!");
    }

    private static void acessarMenuProfessor() {
        System.out.println("\n--- ACESSAR MENU DO PROFESSOR ---");
        System.out.print("Digite o ID do professor: ");
        long professorId = ScannerUtil.lerInt();

        Professor professor = professorService.buscarProfessorPorId(professorId);
        if (professor != null) {
            ProfessorController.exibirTelaInicialProfessor(professor);
        } else {
            System.out.println("Professor não encontrado!");
        }
    }
}