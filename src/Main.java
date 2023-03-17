import Controller.Controller;
import View.Calc;

public class Main {
    public static void main(String[] args) {
        Controller ctrl = new Controller(new Calc());
        ctrl.init();
    }
}
