package lesson4;

class Main {
    public static void main(String[] args) {
        Employee[] staff = new Employee[5];
        staff[0] = new Employee("Петров Петр Петрович",
                "Разработчик", "petrovpp@company.ru",
                "8-999-123-45-67", 30000, 30);
        staff[1] = new Employee("Иванов Иван Иванович",
                "Инженер", "ivanovii@company.ru",
                "8-999-111-22-33", 40000, 50);
        staff[2] = new Employee("Васильев Василий Васильевич",
                "Оператор", "vasilievvv@company.ru",
                "8-999-444-55-66", 25000, 25);
        staff[3] = new Employee("Сидоров Сидор Сидорович",
                "Администратор", "sidorovss@company.ru",
                "8-999-777-88-99", 65000, 45);
        staff[4] = new Employee("Андреев Андрей Андреевич",
                "Курьер", "andreevaa@company.ru",
                "8-999-987-65-43", 15000, 20);

        for (Employee employee : staff) {
            if (employee.age >= 40)
                employee.info();
        }


        Cat murzik = new Cat("Мурзик");

        murzik.run(200);
        murzik.jump(2);
        murzik.swim(10);

        Dog barbos = new Dog("Барбос");
        barbos.run(500);
        barbos.jump(0.5);
        barbos.swim(10);
    }
}
