package lesson4;

abstract class Animal {
    String type;
    String name;
    int maxRunDist;
    int maxSwimDist;
    double maxJumpHeight;

    public Animal(String type, String name, int maxRunDist, int maxSwimDist, double maxJumpHeight) {
        this.type = type;
        this.name = name;
        this.maxRunDist = maxRunDist;
        this.maxSwimDist = maxSwimDist;
        this.maxJumpHeight = maxJumpHeight;
        System.out.printf("Создан %s %s с параметрами: бег(до %dм), прыжок(до %fм), заплыв(до %dм)\n", type, name, maxRunDist, maxJumpHeight, maxSwimDist);
    }

    void run(int dist) {
        if (maxRunDist < dist)
            System.out.printf("%s %s не справился с забегом на %d метров\n", type, name, dist);
        else
            System.out.printf("%s %s отлично справился с забегом на %d метров!\n", type, name, dist);
    }

    void swim(int dist) {
        if (maxSwimDist == 0) {
            System.out.printf("%s %s не умеет плавать!\n", type, name);
            return;
        }
        if (maxSwimDist < dist)
            System.out.printf("%s %s не справился с заплывом на %d метров\n", type, name, dist);
        else
            System.out.printf("%s %s отлично справился с заплывом на %d метров!\n", type, name, dist);
    }

    void jump(double height) {
        if (maxJumpHeight < height)
            System.out.printf("%s %s не справился с прыжком на высоту %s метров\n", type, name, height);
        else
            System.out.printf("%s %s отлично справился с прыжком на высоту %s метров!\n", type, name, height);
    };
}
