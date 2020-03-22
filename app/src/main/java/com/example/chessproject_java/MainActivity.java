package com.example.chessproject_java;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    androidx.gridlayout.widget.GridLayout gridLayoutId;
    Button findPathsbtn, resetbtn;
    TextView numPaths;
    public static final String Tag = "MainActivity";
    int Click_Times = 0;
    int startPointX = 0;
    int startPointY = 0;
    int endPointX = 0;
    int endPointY = 0;
    int flag = 0;
    int flag1 = 1;
    int counter = 1;
    ArrayList<Integer> unEvenNums_table = new ArrayList<Integer>(Arrays.asList(1, 3, 5, 7));
    ArrayList<String> final_list = new ArrayList<>();
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFindIds();
        gridLayoutId.setColumnCount(8);
        gridLayoutId.setRowCount(8);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {

                final int row = i;
                final int col = j;
                final Button b1 = new Button(this);
                b1.setId(counter++);
                b1.setTag((i) + "," + (j));
                b1.setText((i-1) + "," + (j-1));
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       handleClicks(b1,row,col);
                    }
                });
                setChessColors(i, b1);

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(130, 130);
                lp.addRule(RelativeLayout.RIGHT_OF, b1.getId() - 1);
                b1.setLayoutParams(lp);
                gridLayoutId.addView(b1);
            }
        }
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     resetChess();
            }
        });

        findPathsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Click_Times < 2)
                    Toast.makeText(MainActivity.this, "choose two points!", Toast.LENGTH_SHORT).show();
                else
                    startProsess();
            }
        });
    }

    private void handleClicks(Button b1, int row, int col) {
        Click_Times++;
        if (Click_Times <= 2) {
            if (Click_Times == 1) {
                b1.setText("S");
                startPointX = row;
                startPointY = col;
            } else if (Click_Times == 2) {
                b1.setText("E");
                endPointX = row;
                endPointY = col;
            }
            b1.setTextSize(18F);
            b1.setTextColor(getResources().getColor(R.color.black));
            b1.setBackgroundColor(getResources().getColor(R.color.lightblue));
        }
    }


    private void startProsess() {
        ArrayList<Coordinates> queue1 = new ArrayList<>();
        ArrayList<Coordinates> firstLayer;
        ArrayList<Coordinates> secLayer;
        ArrayList<Coordinates> thirdLayer;

        String end = (endPointX - 1) + "," + (endPointY - 1);
        String start = (startPointX - 1) + "," + (startPointY - 1);

        Coordinates coordinates_start = new Coordinates(startPointX - 1, startPointY - 1, null);
        queue1.add(coordinates_start);

        Coordinates endPoint = new Coordinates(endPointX - 1, endPointY - 1, null);
        firstLayer = Layers.findLayerCoordinates(queue1, endPoint, coordinates_start, 1);
        secLayer = Layers.findLayerCoordinates(firstLayer, endPoint, coordinates_start, 2);
        thirdLayer = Layers.findLayerCoordinates(secLayer, endPoint, coordinates_start, 2);


        for (int i = 0; i < thirdLayer.size(); i++) {
            Coordinates coordinates = thirdLayer.get(i);
            String initial = coordinates.toString();
            Log.i(Tag, initial);
            if (initial.contains(end)) {
                int flag = 0;
                String[] sec = initial.split("-");
                String newStr = "";
                for (int h = sec.length - 2; h >= 0; h--) {
                    if (sec[h].equals(end))
                        flag = h;
                    if (flag <= h) {
                        newStr += sec[h] + "-";
                    }
                }

                if (!final_list.contains(newStr) && newStr.contains(end))
                    final_list.add(newStr);
            }

        }
       if(final_list.size()==0)
           numPaths.setVisibility(View.VISIBLE);
        for (int i = 0; i < final_list.size(); i++) {
           Log.i("coor--final", final_list.get(i).toString());
        }

        // fill the chess with the paths
         updateChessWithMoves(start,end);


    }

    private void updateChessWithMoves(String start, String end) {
        int count = gridLayoutId.getChildCount();
        String [] colors = this.getResources().getStringArray(R.array.colors);
//         ArrayList<Integer> colorList=new ArrayList<>();
//        for (int i = 0; i < colors.length; i++) {
//            colorList.add(colors[i]);
//        }
        for (int i = 0; i < count; i++) {
            Button b1 = (Button) gridLayoutId.getChildAt(i);
            // b1.setText("");
            String tagX = b1.getTag().toString().split(",")[0];
            String tagY = b1.getTag().toString().split(",")[1];
            for(int k = 0; k< final_list.size(); k++){
                String[] sec = final_list.get(k).split("-");
                String newStr = "";
              //  Log.i("coor--final-----alllll", final_list.get(k));
                for (int h = sec.length - 1; h >= 0; h--) {
                    Log.i("coor--final", sec[h]);
                    String currentPosition=(Integer.parseInt(tagX)-1)+","+(Integer.parseInt(tagY)-1);


                    //πρεπει να ελεγξω εδω μεσα καθε στοιχειου του grid layout αν ειναι ιδιο με αυτο απο τα μονοπατια ετσι
                    //ωστε να αλλαξω χρωμα
                    if(currentPosition.equals(sec[h]) && !currentPosition.equals(end) && !currentPosition.equals(start)){
                       b1.setBackgroundColor(Color.parseColor(colors[k]));
                       // b1.setBackgroundColor(colorList.get(k));
                        Log.i("coor--final-----alllll", final_list.get(k));
                        int a=3;
                    }

                }
                Log.i("coor--final","---------------");
            }
        }
    }


    private void resetChess() {
        int count = gridLayoutId.getChildCount();
        for (int i = 0; i < count; i++) {
            Button b1 = (Button) gridLayoutId.getChildAt(i);
            b1.setText("");
            String tagX = b1.getTag().toString().split(",")[0];
            setChessColors(Integer.parseInt(tagX), b1);
        }
        Click_Times = 0;
        numPaths.setVisibility(View.GONE);
        final_list.clear();
    }


    private void setChessColors(int chessRow, Button b1) {
        if (unEvenNums_table.contains(chessRow)) {
            if (flag == 0) {
                b1.setBackgroundColor(getResources().getColor(R.color.white));
                flag = 1;
            } else {
                b1.setBackgroundColor(getResources().getColor(R.color.black));
                flag = 0;
            }
        } else {
            if (flag1 == 0) {
                b1.setBackgroundColor(getResources().getColor(R.color.white));
                flag1 = 1;
            } else {
                b1.setBackgroundColor(getResources().getColor(R.color.black));
                flag1 = 0;
            }
        }
    }



    private void initFindIds() {
        findPathsbtn = findViewById(R.id.findPathsId);
        gridLayoutId = findViewById(R.id.gridLayoutId);
        resetbtn = findViewById(R.id.resetId);
        numPaths=findViewById(R.id.numPathsId);
    }
}





