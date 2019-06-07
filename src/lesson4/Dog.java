package lesson4;

class Dog extends Animal {
    private static int averageRunDist = 500;
    private static double averageJumpHeight = 0.5;
    private static int averageSwimDist = 10;

    public Dog(String name) {
        super("Пёс", name, (averageRunDist / 2) + (int)(Math.random() * averageRunDist), (averageSwimDist / 2) + (int)(Math.random() * averageSwimDist), (averageJumpHeight / 2) + (Math.random() * averageJumpHeight));
    }
}
