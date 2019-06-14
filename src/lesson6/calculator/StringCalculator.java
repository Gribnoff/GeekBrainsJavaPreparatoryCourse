package lesson6.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class StringCalculator extends Calculator {

    StringCalculator() {
        super("Строчный Калькулятор");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(500, 400, 400, 100);

        JPanel data = new JPanel();
        data.setLayout(new BoxLayout(data, BoxLayout.X_AXIS));
        data.setPreferredSize(new Dimension(1, 50));
        data.setBounds(0, 0, 300, 50);
        data.setMinimumSize(new Dimension(300, 50));
        add(data,BorderLayout.CENTER);

        JPanel actions = new JPanel();
        actions.setPreferredSize(new Dimension(1, 50));
        add(actions,BorderLayout.SOUTH);

        JTextField firstNumField = new CalcTextField();
        JTextField secondNumField = new CalcTextField();

        JLabel actionLabel = new CalcLabel(" ");
        actionLabel.setPreferredSize(new Dimension(30, 27));
        JLabel resultLabel = new CalcLabel(" ");

        JButton getResult = new CalcButton("=");
        ActionListener getResultListener = e -> {
            if (firstNumField.getText().length() == 0 || firstNumField.getText() == null || secondNumField.getText().length() == 0 || secondNumField.getText() == null)
                resultLabel.setText("NaN");
            else if (actionLabel.getText() == null || actionLabel.getText().equals(" "))
                resultLabel.setText("Не выбрано действие");
            else {
                Double result = StringCalculator.doAction(actionLabel.getText(), Double.parseDouble(firstNumField.getText()), Double.parseDouble(secondNumField.getText()));
                if (result - result.intValue() != 0)
//                resultLabel.setText(Double.toString(result));
                    resultLabel.setText(String.format("%.4f", result));
                else
                    resultLabel.setText(Integer.toString(result.intValue()));
            }
        };
        getResult.addActionListener(getResultListener);

        data.add(firstNumField);
        data.add(actionLabel);
        data.add(secondNumField);
        data.add(getResult);
        data.add(resultLabel);

        ActionListener actionButtonListener = e -> actionLabel.setText(e.getActionCommand());
        JButton plus = new CalcButton("+");
        JButton minus = new CalcButton("-");
        JButton mult = new CalcButton("*");
        JButton div = new CalcButton("/");
        JButton pow = new CalcButton("^");
        plus.addActionListener(actionButtonListener);
        minus.addActionListener(actionButtonListener);
        mult.addActionListener(actionButtonListener);
        div.addActionListener(actionButtonListener);
        pow.addActionListener(actionButtonListener);

        actions.add(plus);
        actions.add(minus);
        actions.add(mult);
        actions.add(div);
        actions.add(pow);
        setVisible(true);
    }
}

class CalcButton extends JButton{
    CalcButton(String text) {
        super(text);
    }
}

class CalcTextField extends JTextField {
    CalcTextField() {
        super();
        setPreferredSize(new Dimension(50, 27));
        setMaximumSize(new Dimension(150, 27));
        setHorizontalAlignment(RIGHT);
    }
}

class CalcLabel extends JLabel {
    CalcLabel(String text) {
        super(text);
        setPreferredSize(new Dimension(150, 27));
        setHorizontalAlignment(CENTER);
    }
}
