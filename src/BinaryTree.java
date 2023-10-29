public class BinaryTree {
    // [=================== Attributes ===================]

    private Node root;

    // [=================== Constructors ===================]

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(char[] c) {
        root = new Node(c[0]);

        for (char d : c) {
            if (c[0] == d)
                continue;
            addElement(d, root);
            // System.out.println(d);
        }
    }

    public void addElement(char c, Node r) {
        if (r != null) {
            if (r.getData() > c) {
                if (r.getLeftChild() != null) {
                    addElement(c, r.getLeftChild());
                } else {
                    r.setLeftChild(new Node(c));
                    return;
                }
            } else if (r.getData() < c) {
                if (r.getRightChild() != null) {
                    addElement(c, r.getRightChild());
                } else {
                    r.setRightChild(new Node(c));
                    return;
                }
            } else {
                System.out.println("wtf is that");
            }
        }
    }

    // [=================== Methods ===================]

    // [=================== Utitly ===================]

    // print tree

    public static void showTrunks(Trunk p)
    {
        if (p == null) {
            return;
        }
 
        showTrunks(p.prev);
        System.out.print(p.str);
    }

    void printTree(Node root, Trunk prev, boolean isLeft)
    {
        if (root == null) {
            return;
        }
 
        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);
 
        printTree(root.getRightChild(), trunk, true);
 
        if (prev == null) {
            trunk.str = "---";
        }
        else if (isLeft) {
            trunk.str = ".---";
            prev_str = "   |";
        }
        else {
            trunk.str = "`---";
            prev.str = prev_str;
        }
 
        showTrunks(trunk);
        System.out.println(" " + root.getData());
 
        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";
 
        printTree(root.getLeftChild(), trunk, false);
    }

    // Show methods

    public String showTree(Node r, String s) {
        if (r != null) {


            for (int i = 1; i <= 4; i++) {
                System.out.println("lvl " + i + ": " + dataByLevel(i) + "\n");
            }

            return s;
        }
        return "Empty Tree.";
    }

    public String showTree() {
        String s = null;
        return showTree(root, s);
    }

    // elements by level

    public String dataByLevel(int selectedLvl) {
        return dataByLevel(root, selectedLvl, 1, "");
    }

    public String dataByLevel(Node r, int selectedlvl, int lvl, String s) {
        if (r != null) {
            s = dataByLevel(r.getLeftChild(), selectedlvl, lvl+1, s);
            if (selectedlvl == lvl) {
                s += r.getData(); return s;
            };
            s += dataByLevel(r.getRightChild(), selectedlvl, lvl+1, s);
        } else {
            return "";
        }
        return s;
    }

    // Read Methods

    public String inorden(Node r, String s) { // i - r - d
        if (r != null) {
            s = inorden(r.getLeftChild(), s);
            s += r.getData();
            s += inorden(r.getRightChild(), s);
            return s;
        }
        return "";
    }

    // [=================== Getters and Setters ===================]

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}