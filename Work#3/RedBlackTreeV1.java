public class RedBlackTreeV1 <V extends Comparable> 
{
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;
    private class Node 
    {
        private V value;
        private Node left;
        private Node right;
        private boolean color;
    }
                
    public boolean contains(V value) 
    {
        Node node = root;
        while (node != null) 
        {
            if (node.value.equals(value)) 
            {
                return true;
            }
            if (node.value.compareTo(value) > 0) 
            {
                node = node.left;
            } 
            else 
            {
                node = node.right;
            }
        }
        return false;
    }

    public void insert(V value) 
    {
        root = insert(root, value);
        root.color = BLACK;
    }
            
    private boolean isRed(Node x) 
    {
        if (x == null) 
        {
            return false;
        }
        return x.color == RED;
    }
             
    private Node insert(Node h, V value) 
    {
        if (h == null) 
        {
            Node node = new Node();
            node.value = value;
            node.color = RED;
            return node;
        }
            
        int cmp = value.compareTo(h.value);
        if (cmp < 0) 
        {
            h.left = insert(h.left, value);
        } 
        if (cmp > 0) 
        {
            h.right = insert(h.right, value);
        } 
      
            
        if (isRed(h.right) && !isRed(h.left)) 
        {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) 
        {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) 
        {
            flipColors(h);
        }
            return h;
    }        
            
    private Node rotateLeft(Node h) 
    {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
            
    private Node rotateRight(Node h) 
    {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
            
    private void flipColors(Node h) 
    {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
            
}
