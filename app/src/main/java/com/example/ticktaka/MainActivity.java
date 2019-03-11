package com.example.ticktaka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//0-yellow, 1-red; 2-empty
    //array list =different 3 winning positiions

    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};// game state being empty at the different 9 boxes
    int[][] winningposition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};//winning positions
    int activeplayer = 0;
    boolean gameActive = true;

    public void dropin(View view) {
        ImageView counter = (ImageView) view;


        int tappedcounter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[tappedcounter] == 2 && gameActive) {

            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1500);

            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);

                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);

                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winningposition : winningposition) {
                if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]] && gamestate[winningposition[0]] != 2) {
                    //Someone has won

                    gameActive = false;
                    String winner = "";

                    if (activeplayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Button playagain = (Button) findViewById(R.id.playagain);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnertextView);
                    winnerTextView.setText(winner + " has won!");
                    playagain.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);

                }

            }
//        //In case of a draw
// for (int[] winningposition :winningposition){
//    if ((gamestate[winningposition[1]] != gamestate[winningposition[0]] )&& (gamestate[winningposition[0]] == gamestate[winningposition[2]]  && gamestate[winningposition[0]] != 2)) {
//                    gameActive=false;
//
//                    Toast.makeText(this,"Draw game",Toast.LENGTH_LONG).show();
//
//
//                    Button playagain = (Button) findViewById(R.id.playagain);
//                    playagain.setVisibility(View.VISIBLE);
//
//                }
//                }

        }
    }


    public void playagain(View view) {
        Button playagain = (Button) findViewById(R.id.playagain);

        TextView winnerTextView = (TextView) findViewById(R.id.winnertextView);

        playagain.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }
        for (int i = 0; i < gamestate.length; i++) {
            gamestate[i] = 2;

        }
            activeplayer = 0;
            gameActive = true;

        }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
