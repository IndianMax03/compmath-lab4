package com.manu.model;

import java.util.Arrays;

public class Points {

    private double[] x;
    private double[] y;

    public Points() {
    }

    public Points(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public double[] getY() {
        return y;
    }

    public void setY(double[] y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Points{" +
                "x=" + Arrays.toString(x) +
                ", y=" + Arrays.toString(y) +
                '}';
    }
}
