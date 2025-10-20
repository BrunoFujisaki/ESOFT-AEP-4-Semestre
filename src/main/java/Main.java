import controller.AlunoController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AlunoController ac = new AlunoController(sc);
        ac.iniciarMenu();
        sc.close();

    }
}
