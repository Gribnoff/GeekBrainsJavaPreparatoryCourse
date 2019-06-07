package lesson4;

class Cat extends Animal {
    private static int averageRunDist = 200;
    private static int averageJumpHeight = 2;

    public Cat(String name) {
        super("Кот", name, (averageRunDist / 2) + (int)(Math.random() * averageRunDist), 0, (averageJumpHeight / 2) + (Math.random() * averageJumpHeight));
    }
}
