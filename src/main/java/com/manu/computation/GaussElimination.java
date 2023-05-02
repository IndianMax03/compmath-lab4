package com.manu.computation;

public class GaussElimination {

    private final int n; // число уравнений в СЛАУ
    private final double[][] a; // матрица коэффициентов уравнений
    private final double[] b; // вектор свободных членов

    public GaussElimination(double[][] a, double[] b) {
        this.n = b.length;
        this.a = new double[n][n];
        this.b = new double[n];

        // Создаем копии матрицы коэффициентов и вектора свободных членов
        for (int i = 0; i < n; i++) {
            this.b[i] = b[i];
            for (int j = 0; j < n; j++) {
                this.a[i][j] = a[i][j];
            }
        }
    }

    public double[] solve() {
        for (int k = 0; k < n - 1; k++) {
            // Поиск максимального элемента в столбце k
            int maxRow = k;
            double maxElement = Math.abs(a[k][k]);
            for (int i = k + 1; i < n; i++) {
                double absElement = Math.abs(a[i][k]);
                if (absElement > maxElement) {
                    maxElement = absElement;
                    maxRow = i;
                }
            }

            // Обмен местами строк k и maxRow
            if (maxRow != k) {
                double[] temp = a[k];
                a[k] = a[maxRow];
                a[maxRow] = temp;
                double tempB = b[k];
                b[k] = b[maxRow];
                b[maxRow] = tempB;
            }

            // Приведение матрицы к треугольному виду
            for (int i = k + 1; i < n; i++) {
                double factor = a[i][k] / a[k][k];
                b[i] -= factor * b[k];
                for (int j = k; j < n; j++) {
                    a[i][j] -= factor * a[k][j];
                }
            }
        }

        // Решение уравнений методом обратного хода
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += a[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / a[i][i];
        }

        return x;
    }
}
