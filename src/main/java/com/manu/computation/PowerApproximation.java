package com.manu.computation;

import com.manu.model.Points;

import java.util.Arrays;

public class PowerApproximation extends FunctionalApproximation {

    public PowerApproximation(Points points) {
        super(points);
    }

    @Override
    public void computeCoefficients() {
        computeB();
        computeA();
    }

    @Override
    protected void computeA() {
        double meanLnX = slnx() / N;
        double meanLnY = slny() / N;
        a = Math.exp(meanLnY - b * meanLnX);
    }

    @Override
    protected void computeB() {
        double meanLnX = slnx() / N;
        double meanLnY = slny() / N;
        double diffLnXLnY = 0;
        double diffXX = 0;

        for (int i = 0; i < N; i++) {
            diffLnXLnY += (Math.log(x[i]) - meanLnX) *  (Math.log(y[i]) - meanLnY);
            diffXX += Math.pow(Math.log(x[i]) - meanLnX, 2);
        }

        b = diffLnXLnY / diffXX;

    }

    @Override
    public void computePhy() {
        for (int i = 0; i < N; i++) {
            phy[i] = a * Math.pow(x[i], b);
        }
    }

    private double slnx() {
        return Arrays.stream(x)
                .filter(x -> x > 0)
                .map(Math::log)
                .sum();
    }

    private double slny() {
        return Arrays.stream(y)
                .filter(y -> y > 0)
                .map(Math::log)
                .sum();
    }

    @Override
    public String toString() {
        return "PowerApproximation";
    }
}
