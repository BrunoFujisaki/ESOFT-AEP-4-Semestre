package controller;

import util.ScannerUtil;

public class MenuController {
    private MenuController() {}

    public static void iniciar() {
        int opcao = 0;

        do {
            System.out.println("\n==================================");
            System.out.println("           BEM-VINDO(A)           ");
            System.out.println("==================================");
            System.out.println("1. Entrar (Login)");
            System.out.println("2. Cadastrar Novo Usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = ScannerUtil.lerInt();

            switch (opcao) {
                case 1:
                    AutenticacaoController.tentarLogin();
                    break;
                case 2:
                    AutenticacaoController.cadastrarNovoUsuario();
                    break;
                case 0:
                    System.out.println("\nEncerrando a aplicação...");
                    break;
                default:
                    System.out.println("Opção desconhecida. Tente novamente.");
            }

        } while (opcao != 0);
    }
}
