package controller;

import model.Professor;
import services.ProfessorService;
import util.ScannerUtil;

public class ProfessorController {
    private static final ProfessorService professorService = new ProfessorService();

    private ProfessorController() {}

    public static void exibirTelaInicialProfessor(Professor professor) {
        int opcao;

        do {
            System.out.println("\n=== MENU DO PROFESSOR ===");
            System.out.printf("Bem-vindo, Professor %s!\n", professor.getNome());
            System.out.println("==========================");
            System.out.println("1. Ver informações do perfil");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = ScannerUtil.lerInt();
            switch (opcao) {
                case 1 -> verPerfil(professor);
                case 0 -> System.out.println("\nSaindo do menu do professor...");
                default -> System.err.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void verPerfil(Professor professor) {
        System.out.println("\n--- SEU PERFIL ---");
        System.out.println("ID: " + professor.getId());
        System.out.println("Nome: " + professor.getNome());
        System.out.println("Email: " + professor.getEmail());
        System.out.println("Especialidade: " + professor.getEspecialidade());
        ScannerUtil.lerLinha();
    }
}