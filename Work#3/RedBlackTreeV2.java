public class RedBlackTreeV2 {

    boolean contains(int value) {
        Node current = root;
        while (current != nil) {
            if (value == current.value) {
                return true;
            } else if (current.left != nil && value < current.value) {
                current = current.left;
            } else if (current.right != nil && value > current.value) {
                current = current.right;
            } else {
                return false;
            }
        }
        return false;
    }
    
    class Node {
        int key;
        int value;
        Node left = null;
        Node right = null;
        Node parent = null;
        boolean color = BLACK;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.left = nil;
            this.right = nil;
            this.parent = nil;
            this.color = RED;
        }
    }

    Node root = null;
    Node nil = new Node(0, 0);
    static final boolean RED = true;
    static final boolean BLACK = false;

    void insert(int key) {
        Node current = root;
        Node parent = null;

        while (current != nil) {
            parent = current;
            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        Node newNode = new Node(key, key);
        newNode.parent = parent;

        if (parent == null) {
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        balanceTree(newNode);
    }

    void balanceTree(Node newNode) {
        while (newNode.parent.color == RED) {
            if (newNode.parent == newNode.parent.parent.left) {
                Node uncle = newNode.parent.parent.right;
                if (uncle.color == RED) {
                    newNode.parent.color = BLACK;
                    uncle.color = BLACK;
                    newNode.parent.parent.color = RED;
                    newNode = newNode.parent.parent;
                } else {
                    if (newNode == newNode.parent.right) {
                        newNode = newNode.parent;
                        leftRotate(newNode);
                    }
                    newNode.parent.color = BLACK;
                    newNode.parent.parent.color = RED;
                    rightRotate(newNode.parent.parent);
                }
            } else {
                Node uncle = newNode.parent.parent.left;
                if (uncle.color == RED) {
                    newNode.parent.color = BLACK;
                    uncle.color = BLACK;
                    newNode.parent.parent.color = RED;
                    newNode = newNode.parent.parent;
                } else {
                    if (newNode == newNode.parent.left) {
                        newNode = newNode.parent;
                        rightRotate(newNode);
                    }
                    newNode.parent.color = BLACK;
                    newNode.parent.parent.color = RED;
                    leftRotate(newNode.parent.parent);
                }
            }
        }
        root.color = BLACK;
    }

    void leftRotate(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        if (temp.left != nil) {
            temp.left.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == null) {
            root = temp;
        } else if (node == node.parent.left) {
            node.parent.left = temp;
        } else {
            node.parent.right = temp;
        }
        temp.left = node;
        node.parent = temp;
    }

    void rightRotate(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        if (temp.right != nil) {
            temp.right.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == null) {
            root = temp;
        } else if (node == node.parent.right) {
            node.parent.right = temp;
        } else {
            node.parent.left = temp;
        }
        temp.right = node;
        node.parent = temp;
    }
}