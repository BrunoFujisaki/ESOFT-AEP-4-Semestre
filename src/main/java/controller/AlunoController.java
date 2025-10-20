package controller;

import model.Aluno;
import services.AlunoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AlunoController {
    private final AlunoService alunoService = new AlunoService();
    private final Scanner sc;

    public AlunoController(Scanner sc) {
        this.sc = sc;
    }

    public void iniciarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("\n--- MENU ALUNOS ---");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Listar Alunos");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrar();
                    break;
                case 2:
//                    listar();
                    break;
                // ...
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Data nascimento (DD/MM/YYYY): ");
        String dataNascimento = sc.nextLine();

        Aluno aluno = new Aluno(
                nome, email, senha,
                LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        );

        try {
            alunoService.cadastrarAluno(aluno);
            System.out.println("✅ Aluno cadastrado com sucesso!");
        } catch (RuntimeException e) {
            System.err.println("❌ Erro ao cadastrar: " + e.getMessage());
        }
    }
}
