// Урок 3. Структуры данных. Связный список.
// Автотест
// В классе Answer реализуйте метод reverse, который принимал бы на вход односвязный список и разворачивал бы его.


// Вариант №1 - Реализация через цикл While

// class Node {
//     int val;
//     Node next;

//     public Node(int val) {
//         this.val = val;
//     }
// }

// class Answer {
//     Node head;
//     public void reverse() {
//     // Введите свое решение ниже
    
//     Node previousNode = null;
//     Node currentNode = head;
//     Node nextNode = null;
      
//         while(currentNode != null)
//         {
//             nextNode = currentNode.next;
//             currentNode.next = previousNode;
//             previousNode = currentNode;
//             currentNode = nextNode; 
//         }
//         head = previousNode;

//     }
// }

// public class Main {
//     public static void main(String[] args) {
//         // Создаем связанный список 1 -> 2 -> 3 -> 4
//         Node head;
//         if (args.length == 0) {
//             head = new Node(1);
//             head.next = new Node(2);
//             head.next.next = new Node(3);
//             head.next.next.next = new Node(4);
//         } else {
//             head = new Node(Integer.parseInt(args[0]));
//             head.next = new Node(Integer.parseInt(args[1]));
//             head.next.next = new Node(Integer.parseInt(args[2]));
//             head.next.next.next = new Node(Integer.parseInt(args[3]));
//         }

//         // Сохраняем голову списка в поле класса Answer
//         Answer ans = new Answer();
//         ans.head = head;
//         // Инвертируем порядок элементов списка
//         ans.reverse();

//         // Выводим инвертированный порядок элементов списка
//         Node current = ans.head;
//         while (current != null) {
//             System.out.print(current.val + " ");
//             current = current.next;
//         }
//     }
// }


// Вариант №2 - Рекурсия

// class Node {
//     int val;
//     Node next;

//     public Node(int val) {
//         this.val = val;
//     }
// }


// class Answer {
//     Node head;

//     public Node reverse(Node node) {
//         if (node == null || node.next == null) 
//         {
//             head = node;
//             return node;
//         }
//         Node rest = reverse(node.next);
//         node.next.next = node;
//         node.next = null;

//         return rest;
//     }
    
// }


// public class Main {
//     public static void main(String[] args) {
//         // Создаем связанный список 1 -> 2 -> 3 -> 4
//         Node head;
//         if (args.length == 0) {
//             head = new Node(1);
//             head.next = new Node(2);
//             head.next.next = new Node(3);
//             head.next.next.next = new Node(4);
//         } else {
//             head = new Node(Integer.parseInt(args[0]));
//             head.next = new Node(Integer.parseInt(args[1]));
//             head.next.next = new Node(Integer.parseInt(args[2]));
//             head.next.next.next = new Node(Integer.parseInt(args[3]));
//         }

//         // Сохраняем голову списка в поле класса Answer
//         Answer ans = new Answer();
//         ans.head = head;
//         // Инвертируем порядок элементов списка
//         ans.reverse(head);

//         // Выводим инвертированный порядок элементов списка
//         Node current = ans.head;
//         while (current != null) {
//             System.out.print(current.val + " ");
//             current = current.next;
//         }
//     }
// }

// Вариант № 3 - Решение бота с сайта (идеальное решение)

class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }
}

class Answer {
    Node head;

    public void reverse() {
        if (head != null && head.next != null) {
            reverse(head, head.next);
        }
    }

    private void reverse(Node current, Node next) {
        if (next.next != null) {
            reverse(next, next.next);
        } else {
            head = next;
        }

        next.next = current;
        current.next = null;
    }
}

public class Main {

    public static void main(String[] args) {
        // Создаем список для тестирования
        Node head;
        if (args.length == 0) {
            head = new Node(1);
            head.next = new Node(2);
            head.next.next = new Node(3);
            head.next.next.next = new Node(4);
        } else {
            head = new Node(Integer.parseInt(args[0]));
            head.next = new Node(Integer.parseInt(args[1]));
            head.next.next = new Node(Integer.parseInt(args[2]));
            head.next.next.next = new Node(Integer.parseInt(args[3]));
        }

        // Разворачиваем список
        Answer ans = new Answer();
        ans.head = head;
        ans.reverse();

        // Выводим результат
        Node current = ans.head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }
}