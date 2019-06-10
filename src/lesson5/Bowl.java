package lesson5;

public class Bowl {
    final int capacity;
    private int foodIn;

    Bowl(int capacity) {
        this.capacity = capacity;
        fill();
    }

    public int checkFood() {
        return foodIn;
    }

    void fill() {
        System.out.println("Наполняем миску.");
        foodIn = capacity;
        System.out.println("Миска наполнена до краёв.");
    }

    void takeFood(int quantity) {
        if (quantity > foodIn) {
            System.out.println("В миске мало еды!");
            fill();
        }

        foodIn -= quantity;
    }
}
