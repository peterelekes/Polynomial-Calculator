package Controller;

import Model.Polynomial;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitTests {
    Operation op = new Operation();
    Polynomial P = new Polynomial();
    Polynomial Q = new Polynomial();

    @Test
    public void addTest()
    {
        P=op.readPolynomial("3x^2+2x");
        Q=op.readPolynomial("x^2-x");
        assertTrue(op.printPolynomial(op.addition(P,Q)).equals("+4.0x^2+1.0x^1"));
    }
    @Test
    public void subtractTest()
    {
        P=op.readPolynomial("3x^2+2x");
        Q=op.readPolynomial("x^2-x");
        assertTrue(op.printPolynomial(op.subtraction(P,Q)).equals("+2.0x^2+3.0x^1"));
    }
    @Test
    public void multiplyTest()
    {
        P=op.readPolynomial("x^2+x^1");
        Q=op.readPolynomial("x^0");
        assertTrue(op.printPolynomial(op.multiplication(P,Q)).equals("+1.0x^2+1.0x^1"));
    }
    @Test
    public void derivateTest()
    {
        P=op.readPolynomial("x^2+x^1");
        assertTrue(op.printPolynomial(op.derivation(P)).equals("+2.0x^1+1.0"));
    }
    @Test
    public void integrateTest()
    {
        P=op.readPolynomial("x^4+x^1");
        assertTrue(op.printPolynomial(op.integration(P)).equals("+0.2x^5+0.5x^2"));
    }

}
