package com.manu.computation;

import com.manu.model.Points;

public class SquareApproximation extends FunctionalApproximation {

    public SquareApproximation(Points points) {
        super(points);
    }

    @Override
    public void computeCoefficients() {
        computeA();
        computeB();
        computeC();
    }

    @Override
    protected void computeA() {
        a = (N * sxy() - sx() * sy()) / (N * sxx() - sx() * sx());
    }

    @Override
    protected void computeB() {
        b = (sy() - a * sxx()) / N;
    }

    @Override
    protected void computeC() {
        c = (sy() - a * sxx() - b * sx()) / N;
    }

    @Override
    public void computePhy() {
        for (int i = 0; i < N; i++) {
            phy[i] = a * x[i] * x[i] + b * x[i] + c;
        }
    }

    @Override
    public String toString() {
        return "SquareApproximation";
    }
}
