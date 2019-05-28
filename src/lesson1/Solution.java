package lesson1;

public class Solution {
    //пункт №1
    public static void main(String[] args) {
        //пункт №2
        byte b = 126;
        short sh = 255;
        char ch = 120;
        int i = 548;
        long l = 620L;
        float f = 1024.56f;
        double d = 2058.;
        boolean bool = false;
        String s = "This is string!";
    }

    //пункт №3
    static int method1(int a, int b, int c, int d) {
        return (a * (b + c / d));
    }

    //пункт №4
    static boolean method2(int a, int b) {
        return (a + b >= 10) && (a + b <= 20);
    }

    //пункт №5
    static void method3(int a) {
        if (a < 0)
            System.out.println("Отрицательное число");
        else
            System.out.println("Положительное число");
    }

    //пункт №6
    static boolean method4(int a) {
        return a < 0;
    }

    //пункт №7
    static void method5(String name) {
        System.out.printf("Привет, %s!\n", name);
    }

    //пункт №8
    static void method6(int year) {
        if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0))
            System.out.printf("%d - високосный год\n", year);
        else System.out.printf("%d - невисокосный год\n", year);
    }
}
