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
            if (c[0] == d) continue;
            addElement(d);
            balanceTree(root);
        }
    }

    // [=================== Methods ===================]
  
    //  --- AVL --- 
    
    public void balanceTree(Node r) {
        if (r == null) return;

        balanceTree(r.getLeftChild());
        balanceTree(r.getRightChild());
        int rBalanceFactor = balanceFactor(r);
        if (rBalanceFactor == 2 || rBalanceFactor == -2) {
            if (getHeight(r.getLeftChild()) > getHeight(r.getRightChild())) {
                if (getHeight(r.getLeftChild().getRightChild()) > getHeight(r.getLeftChild().getLeftChild())) {
                    avlAlgoritmSelection(r, r.getLeftChild(), r.getLeftChild().getRightChild());
                } else {
                    avlAlgoritmSelection(r, r.getLeftChild(), r.getLeftChild().getLeftChild());
                }
            } else {
                if (getHeight(r.getRightChild().getRightChild()) > getHeight(r.getRightChild().getLeftChild())) {
                    avlAlgoritmSelection(r, r.getRightChild(), r.getRightChild().getRightChild());
                } else {
                    avlAlgoritmSelection(r, r.getRightChild(), r.getRightChild().getLeftChild());
                }
            }
        }
    }

    private int balanceFactor(Node r) {
        return getHeight(r.getLeftChild()) - getHeight(r.getRightChild());
    }

    // avl algoritm selection

    private void avlAlgoritmSelection(Node q, Node r, Node s) {
        int qBalanceFactor = balanceFactor(q), rBalanceFactor = balanceFactor(r);
        if (qBalanceFactor == 2 && rBalanceFactor == 1) {
            rotationRight(q, r);
        } else if (qBalanceFactor == -2 && rBalanceFactor == -1) {
            rotationLeft(q, r);
        } else if (qBalanceFactor == -2 && rBalanceFactor == 1) {
            doubleRotationLeft(q, r, s);
        } else if (qBalanceFactor == 2 && rBalanceFactor == -1) {
            doubleRotationRight(q, r, s);
        }
    }

    // avl rotations

    private void rotationLeft(Node q, Node r) {
        if (q != null && r != null) {
            Node subTreeRoot = searchParent(q);
            Node s = r.getLeftChild();
            if (subTreeRoot != null) {
                if (subTreeRoot.getRightChild() == q) {
                    subTreeRoot.setRightChild(r);
                } else subTreeRoot.setLeftChild(r);
            } else root = r;
            
            r.setLeftChild(q);
            q.setRightChild(s);
        }
    }

    private void rotationRight(Node q, Node r) {
        if (q != null && r != null) {
            Node subTreeRoot = searchParent(q);
            Node s = r.getRightChild();

            if (subTreeRoot != null) {
                if (subTreeRoot.getRightChild() == q) {
                    subTreeRoot.setRightChild(r);
                } else subTreeRoot.setLeftChild(r); 
                
            } else root = r;
            r.setRightChild(q);
            q.setLeftChild(s);

        }
    }

    private void doubleRotationLeft(Node q, Node r, Node s) { // p q r

        if (q != null && r != null && s != null) {
            Node subTreeRoot = searchParent(q);
            if  (subTreeRoot != null ) {
                if (subTreeRoot.getLeftChild() == q) {
                    subTreeRoot.setLeftChild(s);
                } else subTreeRoot.setRightChild(s);
            } else root = s;
            q.setRightChild(s.getLeftChild());
            s.setLeftChild(q);
            r.setLeftChild(s.getRightChild());
            s.setRightChild(r);
        }

    }

    public void doubleRotationRight(Node q, Node r, Node s) {
        if (q != null && r != null && s != null) {
            Node subTreeRoot = searchParent(q);

            if (subTreeRoot != null) {
                if (subTreeRoot.getLeftChild() == q) {
                    subTreeRoot.setLeftChild(s);
                } else
                    subTreeRoot.setRightChild(s);
            } else
                root = s;

            q.setLeftChild(s.getRightChild());
            s.setRightChild(q);
            r.setRightChild(s.getLeftChild());
            s.setLeftChild(r);
        }
    }
    
    // insert
    public void addElement(char c) { addElement(root, c); }
    private void addElement(Node r, char c) {
        if (r != null) {
            if (r.getData() > c) {
                if (r.getLeftChild() != null) {
                    addElement(r.getLeftChild(), c);
                } else {
                    r.setLeftChild(new Node(c));
                    return;
                }
            } else if (r.getData() < c) {
                if (r.getRightChild() != null) {
                    addElement(r.getRightChild(), c);
                } else {
                    r.setRightChild(new Node(c));
                    return;
                }
            } else if (r.getData() == c) return;
        }
    }

    // count methods

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

    // special getters

    public char getBrother(Node b) { return getBrother(root, b.getData(), '\0'); }
    public char getBrother(char b) { return getBrother(root, b, '\0'); }
    private char getBrother(Node r, char b, char s) {

        if ( r != null) {
            if (r.getLeftChild() != null && r.getLeftChild().getData() == b) {
                return r.getRightChild() != null ? r.getRightChild().getData() : 0;
            } else if (r.getRightChild() != null && r.getRightChild().getData() == b) {
                return r.getLeftChild() != null ? r.getLeftChild().getData() : 0 ;
            }

            if (r.getData() > b) {
                s = getBrother(r.getLeftChild(), b, s);
            } else if (r.getData() < b) {
                s = getBrother(r.getRightChild(), b, s);
            }

            return s;
        }

        return '\0';
    }

    public int getLevel(Node c) {
        return getLevel(root, root.getData(), c.getData(), 1);
    }
    public int getLevel(char data) {
        return getLevel(root, root.getData(), data, 1);
    }
    private int getLevel(Node r, char nodeData, char data, int lvl) {

        if (r != null) {
            if (r.getData() == data) {
                return lvl;
            } else if (r.getData() > data) {
                return getLevel(r.getLeftChild(), nodeData, data, lvl+1);
            } else if (r.getData() < data) {
                return getLevel(r.getRightChild(), nodeData, data, lvl+1);
            }
        }
        return 0;
    }

    public int getHeight(Node r) {
        if (r != null) {
            int leftHeigth = getHeight(r.getLeftChild()) + 1;
            int rightHeigth = getHeight(r.getRightChild()) + 1;
            if (rightHeigth > leftHeigth) {
                return rightHeigth;
            } else return leftHeigth;
        }
        return 0;
    }

    public ArrayList<Node> showAncestors() {

        return null;
    }

    // Read Methods

    public String inorder() { return inorder(root, ""); }
    private String inorder(Node r, String s) { // i - r - d
        if (r != null) {
            s = inorder(r.getLeftChild(), s);
            s += r.getData();
            s += inorder(r.getRightChild(), s);
            return s;
        }
        return "";
    }

    public String postorder() { return postorder(root, ""); }
    private String postorder(Node r, String s) { // i - d - r
        if (r != null) {
            s = postorder(r.getLeftChild(), s);
            s += postorder(r.getRightChild(), s);
            s += r.getData();
            return s;
        }
        return "";

    }

    public String preorder() { return preorder(root, ""); }
    private String preorder(Node r, String s) { // r - i - d
        if (r != null) {
            s = ""+r.getData();
            s += preorder(r.getLeftChild(), s);
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
    private String dataByLevel(Node r, int selectedlvl, int lvl, String s) {
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

    // searching
    
    public Node search(Node c) { return search(root, c.getData()); }
    public Node search(char c) { return search(root, c); }
    private Node search(Node r, char c) {
        if (r != null) {
            if (r.getData() == c) {
                return r;
            } else if (r.getData() > c) {
                return search(r.getLeftChild(), c);
            } else if (r.getData() < c) {
                return search(r.getRightChild(), c);
            }
        }
        return null;
    }

    public Node searchParent(Node c) { return searchParent(root, c.getData()); }
    public Node searchParent(char c) { return searchParent(root, c); }
    private Node searchParent(Node r, char c) {
        if (r != null) {
            if (r.getRightChild() != null && r.getRightChild().getData() == c) {
                return r;
            } else if (r.getLeftChild() != null && r.getLeftChild().getData() == c) { 
                return r;
            } else if (r.getData() > c) {
                return searchParent(r.getLeftChild(), c);
            } else if (r.getData() < c) {
                return searchParent(r.getRightChild(), c);
            }
        }
        return null;
    }

    // [=================== Getters and Setters ===================]

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}