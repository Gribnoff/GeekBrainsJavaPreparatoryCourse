package lesson5;

public class CatFeeding {
    public static void main(String[] args) {
        Cat[] cats = {
                new Cat("Васька", 10),
                new Cat("Кузя", 15),
                new Cat("Семён", 5),
                new Cat("Шиш", 25),
                new Cat("Бублик", 20)};
        Bowl bowl = new Bowl(20);

        System.out.println("\nКоты начинают есть\n");
        for (Cat cat : cats) {
            cat.eat(bowl);
        }

        System.out.println();
        for (Cat cat : cats) {
            System.out.println(cat.toString() + " сыт? - " + cat.isFull);
        }
    }
}
