package lesson2;

public class Solution {
    public static void main(String[] args) {
        //Задание №1
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] == 0) ? 1 : 0;
        }

        //Задание №2
        int[] array2 = new int[8];

        for (int i = 0; i < array2.length; i++) {
            array2[i] = i * 3;
        }

        //Задание №3
        int[] array3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for (int i = 0; i < array3.length; i++) {
            if (array3[i] < 6)
                array3[i] *= 2;
        }
    }
}
