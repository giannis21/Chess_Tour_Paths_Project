package com.example.chessproject_java;


import java.util.ArrayList;

public class Coordinates {
    int x,y;
    private int numOfchildren;
    private Coordinates parent;
  
    public Coordinates(int x, int y, Coordinates parentNode) {
        this.x = x;
        this.y = y;
        parent=parentNode;
   
    }

    private Coordinates getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public String toString() {
        return    x + "," + y +"-"+getParent();

    }

}
