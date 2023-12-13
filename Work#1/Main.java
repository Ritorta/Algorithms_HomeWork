// Урок 2. Структуры данных. Массивы. Алгоритмы массивов.

// Реализовать алгоритм пирамидальной сортировки (сортировка кучей).

import java.util.Random;

public class Main 
{
    void compareElementRoot(int array[], int n, int i)
    {
        int root = i; // наибольший элемент в корне
        int l = 2 * i + 1; // левый элемент в корне
        int r = 2 * i + 2; // правый элемент в корне

        if(l < n && array[l] > array[root]) // Если левый элемет больше корня
        {
            root = l;
        }

        if(r < n && array[r] > array[root]) // Если правый элемент больще корня
        {
            root = r;
        }

        if(root != i) // Если корень не самый большой элемент
        {
            int temp = array[i];
            array[i] = array[root];
            array[root] = temp;

            compareElementRoot(array, n, root); // Рекурсия в двойную кучу

        }
    }
    
    public void heapSort(int array[])
    {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) // Постороение кучи 
        {
            compareElementRoot(array, n, i);
        }

        for(int i = n - 1; i >= 0; i--) // Извлечение элементов кучи
        {
            // Переставляем отсортированный элемент массива в конец
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            compareElementRoot(array, i, 0); // начинаем сортировку оставшегося массива

        }
    }

    static void printArray(int array[])
    {
        int n = array.length;
        for(int i = 0; i < n; i++)
        {
            System.out.print(array[i] + " ");

        }
    }

    public static void main(String[] args) 
    {
        Random rand = new Random();
        int array[] = new int[30];

        System.out.print("Insorted array: ");

        for (int i = 0; i < array.length; i++) 
        {
            array[i] = rand.nextInt(100); 
            System.out.print(array[i] + " ");    
        } 
        
        Main run = new Main();
        run.heapSort(array);

        System.out.println();
        System.out.print("Sorted array: ");
        printArray(array);

    }
}