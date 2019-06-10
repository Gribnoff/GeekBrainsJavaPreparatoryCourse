package lesson5;

class Cat{
    private String name;
    private int stomach;
    boolean isFull;

    Cat(String name, int stomach) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Кот " + name;
    }
}
