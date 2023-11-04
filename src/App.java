public class App {
    public static void main(String[] args) throws Exception {
        
        BinaryTree bt = new BinaryTree("dcabgfh".toCharArray());

        bt.printTree(bt.getRoot(), null, false);
        System.out.println(bt.countRoots(bt.getRoot()));
        System.out.println(bt.countLeaves(bt.getRoot()));
        System.out.println(bt.getBrother('f').getData());
    
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