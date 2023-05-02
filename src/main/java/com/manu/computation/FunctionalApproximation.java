package com.manu.computation;

import com.manu.model.Points;

import java.util.Arrays;
import java.util.stream.IntStream;

public abstract class FunctionalApproximation {

    public static final int N = 8;
    protected final double[] x;
    protected final double[] y;
    protected double a = 0;
    protected double b = 0;
    protected double c = 0;

    protected double standardDeviation; //  среднеквадратичное отклонение
    protected double[] epsilons = new double[N]; //  отклонения
    protected double[] phy = new double[N]; //  новые значения
    protected double deviationMeasure; //  мера отклонения S

    public FunctionalApproximation(Points points) {
        this.x = points.getX();
        this.y = points.getY();
    }

    public abstract void computeCoefficients();

    protected abstract void computeA();

    protected abstract void computeB();

    protected void computeC() {
        return;
    }

    public abstract void computePhy();

    public void computeEpsilons() {
        for (int i = 0; i < N; i++) {
            epsilons[i] = phy[i] - y[i];
        }
    }

    public void computeStandardDeviation() {
        double difPhyY = 0;

        for (int i = 0; i < N; i++) {
            difPhyY += Math.pow(phy[i] - y[i], 2);
        }
        standardDeviation = Math.sqrt(difPhyY / N);
    }

    public  void computeDeviationMeasure() {
        deviationMeasure = Arrays.stream(epsilons)
                .map(eps -> eps * eps)
                .sum();
    }

    protected double sx() {
        return Arrays.stream(x)
                .sum();
    }

    protected double sxx() {
        return Arrays.stream(x)
                .map(x -> x * x)
                .sum();
    }

    protected double sy() {
        return Arrays.stream(y)
                .sum();
    }

    protected double sxy() {
        return IntStream.range(0, x.length)
                .mapToDouble(i -> x[i] * y[i])
                .sum();
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public double[] getEpsilons() {
        return epsilons;
    }

    public double[] getPhy() {
        return phy;
    }

    public double getDeviationMeasure() {
        return deviationMeasure;
    }

    @Override
    public String toString() {
        return "FunctionalApproximation";
    }
}
