// public class RedBlackTree {

//     private final Node EMPTY = new Node(0);
//     private Node header;

//     public class Node {
//         int value;
//         Node left;
//         Node right;
//         Color color;

//         public Node(int value) {
//             this.value = value;
//             this.left = EMPTY;
//             this.right = EMPTY;
//             this.color = Color.RED;
//         }
//     }

//     public enum Color {
//         RED, BLACK;
//     }

//     protected Node current;
//     private Node parent;
//     private Node grand;
//     private Node great;

//     public RedBlackTree() {
//         header = new Node(Integer.MIN_VALUE);
//         header.left = EMPTY;
//         header.right = EMPTY;

//         // RedBlackTreeV3 tree = new RedBlackTreeV3(); // Создание экземпляра класса
//         // // Вставка элементов в дерево
//         // tree.insert(5);
//         // tree.insert(3);
//         // tree.insert(8);
//         // // Проверка, содержит ли дерево значение true/false
//         // System.out.println(tree.contains(4));
//         // System.out.println(tree.contains(2));
//     }

//     public void insert(int item) {

//         current = parent = grand = header;
//         Node newItem = new Node(item);

//         while (current != EMPTY) {
//             great = grand;
//             grand = parent;
//             parent = current;
//             current = newItem.value > current.value ? current.right : current.left;

//             if (current.left.color == Color.RED && current.right.color == Color.RED) {
//                 reorient(newItem);
//             }
//         }

//         current = newItem;
//         if (newItem.value < parent.value) {
//             parent.left = current;
//         } else {
//             parent.right = current;
//         }
//         reorient(newItem);

//         // while (current.value != newItem.value) {
//         //     great = grand;
//         //     grand = parent;
//         //     parent = current;
//         //     current = newItem.value > current.value ? current.right : current.left;

//         //     if (current.left.color == Color.RED && current.right.color == Color.RED) {
//         //         reorient(newItem);
//         //     }
//         // }

//         // if (current != EMPTY) {
//         //     return;
//         // }

//         // current = newItem;
//         // if (newItem.value < parent.value) {
//         //     parent.left = current;
//         // } else {
//         //     parent.right = current;
//         // }
//         // reorient(newItem);
//     }

//     protected void reorient(Node item) {

//         current.color = Color.RED;
//         current.left.color = Color.BLACK;
//         current.right.color = Color.BLACK;

//         if (parent.color == Color.RED) {
//             grand.color = Color.RED;

//             if (item.value < grand.value != item.value < parent.value) {
//                 parent = rotate(item.value, grand);
//             }
//             rotate(item.value, great);
//             // current = rotate(item.value, great);
//             // current.color = Color.BLACK;
//         }

//         header.right.color = Color.BLACK;
//     }

//     private Node rotate(int item, Node parent) {
//         if (item < parent.value) {

//             Node node = parent.left;

//             if (node.right.color == Color.RED) {
//                 node = rotateWithRightNode(node);
//             }
//             if (node.left.color == Color.RED) {
//                 rotateWithLeftNode(node);
//             }
//             parent.left = node;
//             return node;
//         } else {
//             Node node = parent.right;
//             if (node.left.color == Color.RED) {
//                 node = rotateWithLeftNode(node);
//             }
//             if (node.right.color == Color.RED) {
//                 rotateWithRightNode(node);
//             }
//             parent.right = node;
//             return node;
//         }
//     }

//     private Node rotateWithLeftNode(Node node) {
//         Node left = node.left;
//         node.left = left.right;
//         left.right = node;
//         return left;
//     }

//     private Node rotateWithRightNode(Node node) {
//         Node right = node.right;
//         node.right = right.left;
//         right.left = node;
//         return right;
//     }

//     public boolean contains(int value) {
//         Node current = header.right;
//         while (current != EMPTY) {
//             if (value == current.value) {
//                 return true;
//             } else if (current.left != EMPTY && value < current.value) {
//                 current = current.left;
//             } else if (current.right != EMPTY && value > current.value) {
//                 current = current.right;
//             } else {
//                 return false;
//             }
//         }
//         return false;
//     }

// }

public class RedBlackTree<K extends Comparable<K>, V> {

    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        K key;
        V value;
        Node left, right;
        boolean color;

        public Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private Node put(Node h, K key, V value) {
        if (h == null) {
            return new Node(key, value, RED);
        }

        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, value);
        } else if (cmp > 0) {
            h.right = put(h.right, key, value);
        } else {
            h.value = value;
        }

        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        return h;
    }

    private boolean isRed(Node x) {
        if (x == null)
            return false;
        return x.color == RED;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    public V get(K key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x.value;
        }
        return null;
    }
}
