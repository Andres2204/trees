public class App {
    public static void main(String[] args) throws Exception {
        
        BinaryTree bt = new BinaryTree("dcabgfh".toCharArray());

        bt.printTree(bt.getRoot(), null, false);
        System.out.println("\nN padres: "+bt.countRoots(bt.getRoot()));
        System.out.println("N hojas: "+bt.countLeaves(bt.getRoot()));
        System.out.println("Hermano: "+bt.getBrother('g'));

        System.out.println("Height: "+bt.getHeight(bt.getRoot().getRightChild().getRightChild()));
        System.out.println("Buscar Nodo: "+ bt.search('g').getData());
        System.out.println("Buscar padre: " + bt.searchParent('a').getData());


    }
}

/*
    create tree
    lvl: 2
    1       d
    2   c      g
    3 a      f  h
    4   b
 */