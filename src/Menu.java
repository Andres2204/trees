import java.util.Scanner;

public class Menu {

    public static void main() {
        BinaryTree bt = new BinaryTree("dcabgfh".toCharArray());
        Scanner read = new Scanner(System.in);
        int opt = 1;

        while (opt != 0) {

            try {

                System.out.println("[============== Impresion del arbol ==============]\n\n");
                bt.printTree(bt.getRoot(), null, false);

                System.out.print("\n\n" + mainMenu());
                opt = Integer.parseInt(read.nextLine());

                switch (opt) {

                    // creation and insertion

                    case 1: // create tree

                        break;

                    case 2: // insert

                        System.out.print("Elemento a agregar (caracter): \s");
                        bt.addElement(read.next().charAt(0));
                        bt.balanceTree(bt.getRoot());
                        break;

                    // show tree content

                    case 3: // inorden posorden preorden
                        res("Pre-orden: " + bt.preorder() + "\nPos-orden: " + bt.postorder() + "\nIn-orden: "
                                + bt.inorder());
                        break;

                    case 4: // counting information
                        res("Numero de sub-arboles: " + bt.countRoots() + "\nNumero de hojas: " + bt.countLeaves());
                        break;

                    // element characteristics

                    case 5: // height
                        System.out.print("Elemento a para buscar altura (caracter): \s");
                        System.out.println(bt.getHeight(bt.search(read.next().charAt(0))));
                        break;

                    case 6: // lvl
                        System.out.print("Elemento a para buscar altura (caracter): \s");
                        System.out.println(bt.getLevel(bt.search(read.next().charAt(0))));
                        break;

                    case 7: // Brother
                        break;

                    case 8: // cousins
                        break;

                    case 9: // ancestors
                        break;

                    case 0:
                        System.exit(0);
                        break;

                    default:
                        res("Opcion inadecuada.");
                        break;
                }
            } catch (Exception e) {

            }
        }

        read.close();
    }

    private static String mainMenu() {
        return """

                [================ Menu de arboles ================]

                1. Crear un arbol por medio de una cadena.
                2. Añadir elemento (con avl).

                [================== Informacion ==================]

                3. Mostra recorridos.
                4. Mostrar informacion de conteo.

                [========== Informacion sobre elementos ==========]

                5. Altura de un elemento.
                6. Nivel de un elemento.
                7. Hermano de un elemento.
                8. Primos de un elemento.
                9. Ancestros de un elemento


                0. Salir.
                Opcion: \s""";

    }

    private static void res(String s) {
        System.out.println("[------------------- Respuesta -------------------]\n\n" + s + "\n\nContinuar....");
        Scanner read = new Scanner(System.in);
        read.nextLine();
        read.close();
    }

}
