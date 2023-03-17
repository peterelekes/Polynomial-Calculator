package Model;

public class Monomial {
    private double coeff;
    private int degree;

    public Monomial(double coeff, int degree) {
        this.coeff = coeff;
        this.degree = degree;
    }

    public Monomial() {
        this.degree = 0;
        this.coeff = 0;
    }

    public double getCoeff() {
        return coeff;
    }

    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
