package lesson6.calculator;

import javax.swing.*;

abstract class Calculator extends JFrame {
    Calculator(String type){
        super(type);
    }

    static double doAction(String type, Double z, Double x) {
        Double result = 0.0;
        switch (type) {
            case "+": {
                result = addition(z, x);
                break;
            }
            case "-": {
                result = subtraction(z, x);
                break;
            }
            case "*": {
                result = multiplication(z, x);
                break;
            }
            case "/": {
                result = division(z, x);
                break;
            }
            case "^": {
                result = power(z, x);
                break;
            }
        }
        return result;
    }

    private static Double addition(Double z, Double x) {
        return z + x;
    }

    private static Double subtraction(Double z, Double x) {
        return z - x;
    }

    private static Double multiplication(Double z, Double x) {
        return z * x;
    }

    private static Double division(Double z, Double x) {
        return z / x;
    }

    private static Double power(Double z, Double x) {
        return Math.pow(z, x);
    }
}
