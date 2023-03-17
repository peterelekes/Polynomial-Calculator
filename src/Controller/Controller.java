package Controller;

import Model.Polynomial;
import View.Calc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private final Calc view;
    private Polynomial P;
    private Polynomial Q;
    Polynomial result=new Polynomial();
    private final Operation op = new Operation();

    JFrame frame = new JFrame("Polynomial Calculator");

    public void init() {
        frame.setContentPane(view.getMain());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(432, 463);
        frame.setVisible(true);
    }

    public Controller(Calc view) {
        this.view = view;
        view.AdditionButton(new AddButton());
        view.SubtractionButton(new SubtractButton());
        view.MultiplicationButton(new MultiplicateButton());
        view.DivisionButton(new DivideButton());
        view.DerivationButton(new DerivateButton());
        view.IntegrationButton(new IntegrateButton());
        view.ExButton(new ExitButton());
    }

    class AddButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getTextField1().getText().equals("") || view.getTextField2().getText().equals("")) {
                JOptionPane.showMessageDialog(view.getMain(), "Bad input format");
            } else {
                P = op.readPolynomial(view.getTextField1().getText());
                Q = op.readPolynomial(view.getTextField2().getText());
                if (P == null || Q == null) {
                    JOptionPane.showMessageDialog(view.getMain(), "Bad input format");
                } else {
                    result = op.addition(P, Q);
                    view.getResultLabel().setText(op.printPolynomial(result));
                }
            }
        }
    }

    class SubtractButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getTextField1().getText().equals("") || view.getTextField2().getText().equals("")) {
                JOptionPane.showMessageDialog(view.getMain(), "Bad input format");
            } else {
                P = op.readPolynomial(view.getTextField1().getText());
                Q = op.readPolynomial(view.getTextField2().getText());
                if (P == null || Q == null) {
                    JOptionPane.showMessageDialog(view.getMain(), "Bad input format");
                } else {
                    result = op.subtraction(P, Q);
                    view.getResultLabel().setText(op.printPolynomial(result));
                }
            }
        }
    }

    class MultiplicateButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Operation op = new Operation();
            if (view.getTextField1().getText().equals("") || view.getTextField2().getText().equals("")) {
                JOptionPane.showMessageDialog(view.getMain(), "Bad input format");
            } else {
                P = op.readPolynomial(view.getTextField1().getText());
                Q = op.readPolynomial(view.getTextField2().getText());
                if (P == null || Q == null) {
                    JOptionPane.showMessageDialog(view.getMain(), "Bad input format");
                } else {
                    result = op.multiplication(P, Q);
                    view.getResultLabel().setText(op.printPolynomial(result));
                }
            }
        }
    }

    class DivideButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(view.getMain(), "I tried to implement this function, but for whatever reason it does not work.\nCheck code please!");
            /*List<Polynomial> res;
            if (view.getTextField1().getText().equals("") || view.getTextField2().getText().equals("")) {
                JOptionPane.showMessageDialog(view.getMain(), "Bad input format");
            } else {
                P = op.readPolynomial(view.getTextField1().getText());
                Q = op.readPolynomial(view.getTextField2().getText());
                if (P == null || Q == null) {
                    JOptionPane.showMessageDialog(view.getMain(), "Bad input format");
                } else {
                    res = op.division(P, Q);
                    view.getResultLabel().setText("Quotient : " + op.printPolynomial(res.get(0)));
                    view.getResultLabel().setText("\nRemainder : " + op.printPolynomial(res.get(1)));
                }
            }
             */
        }
    }

    class DerivateButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getTextField1().getText().equals("")) {
                JOptionPane.showMessageDialog(view.getMain(), "Bad input format");
            } else {
                P = op.readPolynomial(view.getTextField1().getText());
                if (P == null) {
                    JOptionPane.showMessageDialog(view.getMain(), "Bad input format");
                } else {
                    result = op.derivation(P);
                    view.getResultLabel().setText(op.printPolynomial(result));
                }
            }
        }
    }

    class IntegrateButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getTextField1().getText().equals("")) {
                JOptionPane.showMessageDialog(view.getMain(), "Bad input format");
            } else {
                P = op.readPolynomial(view.getTextField1().getText());
                if (P == null) {
                    JOptionPane.showMessageDialog(view.getMain(), "Bad input format");
                } else {
                    result = op.integration(P);
                    view.getResultLabel().setText(op.printPolynomial(result));
                }
            }
        }
    }

    static class ExitButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
