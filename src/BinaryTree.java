import java.util.ArrayList;

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

    // [=================== Methods ===================]

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

    public int countRoots() { return countRoots(root); }
    public int countRoots(Node r) {
        if (r != null && (r.getLeftChild() != null || r.getRightChild() != null)) {
            return countRoots(r.getLeftChild()) + countRoots(r.getRightChild()) + 1;
        } else
            return 0;
    }


    public int countLeaves() { return countLeaves(root); }
    public int countLeaves(Node r) {
        if (r != null) {
            if (r.getLeftChild() == null && r.getRightChild() == null)
                return 1;
            else
                return countLeaves(r.getLeftChild()) + countLeaves(r.getRightChild());
        }
        return 0;
    }

    public Node getBrother(Node b) { return getBrother(root, b.getData()); }
    public Node getBrother(char b) { return getBrother(root, b); }
    public Node getBrother(Node r, char b) {

        if ( r != null) {
            Node selected = null;
            if (r.getData() > b) {
                selected = getBrother(r.getLeftChild(), b);
            } else if (r.getData() < b) {
                selected = getBrother(r.getRightChild(), b);
            } else if (r.getData() == b) {
                return r;
            }

            if (selected != null) {
                if (r.getLeftChild().getData()  == b) {
                    selected =  r.getRightChild();
                } else {
                    selected = r.getLeftChild();
                }
            }

            return selected;
        }

        return null;
    }

    public int getLevel() {

        return 0;
    }

    public ArrayList<Node> showAncestors() {

        return null;
    }

    // Read Methods

    public String inorder() { return inorder(root, ""); }
    public String inorder(Node r, String s) { // i - r - d
        if (r != null) {
            s = inorder(r.getLeftChild(), s);
            s += r.getData();
            s += inorder(r.getRightChild(), s);
            return s;
        }
        return "";
    }

    public String postorder() { return postorder(root, ""); }
    public String postorder(Node r, String s) { // i - d - r
        if (r != null) {
            s = postorder(r.getLeftChild(), s);
            s += postorder(r.getRightChild(), s);
            s += r.getData();
            return s;
        }
        return "";

    }

    public String preorder() { return preorder(root, ""); }
    public String preorder(Node r, String s) { // r - i - d
        if (r != null) {
            s += r.getData();
            s = preorder(r.getLeftChild(), s);
            s += preorder(r.getRightChild(), s);
            return s;
        }
        return "";
    }

    // [=================== Utitly ===================]

    // print tree

    public static void showTrunks(Trunk p) {
        if (p == null) {
            return;
        }

        showTrunks(p.prev);
        System.out.print(p.str);
    }

    void printTree(Node root, Trunk prev, boolean isLeft) {
        if (root == null) {
            return;
        }

        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);

        printTree(root.getRightChild(), trunk, true);

        if (prev == null) {
            trunk.str = "---";
        } else if (isLeft) {
            trunk.str = ".---";
            prev_str = "   |";
        } else {
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
            s = dataByLevel(r.getLeftChild(), selectedlvl, lvl + 1, s);
            if (selectedlvl == lvl) {
                s += r.getData();
                return s;
            }
            ;
            s += dataByLevel(r.getRightChild(), selectedlvl, lvl + 1, s);
        } else {
            return "";
        }
        return s;
    }

    // [=================== Getters and Setters ===================]

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}