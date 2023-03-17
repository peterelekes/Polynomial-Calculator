package Model;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {

    private List<Monomial> monomialsList = new ArrayList<>();

    public List<Monomial> getMonomialsList() {
        return monomialsList;
    }

    public void setMonomialsList(List<Monomial> monomialsList) {
        this.monomialsList = monomialsList;
    }


    public Polynomial(List<Monomial> monomialsList) {
        this.monomialsList = monomialsList;
    }

    public Polynomial() {
    }
}
