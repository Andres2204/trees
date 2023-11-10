import java.util.Scanner;

public class Menu {

    public static void main() {
        BinaryTree bt = new BinaryTree("0123456789abcdefghijkelmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray());
        Node searched = null;
        Scanner read = new Scanner(System.in);
        String aux = "";
        int opt = 1;
        char charAux = 0;

        while (opt != 0) {

            try {

                System.out.println("\n[============== Impresion del arbol ==============]\n\n");
                bt.printTree(bt.getRoot(), null, false);

                System.out.print("\n\n" + mainMenu());
                opt = Integer.parseInt(read.nextLine());

                switch (opt) {

                    // creation and insertion

                    case 1: // create tree
                        System.out.print("Ingrese cadena para crear nuevo arbol: \s");
                        bt = new BinaryTree(read.nextLine().toCharArray());
                        res("Nuevo arbol creado!");
                        break;

                    case 2: // insert

                        System.out.print("Elemento a agregar (caracter): \s");
                        bt.addElement(read.next().charAt(0));
                        bt.balanceTree(bt.getRoot());
                        res("Elemento insertado!");
                        break;

                    case 3: // delete element
                        System.out.print("Elemento a eliminar (caracter): \s");
                        searched = bt.search(read.next().charAt(0));
                        if (searched == null) {
                            res("El elemento no ha sido encontrado.");
                        } else {
                            bt.deleteElement(searched);
                            res("Se ha eliminado el dato " + searched.getData());
                        }
                        break;


                    // show tree content

                    case 4: // inorden posorden preorden
                        res("Pre-orden: " + bt.preorder() + "\nPos-orden: " + bt.postorder() + "\nIn-orden: "
                                + bt.inorder());
                        break;

                    case 5: // counting information
                        res("Numero de sub-arboles: " + bt.countRoots() + "\nNumero de hojas: " + bt.countLeaves());
                        break;

                    // element characteristics

                    case 6: // height
                        System.out.print("Elemento para mostrar altura (caracter): \s");
                        res("Su altura es: "+bt.getHeight(bt.search(read.next().charAt(0))));
                        break;

                    case 7: // lvl
                        System.out.print("Elemento para mostrar altura (caracter): \s");
                        res("Su nivel es: "+bt.getLevel(bt.search(read.next().charAt(0))));
                        break;

                    case 8: // Brother
                        System.out.print("Elemento para encontrar hermano (caracter): \s");
                        charAux = bt.getBrother(bt.search(read.next().charAt(0)));
                        if (charAux != 0) {
                            res("Su Hermano es: " + charAux);
                        } else res("El dato ingresado no tiene hermano");
                        break;

                    case 9: // cousins
                        System.out.print("Elemento para buscar primos (caracter): \s");

                        searched = bt.search(read.next().charAt(0));
                        if (searched == null) {
                            res("El elemento no ha sido encontrado.");
                        } else {
                            aux = bt.dataByLevel(bt.getLevel(searched)).replace(""+searched.getData(), "");
                            if (aux != "") {
                                res("Los primos son: " + aux);
                            } else res("El elemento ingresado no tiene primos.");
                        }
                        break;

                    case 10: // ancestors
                        System.out.print("Elemento sus ancestros (caracter): \s");

                        searched = bt.search(read.next().charAt(0));
                        if (searched == null) {
                            res("El elemento no ha sido encontrado.");
                        } else {
                            System.out.println("\n[=================== Respuesta ===================]\n\n");
                            bt.showAncestors(searched.getData());
                        }
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
                3. Elimnar un element

                [================== Informacion ==================]

                4. Mostra recorridos.
                5. Mostrar informacion de conteo.

                [========== Informacion sobre elementos ==========]

                6. Altura de un elemento.
                7. Nivel de un elemento.
                8. Hermano de un elemento.
                9. Primos de un elemento.
                10. Ancestros de un elemento


                0. Salir.
                Opcion: \s""";

    }

    private static void res(String s) {
        System.out.println("\n[=================== Respuesta ===================]\n\n" + s);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Ctrl + C detectado. Saliendo...");
        }
    }

}
