package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Calc extends JFrame{
    private JPanel main;
    private JButton multiplicateButton;
    private JButton addButton;
    private JButton subtractButton;
    private JButton exitButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton divideButton;
    private JButton derivateButton;
    private JButton integrateButton;
    private JLabel resultLabel;

    //region get&set
    public JPanel getMain() {
        return main;
    }

    public void setMain(JPanel main) {
        this.main = main;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }

    public void setResultLabel(JLabel resultLabel) {
        this.resultLabel = resultLabel;
    }
    //endregion

    //region buttons
    public void AdditionButton(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void SubtractionButton(ActionListener actionListener) {
        subtractButton.addActionListener(actionListener);
    }

    public void MultiplicationButton(ActionListener actionListener) {
        multiplicateButton.addActionListener(actionListener);
    }

    public void DivisionButton(ActionListener actionListener) {
        divideButton.addActionListener(actionListener);
    }

    public void DerivationButton(ActionListener actionListener) {
        derivateButton.addActionListener(actionListener);
    }

    public void IntegrationButton(ActionListener actionListener) {
        integrateButton.addActionListener(actionListener);
    }

    public void ExButton(ActionListener actionListener) {
        exitButton.addActionListener(actionListener);
    }
    //endregion

    public Calc() {    }
}
