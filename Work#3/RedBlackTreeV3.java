import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class RedBlackTreeV3 {
private static final Node EMPTY = new Node(0);
private Node header;
    public class Node {
        int value;
        Node left;
        Node right;
        Color color;
    
        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = Color.RED;
        }
    }

    public enum Color {
        RED,
        BLACK;
    }
    
    static {
        EMPTY.left = EMPTY;
        EMPTY.right = EMPTY;
    }
    protected Node current;
    private Node parent;
    private Node grand;
    private Node great;
    

    public RedBlackTreeV3() {
        header = new Node(Integer.MIN_VALUE);
        header.left = EMPTY;
        header.right = EMPTY;
    }
    public void insert(int item) {
        current = parent = grand = header;

        Node newItem = new Node(item);

        while (current.value != newItem.value) {
            great = grand;
            grand = parent;
            parent = current;
            current = newItem.value > current.value ? current.right : current.left;

            if (current.left.color == Color.RED && current.right.color == Color.RED) {
                reorient(newItem);
            }
        }

        if (current != EMPTY) {
            return;
        }

        current = newItem;
        if (newItem.value < parent.value) {
            parent.left = current;
        } else {
            parent.right = current;
        }
        reorient(newItem);
    }

    protected void reorient(Node item) {
        current.color = Color.RED;
        current.left.color = Color.BLACK;
        current.right.color = Color.BLACK;

        if (parent.color == Color.RED) {
            grand.color = Color.RED;

            if (item.value < grand.value != item.value < parent.value) {
                parent = rotate(item.value, grand);
            }

            current = rotate(item.value, great);
            current.color = Color.BLACK;
        }

        header.right.color = Color.BLACK;
    }

    private Node rotate(int item, Node parent) {
        if (item < parent.value) {
            Node node = parent.left;
            if (node.right.color == Color.RED) {
                node = rotateWithRightNode(node);
            }
            if (node.left.color == Color.RED) {
                rotateWithLeftNode(node);
            }
            parent.left = node;
            return node;
        } else {
            Node node = parent.right;
            if (node.left.color == Color.RED) {
                node = rotateWithLeftNode(node);
            }
            if (node.right.color == Color.RED) {
                rotateWithRightNode(node);
            }
            parent.right = node;
            return node;
        }
    }

    private Node rotateWithLeftNode(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        return left;
    }

    private Node rotateWithRightNode(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        return right;
    }

    public boolean contains(int value) {
        Node current = header.right;
        while (current != EMPTY) {
            if (value == current.value) {
                return true;
            } else if (current.left != EMPTY && value < current.value) {
                current = current.left;
            } else if (current.right != EMPTY && value > current.value) {
                current = current.right;
            } else {
                return false;
            }
        }
        return false;
    }
}
