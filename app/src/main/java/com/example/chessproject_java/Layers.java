package com.example.chessproject_java;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.chessproject_java.MainActivity.neighboorsCoord;

public class Layers {
    static final private int[] X = {2, 1, -1, -2, -2, -1, 1, 2};
    static final private int[] Y = {1, 2, 2, 1, -1, -2, -2, -1};

    static ArrayList<Coordinates> findLayerCoordinates(ArrayList<Coordinates> totalList) {

        ArrayList<Coordinates> queueLevel = new ArrayList<>();

        for (int i = 0; i < totalList.size(); i++) {

            Coordinates parentCoord = totalList.get(i);

            for (int j = 0; j < 8; j++) {

                int x = parentCoord.x + X[j];
                int y = parentCoord.y + Y[j];

                if (x >= 0 && y >= 0 && x < 8 && y < 8) {

                    Coordinates coordinates = new Coordinates(x, y, parentCoord);
                    addNeighboors(x,y, coordinates);
                    queueLevel.add(coordinates);
                }
            }

        }
        return queueLevel;
    }

    private static void addNeighboors(int x_after_move, int y_after_move, Coordinates coordinates) {

        //πρεπει να παρω ολα τα πιθανα γειτονικα coordinates του καθε σημειου

        // περιπτωση x==2 && y==1
        String initPoint = (x_after_move - 2) + "," + (y_after_move - 1);
        String firstPoint = (x_after_move - 1) + "," + (y_after_move - 1);
        String secPoint = x_after_move + "," + (y_after_move - 1);
        String thirdPoint = x_after_move + "," + y_after_move;
        String total = initPoint+"-"+firstPoint + "-" + secPoint + "-" + thirdPoint;
        neighboorsCoord.add(total);


        // περιπτωση x==1 && y==2
        String initPoint1 = (x_after_move - 1) + "," + (y_after_move - 2);
        String firstPoint1 = (x_after_move - 1) + "," + (y_after_move - 1);
        String secPoint1 = (x_after_move - 1) + "," + (y_after_move);
        String thirdPoint1 = x_after_move + "," + y_after_move;
        String total1 = initPoint1+"-"+firstPoint1 + "-" + secPoint1 + "-" + thirdPoint1;
        neighboorsCoord.add(total1);


        // περιπτωση x==-1 && y==2
        String initPoint2 = (x_after_move + 1) + "," + (y_after_move - 2);
        String firstPoint2 = (x_after_move + 1) + "," + (y_after_move - 1);
        String secPoint2 = (x_after_move + 1) + "," + (y_after_move);
        String thirdPoint2 = x_after_move + "," + y_after_move;
        String total2 = initPoint2+"-"+firstPoint2 + "-" + secPoint2 + "-" + thirdPoint2;
        neighboorsCoord.add(total2);


        // περιπτωση x==-2 && y==1
        String initPoint3 = (x_after_move + 2) + "," + (y_after_move -1);
        String firstPoint3 = (x_after_move + 1) + "," + (y_after_move -1);
        String secPoint3 = (x_after_move) + "," + (y_after_move-1);
        String thirdPoint3 = x_after_move + "," + y_after_move;
        String total3 =  initPoint3+"-"+firstPoint3 + "-" + secPoint3 + "-" + thirdPoint3;
        neighboorsCoord.add(total3);


        // περιπτωση x==-2 && y==-1
        String initPoint4 = (x_after_move + 2) + "," + (y_after_move + 1);
        String firstPoint4 = (x_after_move + 1) + "," + (y_after_move + 1);
        String secPoint4 = (x_after_move) + "," + (y_after_move+ 1);
        String thirdPoint4 = x_after_move + "," + y_after_move;
        String total4 = initPoint4+"-"+firstPoint4 + "-" + secPoint4 + "-" + thirdPoint4;
        neighboorsCoord.add(total4);


        // περιπτωση x==-1 && y==-2
        String initPoint5 = (x_after_move + 1) + "," + (y_after_move + 2);
        String firstPoint5 = (x_after_move + 1) + "," + (y_after_move + 1);
        String secPoint5 = (x_after_move + 1) + "," + (y_after_move);
        String thirdPoint5 = x_after_move + "," + y_after_move;
        String total5 = initPoint5+"-"+firstPoint5 + "-" + secPoint5 + "-" + thirdPoint5;
        neighboorsCoord.add(total5);  //οκ double cjeck



        // περιπτωση x==1 && y==-2
        String initPoint6 = (x_after_move - 1 ) + "," + (y_after_move +2);
        String firstPoint6 = (x_after_move - 1) + "," + (y_after_move + 1);
        String secPoint6 = (x_after_move - 1) + "," + (y_after_move);
        String thirdPoint6 = x_after_move + "," + y_after_move;
        String total6 = initPoint6+"-"+firstPoint6 + "-" + secPoint6 + "-" + thirdPoint6;
        neighboorsCoord.add(total6);  //οκ

        // περιπτωση x==2 && y==-1
        String initPoint7 = (x_after_move -2 ) + "," + (y_after_move +1);
        String firstPoint7 = (x_after_move - 1) + "," + (y_after_move + 1);
        String secPoint7 = (x_after_move ) + "," + (y_after_move+1);
        String thirdPoint7 = x_after_move + "," + y_after_move;
        String total7 = initPoint7+"-"+firstPoint7 + "-" + secPoint7 + "-" + thirdPoint7;
        neighboorsCoord.add(total7);


    }

}


