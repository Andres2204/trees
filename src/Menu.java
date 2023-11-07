import java.util.Scanner;

public class Menu {
    
    
    public static int mainMenu(BinaryTree bt) {
        Scanner read = new Scanner(System.in);
        System.out.println("""
            1. Mostrar recorridos.
            2. Añadir Elemento.
            """);
        int opt = read.nextInt();
        read.close();
        return opt;
    }

}
