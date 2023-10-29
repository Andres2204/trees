public class App {
    public static void main(String[] args) throws Exception {
        
        BinaryTree bt = new BinaryTree("dcabgfh".toCharArray());

        bt.printTree(bt.getRoot(), null, false);

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