package com.manu.computation;

import com.manu.model.Points;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class LinearApproximation extends FunctionalApproximation {

    private double correlationCoefficient;

    public LinearApproximation(Points points) {
        super(points);
    }

    @Override
    public void computeCoefficients() {
        computeA();
        computeB();
    }

    @Override
    protected void computeA() {
        double delta = sxx() * N - sx() * sx();
        double delta1 = sxy() * N - sx() * sy();
        a = delta1 / delta;
    }

    @Override
    protected void computeB() {
        double delta = sxx() * N - sx() * sx();
        double delta2 = sxx() * sy() - sx() * sxy();
        b = delta2 / delta;
    }

    @Override
    public void computePhy() {
        for (int i = 0; i < N; i++) {
            phy[i] = a * x[i] + b;
        }
    }

    public void computeCorrelation() {
        double meanX = DoubleStream.of(x).average().orElse(0d);
        double meanY = DoubleStream.of(y).average().orElse(0d);
        double difXX = 0;
        double difYY = 0;
        double multiDifXY = 0;

        for (int i = 0; i < N; i++) {
            multiDifXY += (x[i] - meanX) * (y[i] - meanY);
            difXX += Math.pow(x[i] - meanX, 2);
            difYY += Math.pow(y[i] - meanY, 2);
        }

        correlationCoefficient = multiDifXY / Math.sqrt(difXX * difYY);
    }

    public double getCorrelationCoefficient() {
        return correlationCoefficient;
    }

    @Override
    public String toString() {
        return "LinearApproximation";
    }
}
