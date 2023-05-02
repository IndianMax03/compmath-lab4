package com.manu.computation;

import com.manu.model.Points;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ExponentialApproximation extends FunctionalApproximation {

    public ExponentialApproximation(Points points) {
        super(points);
    }

    @Override
    public void computeCoefficients() {
        computeB();
        computeA();
    }

    @Override
    protected void computeA() {
        a = Math.exp(sy() / N - b * sx() / N);
    }

    @Override
    protected void computeB() {
        b = (N * sxy() - sx() * sy()) / (N * sxx() - sx() * sx() );
    }

    @Override
    public void computePhy() {
        for (int i = 0; i < N; i++) {
            phy[i] = a * Math.exp(b * x[i]);
        }
    }

    @Override
    protected double sy() {
        return Arrays.stream(y)
                .filter(y -> y > 0)
                .map(Math::log)
                .sum();
    }

    @Override
    protected double sxy() {
        return IntStream.range(0, x.length)
                .filter(i -> y[i] > 0)
                .mapToDouble(i -> x[i] * Math.log(y[i]))
                .sum();
    }

    @Override
    public String toString() {
        return "ExponentialApproximation";
    }
}
