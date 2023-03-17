package Controller;

import Model.Monomial;
import Model.Polynomial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Operation {

    public static boolean isNumeric(String str) {
        return str.matches("[+-]*\\d*\\.?\\d+");
    }

    public Polynomial readPolynomial(String text) {
        Polynomial returnPolynomial = new Polynomial();
        String monomialFormat = "([+-]?[\\d\\.]*[a-zA-Z]?\\^?\\d*)";
        String monomialPartsFormat = "([+-]?[\\d\\.]*)([a-zA-Z]?)\\^?(\\d*)";

        Pattern p1 = Pattern.compile(monomialFormat);
        Matcher m1 = p1.matcher(text);

        while (!m1.hitEnd()) {
            m1.find();
            Pattern p2 = Pattern.compile(monomialPartsFormat);
            Matcher m2 = p2.matcher(m1.group());

            if (m2.find()) {

                double coefficient;
                try {
                    String coef = m2.group(1);
                    if (isNumeric(coef)) {
                        coefficient = Float.valueOf(coef);
                    } else {
                        coefficient = Float.valueOf(coef + "1");
                    }
                } catch (IllegalStateException e) {
                    coefficient = 0.0F;
                }

                int degree;
                try {
                    String exp = m2.group(3);
                    if (isNumeric(exp)) {
                        degree = Integer.valueOf(exp);
                    } else {
                        degree = 1;
                    }
                } catch (IllegalStateException e) {
                    degree = 0;
                }
                if (coefficient == 0.0F)
                    return null;
                else {
                    Monomial insert = new Monomial(coefficient, degree);
                    returnPolynomial.getMonomialsList().add(insert);
                }
            }
        }
        return returnPolynomial;
    }

    public void sortPolynomial(Polynomial P) {
        for (Monomial i : P.getMonomialsList())
            for (Monomial j : P.getMonomialsList())
                if (i.getDegree() < j.getDegree())
                    Collections.swap(P.getMonomialsList(), P.getMonomialsList().indexOf(i), P.getMonomialsList().indexOf(j));

    }

    public String printPolynomial(Polynomial P) {
        if (P == null)
            return null;
        else {
            String returnString = "";
            sortPolynomial(P);
            for (Monomial i : P.getMonomialsList()) {
                    if (i.getCoeff() >= 0)
                        if (i.getDegree() == 0)
                            returnString = returnString + "+" + i.getCoeff();
                        else
                            returnString = returnString + "+" + i.getCoeff() + "x^" + i.getDegree();
                    else if (i.getDegree() == 0)
                        returnString = returnString + "+" + i.getCoeff();
                    else
                        returnString = returnString + i.getCoeff() + "x^" + i.getDegree();

            }
            return returnString;
        }
    }

    public int getDegree(Polynomial P) {
        int deg = 0;
        for (Monomial i : P.getMonomialsList())
            if (i.getDegree() >= deg)
                deg = i.getDegree();
        return deg;
    }

    public Monomial getMaxMonomial(Polynomial P) {
        for (Monomial i : P.getMonomialsList())
            if (i.getDegree() == getDegree(P))
                return i;
        return null;
    }

    public Polynomial addition(Polynomial P, Polynomial Q) {
        Polynomial returnPolynomial = new Polynomial();
        returnPolynomial.setMonomialsList(P.getMonomialsList());
        for (Monomial i : Q.getMonomialsList()) {
            boolean found = false;
            for (Monomial j : returnPolynomial.getMonomialsList()) {
                if (i.getDegree() == j.getDegree()) {
                    j.setCoeff(j.getCoeff()+i.getCoeff());
                    found = true;
                }
            }
            if (!found)
                returnPolynomial.getMonomialsList().add(i);
        }
        return returnPolynomial;
    }


    public Polynomial subtraction(Polynomial P, Polynomial Q) {
        Polynomial returnPolynomial = new Polynomial();
        returnPolynomial.setMonomialsList(P.getMonomialsList());
        for (Monomial i : Q.getMonomialsList()) {
            boolean found = false;
            for (Monomial j : returnPolynomial.getMonomialsList()) {
                if (i.getDegree() == j.getDegree()) {
                    j.setCoeff(j.getCoeff()-i.getCoeff());
                    found = true;
                }
            }
            if (!found) {
                i.setCoeff(-i.getCoeff());
                returnPolynomial.getMonomialsList().add(i);
            }
        }
        return returnPolynomial;
    }

    public Polynomial multiplication(Polynomial P, Polynomial Q) {
        Polynomial returnPolynomial = new Polynomial();
        for (Monomial i : P.getMonomialsList()) {
            for (Monomial j : Q.getMonomialsList()) {
                Monomial mult = new Monomial();
                mult.setCoeff(j.getCoeff()*i.getCoeff());
                mult.setDegree(j.getDegree()+i.getDegree());
                boolean found = false;
                for (Monomial k : returnPolynomial.getMonomialsList()) {
                    if (k.getDegree() == mult.getDegree()) {
                        found = true;
                        k.setCoeff(k.getCoeff()+mult.getCoeff());
                    }
                }
                if (!found)
                    returnPolynomial.getMonomialsList().add(mult);
            }
        }
        return returnPolynomial;
    }

    public List<Polynomial> division(Polynomial P, Polynomial Q) {
        List<Polynomial> result = new ArrayList<>();
        Polynomial c = new Polynomial();
        Polynomial polmc = new Polynomial();
        while (getDegree(P) >= getDegree(Q)) {
            Monomial maxMonomialP = getMaxMonomial(P);
            Monomial maxMonomialQ = getMaxMonomial(Q);
            Monomial mc = new Monomial();
            mc.setDegree(maxMonomialP.getDegree()-maxMonomialQ.getDegree());
            mc.setCoeff(maxMonomialP.getCoeff()/ maxMonomialQ.getCoeff());
            c.getMonomialsList().add(mc);
            polmc.getMonomialsList().add(mc);
            printPolynomial(polmc);
            Polynomial aux = multiplication(Q, polmc);
            printPolynomial(aux);
            P = subtraction(P, aux);
            printPolynomial(P);
            printPolynomial(c);
        }
        result.add(c);
        result.add(P);
        return result;
    }

    public Polynomial derivation(Polynomial P) {
        Polynomial returnPolynomial = new Polynomial();
        for (Monomial i : P.getMonomialsList()) {
            Monomial der = new Monomial();
            der.setDegree(i.getDegree()-1);
            der.setCoeff(i.getCoeff()*i.getDegree());
            boolean found = false;
            for (Monomial k : returnPolynomial.getMonomialsList()) {
                if (k.getDegree() == der.getDegree()) {
                    found = true;
                    k.setCoeff(k.getCoeff()+der.getCoeff());
                }
            }
            if (!found)
                returnPolynomial.getMonomialsList().add(der);
        }
        return returnPolynomial;
    }

    public Polynomial integration(Polynomial P) {
        Polynomial returnPolynomial = new Polynomial();
        for (Monomial i : P.getMonomialsList()) {
            Monomial der = new Monomial();
            der.setDegree(i.getDegree()+1);
            der.setCoeff(i.getCoeff()/(i.getDegree()+1));
            boolean found = false;
            for (Monomial k : returnPolynomial.getMonomialsList()) {
                if (k.getDegree() == der.getDegree()) {
                    found = true;
                    k.setCoeff(k.getCoeff()+der.getCoeff());
                }
            }
            if (!found)
                returnPolynomial.getMonomialsList().add(der);
        }
        return returnPolynomial;
    }
}
