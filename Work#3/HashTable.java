public class HashTable<K, V> 
{
    private static final int INIT_BASCET_COUNT = 16;
    
    private Basket[] baskets;
    public HashTable(int initSize) 
    {
        this.baskets = (Basket[])new Object[initSize];
    }

    public HashTable()
    {
        this(INIT_BASCET_COUNT);
    }

    private int calcilateBasketIndex(K key)
    {
        return key.hashCode() % baskets.length;
    }

    public V get(K key)
    {
        int index = calcilateBasketIndex(key);
        Basket basket = baskets[index];
        if(basket != null)
        {
            return basket.get(key);
        }
        return null;
    }

    private class Entity
    {
        private K key;
        private V value;
    }

    private class Basket
    {
        private Node head;
        private class Node
        {
            private Node next;
            private Entity value;
        }

        public V get(K key)
        {
            Node node = head;
            while(node != null)
            {
                if(node.value.key.equals(key))
                {
                    return node.value.value;
                }
                node = node.next;
            }
            return null;
        }
    }
    
}
