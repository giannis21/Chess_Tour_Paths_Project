//package com.example.chessproject_java;
//
//import android.util.Log;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//
//import static com.example.chessproject_java.MainActivity.Tag;
//
//public class Knight {
//
//  //  static ArrayList<Coordinates> newListofCoordinates = new ArrayList<>();
//
//    public static ArrayList<Coordinates> tempOfEachLevel = new ArrayList<>();
//    static int currentDepthCounter = 0;
//    private static ArrayList<Coordinates> temp1 = new ArrayList<>();
//    private static ArrayList<Coordinates> temp2 = new ArrayList<>();
//    private static ArrayList<Coordinates> temp3 = new ArrayList<>();
//    private static int counteLayer1 = 0;
//    static int counteLayer2 = 0;
//    static int counteLayer3 = 0;
//    static Coordinates startPoint;
//    static void findPaths(int N, int endX, int endY, ArrayList<Coordinates> totalList, int initialPoint,int startPointX,int startPointY) {
//        int[] X = {2, 1, -1, -2, -2, -1, 1, 2};
//        int[] Y = {1, 2, 2, 1, -1, -2, -2, -1};
//
//        ++currentDepthCounter;
//
//        ArrayList<Coordinates> queueLevel = new ArrayList<>();
//
//        for (int i = 0; i < totalList.size(); i++) {
//            Coordinates coordinates = totalList.get(i);
//
//            for (int j = 0; j < 8; j++) {
//
//                int x = coordinates.x + X[j];
//                int y = coordinates.y + Y[j];
//
//                if (x >= 0 && y >= 0 && x < 8 && y < 8) {
//                    Coordinates temp22 = new Coordinates(x, y, false);
//
//                    if (!queueLevel.contains(temp22)) {
//
//                        if (currentDepthCounter == 1) {
//                            queueLevel.add(temp22);
//                            temp1.add(temp22);
//                        } else if (currentDepthCounter == 2) {
//                            queueLevel.add(temp22);
//                            temp2.add(temp22);
//                        } else if (currentDepthCounter == 3) {
//                            queueLevel.add(temp22);
//                            temp3.add(temp22);
//                        }
//                    }
//                }
//            }
//        }
//
//        Log.i(Tag, "size of queueLevel=" + queueLevel.size());
//
//        tempOfEachLevel.addAll(queueLevel);
//
//        if (currentDepthCounter != 4) {
//            Log.i(Tag, "change layer");
//            findPaths(8, endX, endY, queueLevel, counteLayer1 + 1,startPointX,startPointY);
//        } else {
//
//
//
//            Log.i(Tag, "size of nodes counter1=" + temp1.size());
//            Log.i(Tag, "size of nodes counter2=" + temp2.size());
//            Log.i(Tag, "size of nodes counter3=" + temp3.size());
//            Log.i(Tag, "size of nodes tempOfEachLevel=" + tempOfEachLevel.size());

//            ArrayList<Coordinates> firstLevel = new ArrayList<>();
//            ArrayList<Coordinates> secLevel = new ArrayList<>();
//            ArrayList<Coordinates> thirdLevel = new ArrayList<>();
//            for (int i = 0; i < tempOfEachLevel.size(); i++) {
//                if(i<=temp1.size()){
//                    firstLevel.add(tempOfEachLevel.get(i));
//                }else if(i<=temp2.size())
//                    secLevel.add(tempOfEachLevel.get(i));
//                else{
//                    thirdLevel.add(tempOfEachLevel.get(i));
//                }
//            }
//
//            ArrayList<ArrayList<Coordinates>> listOfLists = new ArrayList<>();
//            Coordinates startPoint=new Coordinates(startPointX,startPointY,false);
//            Coordinates endPoint=new Coordinates(endX,endY,false);
//            for(int i=1;i<firstLevel.size();i++){
//                for(int j=0;j<secLevel.size();j++){
//                    for(int k=0;k<thirdLevel.size();k++){
//
//                        Coordinates a=firstLevel.get(0);
//                        Coordinates b=secLevel.get(j);
//                        Coordinates c=thirdLevel.get(k);
//                        if(a.equals(endPoint)){
//                            ArrayList temp11=new ArrayList<Coordinates>();
//                            temp11.add(startPoint);
//                            temp11.add(a);
//                            listOfLists.add(temp11);
//                        }else if(b.equals(endPoint)){
//                            ArrayList temp11=new ArrayList<Coordinates>();
//                            temp11.add(startPoint);
//                            temp11.add(a);
//                            temp11.add(b);
//                            listOfLists.add(temp11);
//                        }else if(c.equals(endPoint)){
//                            ArrayList temp11=new ArrayList<Coordinates>();
//                            temp11.add(startPoint);
//                            temp11.add(a);
//                            temp11.add(b);
//                            temp11.add(c);
//                            listOfLists.add(temp11);
//                        }
//                        Log.i(Tag, "first element=" + a.getX()+"--"+a.getY());
//                        for(int n=0;n<listOfLists.size();n++){
//                            ArrayList temp=listOfLists.get(n);
//                            for(int d=0;d<temp.size();d++){
//                               Log.i(Tag,"coordinates"+temp.get(i).toString());
//                            }
//                        }
//
//                    }
//                }
//            }
//        }
//
//
//    }
//}
