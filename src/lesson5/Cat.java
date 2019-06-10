package lesson5;

class Cat extends Animal {
    private int stomach;
    boolean isFull;

    Cat(String name, int stomach) {
        super("Кот", name);
        this.stomach = stomach;
        isFull = false;
    }

    void eat(Bowl bowl) {
        System.out.println(toString() + " начинает есть.");

        if (bowl.capacity < stomach) {
            System.out.println("Эта миска слишком мала для " + toString() + "!\n");
            return;
        }

        bowl.takeFood(stomach);
        System.out.println(toString() + " поел.\n");
        isFull = true;
    }

    private static int averageRunDist = 200;
    private static int averageJumpHeight = 2;

    public Cat(String name) {
        super("Кот", name, (averageRunDist / 2) + (int)(Math.random() * averageRunDist), 0, (averageJumpHeight / 2) + (Math.random() * averageJumpHeight));
    }
}
