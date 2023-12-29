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

    public boolean put(K key, V value)
    {
        int index = calcilateBasketIndex(key);
        Basket basket = baskets[index];
        if(basket == null)
        {
            basket = new Basket();
            baskets[index] = basket;
        }
        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;
        return basket.add(entity);
    }

    public boolean remove(K key)
    {
        int index = calcilateBasketIndex(key);
        Basket basket = baskets[index];
        return basket.remove(key);
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

        public boolean remove(K key)
        {
            if(head !=null)
            {
                if(head.value.key.equals(key))
                {
                    head = head.next;
                }
                else
                {
                    Node node = head;
                    while(node != null)
                    {
                        if(node.next.value.key.equals(key))
                        {
                            node.next = node.next.next;   
                            return true;     
                        }       
                        node = node.next;
                    }
                }
            }
            return false;
        }
        
        public boolean add(Entity entity)
        {
            Node node = new Node();
            node.value = entity;
            if(head != null)
            {
                Node current = head;
                while(true)
                {
                    if(current.value.key.equals(entity.key))
                    {
                        return false;
                    }
                    if(current.next == null)
                    {
                        current.next = node;
                        return true;
                    }
                    else
                    {
                        current = current.next;
                    }
                }
            }
            else
            {
                head = node;
                return true;
            }
        }
    }
    
}
