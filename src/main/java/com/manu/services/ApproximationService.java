package com.manu.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.manu.computation.*;
import com.manu.model.Points;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApproximationService {

    private final List<FunctionalApproximation>  approximations = new ArrayList<>();

    public ApproximationService(Points points) {
        CubicApproximation cubeAppro = new CubicApproximation(points);
        ExponentialApproximation expoAppro = new ExponentialApproximation(points);
        LinearApproximation lineAppro = new LinearApproximation(points);
        LogApproximation logAppro = new LogApproximation(points);
        PowerApproximation powAppro = new PowerApproximation(points);
        SquareApproximation squareAppro = new SquareApproximation(points);


        double[] x = points.getX();
        double[] y = points.getY();

        boolean hasNegX = false;
        boolean hasNegY = false;

        for (int i = 0; i < FunctionalApproximation.N; i++) {
            if (x[i] < 0) {
                hasNegX = true;
            }
            if (y[i] < 0) {
                hasNegY = true;
            }
        }

        if (hasNegX) {
            approximations.add(cubeAppro);
            approximations.add(expoAppro);
            approximations.add(lineAppro);
            approximations.add(squareAppro);
        } else if (hasNegY) {
            approximations.add(cubeAppro);
            approximations.add(lineAppro);
            approximations.add(logAppro);
            approximations.add(squareAppro);
        } else {
            approximations.add(cubeAppro);
            approximations.add(expoAppro);
            approximations.add(lineAppro);
            approximations.add(logAppro);
            approximations.add(powAppro);
            approximations.add(squareAppro);
        }
    }

    public ObjectNode processData() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        double minDiviation = 0;
        int minDiviationIndex = 0;

        for (int i = 0; i < approximations.size(); i++) {
            FunctionalApproximation approximation = approximations.get(i);
            approximation.computeCoefficients();
            approximation.computePhy();
            approximation.computeEpsilons();
            approximation.computeDeviationMeasure();
            approximation.computeStandardDeviation();

            if (i == 0) {
                minDiviation = approximation.getStandardDeviation();
            } else {
                if (minDiviation > approximation.getStandardDeviation()) {
                    minDiviation = approximation.getStandardDeviation();
                    minDiviationIndex = i;
                }
            }

        }
        FunctionalApproximation answer = approximations.get(minDiviationIndex);
        node.put("name", answer.toString());
        node.putPOJO("x",  Collections.singletonList(answer.getX()));
        node.putPOJO("y",  Collections.singletonList(answer.getY()));
        node.put("a", answer.getA());
        node.put("b", answer.getB());
        node.put("c", answer.getC());
        node.put("d", answer.getD());
        node.putPOJO("phy", Collections.singletonList(answer.getPhy()));
        node.putPOJO("epsilons", Collections.singletonList(answer.getEpsilons()));
        node.put("S", answer.getDeviationMeasure());
        if (answer instanceof LinearApproximation) {
            ((LinearApproximation) answer).computeCorrelation();
            node.put("r", ((LinearApproximation) answer).getCorrelationCoefficient());
        }
        node.put("deviation", answer.getStandardDeviation());

        return node;
    }

}
