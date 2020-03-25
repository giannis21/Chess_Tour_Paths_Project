package com.example.chessproject_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
    Button findPathsbtn, resetbtn,showCoorbtn;
    TextView numPaths, displayedPathsId;
    CardView pathsCardId;
    int Click_Times = 0;
    int startPointX = 0;
    int startPointY = 0;
    int endPointX = 0;
    int endPointY = 0;
    int flag = 0;
    int flag1 = 1;
    int counter = 1;

    ArrayList<Integer> unEvenNums_table = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
    ArrayList<String> final_list = new ArrayList<>();
    ArrayList<String> twoSelectedPoint = new ArrayList<>();
    ArrayList<String> final_listWithNeighboors = new ArrayList<>();
    public static ArrayList<String> neighboorsCoord = new ArrayList<>();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFindIds();        //αρχικοποιω τα layout components που θα χρησιμοποιησω
        setChessBoard();     //σετάρω το grid layout με τα children-buttons
    }

    private void setChessBoard() {
        gridLayoutId.setColumnCount(8);
        gridLayoutId.setRowCount(8);

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {

                final int row = i;
                final int col = j;
                final Button b1 = new Button(this);
                b1.setId(counter++);
                b1.setTag((i) + "," + (j));
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleClicks(b1, row, col);
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
        showCoorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCoordinates();
            }
        });
    }

    private void showCoordinates() {

            int count = gridLayoutId.getChildCount();
            for (int i = 0; i < count; i++) {
                Button b1 = (Button) gridLayoutId.getChildAt(i);
                String tagX = b1.getTag().toString().split(",")[0];
                String tagY = b1.getTag().toString().split(",")[1];
                if(!((startPointX - 1) == (Integer.parseInt(tagX)-1) &&  (startPointY - 1) == (Integer.parseInt(tagY)-1)
                || (endPointX - 1) == (Integer.parseInt(tagX)-1) &&  (endPointY - 1) == (Integer.parseInt(tagY)-1)))
                 b1.setText(getString(R.string.coordinates, (Integer.parseInt(tagX)-1),(Integer.parseInt(tagY)-1)));
                b1.setTextSize(17F);
                b1.setTextColor(getResources().getColor(R.color.red));

            }
    }

    private void handleClicks(Button b1, int row, int col) {
        Click_Times++;

            if (Click_Times <= 2) {
                if (Click_Times == 1) {
                    b1.setText("S");
                    startPointX = row;
                    startPointY = col;
                    twoSelectedPoint.add(row+","+col);
                    b1.setTextColor(getResources().getColor(R.color.red));
                    b1.setBackgroundColor(getResources().getColor(R.color.lightblue));
                    b1.setTextSize(17F);
                } else if (Click_Times == 2) {
                    twoSelectedPoint.add(row+","+col);
                    if(twoSelectedPoint.get(0).equals(twoSelectedPoint.get(1))) {
                        Toast.makeText(MainActivity.this, "point already assigned", Toast.LENGTH_SHORT).show();
                        Click_Times--;
                        twoSelectedPoint.remove(twoSelectedPoint.get(1));
                    }else{
                        b1.setText("E");
                        endPointX = row;
                        endPointY = col;
                        twoSelectedPoint.clear();

                        Log.i("Main","startX="+startPointX+"startPointY="+startPointY+"---endPointX="+endPointX+" endpointY="+endPointY);
                        b1.setTextSize(17F);
                        b1.setTextColor(getResources().getColor(R.color.red));
                        b1.setBackgroundColor(getResources().getColor(R.color.lightblue));
                        twoSelectedPoint.clear();
                    }
            }

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

        firstLayer = Layers.findLayerCoordinates(queue1);
        secLayer = Layers.findLayerCoordinates(firstLayer);
        thirdLayer = Layers.findLayerCoordinates(secLayer);


        for (int i = 0; i < thirdLayer.size(); i++) {
            Coordinates coordinates = thirdLayer.get(i);
            String initial = coordinates.toString();         //εχω ολες τις συντεταγμενες του καθε σημείου
            if (initial.contains(end)) {                     //παιρνω μονο τις συντεταγμενες που περιεχουν το τελικο σημειο
                int flag = 0;
                String[] sec = initial.split("-");
                StringBuilder newStr = new StringBuilder();

                for (int h = sec.length - 2; h >= 0; h--) {
                    if (sec[h].equals(end))
                        flag = h;
                    if (flag <= h) {                          //κραταω την συμβολοσειρα συντεταγμενων μεχρι να βρω το τελικο σημειο
                        newStr.append(sec[h]).append("-");
                    }
                }

                StringBuilder newStrTotal = new StringBuilder();
                if (!final_list.contains(newStr.toString()) && newStr.toString().contains(end)) {
                    final_list.add(newStr.toString());
                    String[] sec1 = newStr.toString().split("-");
                    for (int h = 0; h <= sec1.length - 1; h++) {
                        if (sec1[sec1.length - 1].equals(sec1[sec1.length - 2]) )
                            continue;
                        //ελεγχω τα ορια του πινακα για να καλεσω την συναρτηση που βρισκει γειτονικους κομβους παιρνωντας της 2 ορισματα
                        //ενα τελικο και ενα αρχικο σημειο το αποτελεσμα το κανω append σε ενα string ωστε για καθε τελικο σημειο να
                        //εχω το πλήρες μονοπατι
                        if (h + 1 <= sec1.length - 1)
                            newStrTotal.append(returnCorrectNeighboors(sec1[h + 1], sec1[h]));
                    }

                }

                if (!final_listWithNeighboors.contains(newStrTotal.toString()) && !newStrTotal.toString().equals("")) {        //πρεπει αυτα τα μονοπατια να ειναι μοναδικά οποτε κανω έναν ελεγχο πριν τα περάσω στη λιστα
                    newStrTotal = new StringBuilder(removeLastCharacter(newStrTotal.toString()));
                    final_listWithNeighboors.add(newStrTotal.toString());
                    displayedPathsId.append(newStrTotal + "\n\n");
                }
            }
        }
        if(!final_listWithNeighboors.isEmpty()){
            pathsCardId.setVisibility(View.VISIBLE);
        }
        if (final_list.size() == 0)
            numPaths.setVisibility(View.VISIBLE);

        // ενημερώνει το σκακι με μονο τις τελικες κινησεις για λογους διακριτοτητας
        updateChessWithMoves(start, end);
    }

    private String returnCorrectNeighboors(String endPoint, String startPoint) {

        for (int i = 0; i < neighboorsCoord.size(); i++) {
            String[] sec = neighboorsCoord.get(i).split("-");
            if (sec[0].equals(startPoint) && sec[sec.length - 1].equals(endPoint)) { //οταν επιστρεφω τα ενδιαμεσα στοιχεια ενος αρχικου και ενος τελικου σημειου
                return neighboorsCoord.get(i) + "|";                                 //πρεπει να ελεγξω οτι το αρχικο και τελικο σημειο της συμβολοσειρας ειναι ιδια
            }                                                                        //με αυτα που του εχω περασει ως ορισματα
        }
        return "";
    }

    private void updateChessWithMoves(String start, String end) {
        int count = gridLayoutId.getChildCount();

        for (int i = 0; i < count; i++) {
            Button b1 = (Button) gridLayoutId.getChildAt(i);
            String tagX = b1.getTag().toString().split(",")[0];   //παιρνω τα tags του καθε κουμπιου τα οποια αντιπροσωπευουν τις συντεταγμενες
            String tagY = b1.getTag().toString().split(",")[1];
            for (int k = 0; k < final_list.size(); k++) {              //αυτο το λοοπ είναι για να βάζω το χρώμα στις κινήσεις που θα κάνει


                String[] sec = final_list.get(k).split("-");

                for (int h = sec.length - 1; h >= 0; h--) {
                    String currentPosition = (Integer.parseInt(tagX) - 1) + "," + (Integer.parseInt(tagY) - 1);
                    if (currentPosition.equals(sec[h]) && !currentPosition.equals(end) && !currentPosition.equals(start)) {
                        b1.setBackgroundColor(getResources().getColor(R.color.moves));  //πρεπει να ελεγξω εδω μεσα καθε στοιχειου του
                    }                                                       //grid layout αν ειναι ιδιο με αυτο απο τα μονοπατια
                }                                                           //ετσι ωστε να αλλαξω χρωμα
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
        pathsCardId.setVisibility(View.GONE);
        displayedPathsId.setText("");
        numPaths.setVisibility(View.GONE);
        final_list.clear();
        neighboorsCoord.clear();
        final_listWithNeighboors.clear();
        twoSelectedPoint.clear();
        startPointX=-1; startPointY=-1; endPointY=-1; endPointX=-1;
    }

    private void setChessColors(int chessRow, Button b1) {
        if (unEvenNums_table.contains(chessRow)) {             //εδω απλα σχεδιαζω το ασπρο-μαυρο του σκακιου
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

    public String removeLastCharacter(String str) {             //αυτη η συναρτηση απλα αφαιρει το τελευταιο χαρακτηρα ενος string
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == '|') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    private void initFindIds() {
        findPathsbtn = findViewById(R.id.findPathsId);
        gridLayoutId = findViewById(R.id.gridLayoutId);
        resetbtn = findViewById(R.id.resetId);
        numPaths = findViewById(R.id.numPathsId);
        displayedPathsId = findViewById(R.id.displayedPathsId);
        showCoorbtn=findViewById(R.id.showCoor);
        pathsCardId=findViewById(R.id.pathsCardId);
    }
}





