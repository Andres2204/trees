public class Node {
    // [=================== Attributes ===================]

    private char data;
    private Node leftChild, rightChild;

    // [=================== Constructors ===================]

    public Node() {
        data = '0';
        leftChild = null;
        rightChild = null;
    }

    public Node(char c) {
        data = c;
        leftChild = null;
        rightChild = null;
    }

    // [=================== Attributes ===================]

    public char getData() {
        return data;
    }
    public void setData(char data) {
        this.data = data;
    }
    public Node getLeftChild() {
        return leftChild;
    }
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }
    public Node getRightChild() {
        return rightChild;
    }
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }   
}