package controller;

import dto.AlunoDTO;
import services.AlunoService;
import util.ScannerUtil;

public class AutenticacaoController {
    private static final AlunoService alunoService = new AlunoService();

    private AutenticacaoController() {}

    public static void tentarLogin() {
        System.out.println("\n--- TENTAR LOGIN ---");
        System.out.print("Email: ");
        String email = ScannerUtil.lerLinha();
        System.out.print("Senha: ");
        String senha = ScannerUtil.lerLinha();

        if (email.equals("admin") && senha.equals("123")) {
            System.out.println("\n✅ Login bem-sucedido! Bem-vindo!");
            AdminController.exibirTelaInicialAdmin();
            return;
        }

        var aluno = alunoService.getAlunoPorCredenciais(email, senha);
        if (aluno != null)
            AlunoController.exibirTelaInicialAluno(aluno);

    }

    public static void cadastrarNovoUsuario() {
        System.out.println("\n--- FAZER CADASTRO ---");
        System.out.print("Nome: ");
        String nome = ScannerUtil.lerLinha();
        System.out.print("Email: ");
        String email = ScannerUtil.lerLinha();
        System.out.print("Senha: ");
        String senha = ScannerUtil.lerLinha();
        System.out.print("Data nascimento (DD/MM/YYYY): ");
        String dataNascimento = ScannerUtil.lerLinha();

        alunoService.cadastrarAluno(new AlunoDTO(
                nome, email, senha,
                dataNascimento
        ));


    }
}
