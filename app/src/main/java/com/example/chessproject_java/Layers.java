package com.example.chessproject_java;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Layers {
    public static ArrayList<Coordinates> visited = new ArrayList<>();

    static  ArrayList<ArrayList<Coordinates>> listOfLists = new ArrayList<>();

    static final private int[] X = {2, 1, -1, -2, -2, -1, 1, 2};
    static final private int[] Y = {1, 2, 2, 1, -1, -2, -2, -1};
    static ArrayList<Coordinates> findLayerCoordinates(ArrayList<Coordinates> totalList, Coordinates endPoint, Coordinates startPoint, int timesCalled) {

        ArrayList<Coordinates> queueLevel = new ArrayList<>();

        for (int i = 0; i < totalList.size(); i++) {

            Coordinates current=totalList.get(i);

            for (int j = 0; j < 8; j++) {

                int x = current.x + X[j];
                int y = current.y + Y[j];

                if (x >= 0 && y >= 0 && x < 8 && y < 8) {
                     queueLevel.add(new Coordinates(x, y,current));
                }
            }
        }
        return queueLevel;
    }

}


