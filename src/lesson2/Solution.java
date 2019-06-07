package lesson2;

class Solution {
    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private static void print2dArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //Задание №1
        System.out.println("Задание №1");
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        printArray(array);

        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] == 0) ? 1 : 0;
        }
        printArray(array);

        //Задание №2
        System.out.println("Задание №2");
        int[] array2 = new int[8];

        for (int i = 0; i < array2.length; i++) {
            array2[i] = i * 3;
        }
        printArray(array2);

        //Задание №3
        System.out.println("Задание №3");
        int[] array3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray(array3);

        for (int i = 0; i < array3.length; i++) {
            if (array3[i] < 6)
                array3[i] *= 2;
        }
        printArray(array3);

        //Задание №4
        System.out.println("Задание №4");
        int arraySize = 7;
        int[][] array4 = new int[arraySize][arraySize];

        for (int i = 0; i < array4.length; i++) {
            for (int j = 0; j < array4.length; j++) {
                if ((i == j) || (i == array4.length - j - 1))
                    array4[i][j] = 1;
            }
        }
        print2dArray(array4);

        //Задание №5
        System.out.println("Задание №5");
        int[] array5 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray(array5);
        int min = array5[0];
        int max = array5[0];
        for (int i = 1; i < array5.length; i++) {
            if (array5[i] < min)
                min = array5[i];
            if (array5[i] > max)
                max = array5[i];
        }
        System.out.printf("min = %d, max = %d\n", min, max);


        System.out.println("Задание №6");
        System.out.println(isBalanced(new int[] {1, 1, 1, 2, 1}));
        System.out.println(isBalanced(new int[] {2, 1, 1, 2, 1}));
        System.out.println(isBalanced(new int[] {10, 10}));

        System.out.println("Задание №7");
        printArray(array5);
        moveNumbers(array5, -2);
        printArray(array5);
    }

    //Задание №6
    private static boolean isBalanced(int[] array) {
        if (array == null || array.length == 0) //проверка на пустоту
            return false;

        int sumLeft;
        int sumRight;
        for (int i = 0; i < array.length - 1; i++) {
            sumLeft = 0;
            sumRight = 0;
            for (int j = 0; j < array.length; j++) {
                if (j <= i)
                    sumLeft += array[j];
                else
                    sumRight += array[j];
            }
            if (sumLeft == sumRight)
                return true;
        }
        return false;
    }

    //Задание №7
    private static void moveNumbers(int[] array, int pos) {
        if (pos == 0)
            return;
        else if (pos < 0)
            for (int i = 0; i < -pos; i++) {
                int buf = array[0];
                for (int j = 1; j < array.length; j++) {
                        array[j - 1] = array[j];
                }
                array[array.length - 1] = buf;
            }
        else if (pos > 0)
            for (int i = 0; i < pos; i++) {
                int buf = array[array.length - 1];
                for (int j = array.length - 1; j > 0; j--) {
                    array[j] = array[j - 1];
                }
                array[0] = buf;
            }
    }
}
