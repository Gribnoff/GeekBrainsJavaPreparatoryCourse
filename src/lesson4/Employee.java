package lesson4;

class Employee {
    String fullName;
    String position;
    String email;
    String phone;
    int salary;
    int age;

    public Employee(String fullName, String position, String email, String phone, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void info() {
        System.out.printf("%s, %d лет\nДолжность: %s(з/п %d)\nКонтакты: email %s телефон %s\n\n",
                fullName, age, position, salary, email, phone);
    }
}
