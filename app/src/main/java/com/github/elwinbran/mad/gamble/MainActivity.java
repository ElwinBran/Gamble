package com.github.elwinbran.mad.gamble;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Integer lastRollResult = 0;

    private Integer streak = 0;

    private final List<Integer> rollHistory = new ArrayList<>(50);

    private Integer highscore = 0;//TODO get from external persistance.\\

    private RecyclerView.Adapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lastRollResult = roll();
        RecyclerView log = findViewById(R.id.recyclerView);
        this.recyclerViewAdapter = new DiceRollAdapter(getApplicationContext(), this.rollHistory);
        log.setAdapter(this.recyclerViewAdapter);
        log.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        TextView streakDisplay = findViewById(R.id.textView);
        streakDisplay.setText("current streak: " + streak.toString());
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        highscore = prefs.getInt("highscore", 0); // 0 is default
        TextView highscoreDisplay = findViewById(R.id.textView2);
        highscoreDisplay.setText("Highscore: " + highscore.toString());
        //lower bet button
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Integer newNumber = roll();
                int comparison = getLastRollResult().compareTo(newNumber);
                logRoll(newNumber);
                if(comparison > 0)
                {
                    won();

                }
                else
                {
                    lost();
                }
                setLastRollResult(newNumber);
            }
        });

        //higher bet button
        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Integer newNumber = roll();
                int comparison = getLastRollResult().compareTo(newNumber);
                logRoll(newNumber);
                if(comparison < 0)
                {
                    won();
                }
                else
                {
                    lost();
                }
                setLastRollResult(newNumber);
            }
        });
    }

    /**
     * updates and returns new number.
     * @return
     */
    private Integer roll()
    {
        Integer newNumber = diceRoll();
        ImageView diceDisplay = findViewById(R.id.imageView);
        switch(newNumber){
            case 1: diceDisplay.setImageResource(R.drawable.d1);
                break;
            case 2: diceDisplay.setImageResource(R.drawable.d2);
                break;
            case 3: diceDisplay.setImageResource(R.drawable.d3);
                break;
            case 4: diceDisplay.setImageResource(R.drawable.d4);
                break;
            case 5: diceDisplay.setImageResource(R.drawable.d5);
                break;
            case 6: diceDisplay.setImageResource(R.drawable.d6);
                break;
            default: diceDisplay.setImageResource(R.drawable.dinit);
                break;
        }
        return newNumber;
    }

    private void won()
    {
        streak++;
        updateStreakDisplay();
        updateHighscore();
    }

    private void lost()
    {
        streak = 0;
        updateStreakDisplay();
    }

    private void logRoll(Integer rollResult)
    {
        this.rollHistory.add(rollResult);
        this.recyclerViewAdapter.notifyDataSetChanged();
        //RecyclerView recyclerView = findViewById(R.id.recyclerView);
    }

    private void updateStreakDisplay()
    {
        TextView streakDisplay = findViewById(R.id.textView);
        streakDisplay.setText("current streak: " + streak.toString());
    }

    private void updateHighscore()
    {
        if(streak > highscore) {
            highscore = streak;
            SharedPreferences.Editor editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();
            editor.putInt("highscore", highscore);
            editor.apply();
            TextView highscoreDisplay = findViewById(R.id.textView2);
            highscoreDisplay.setText("Highscore: " + highscore.toString());
        }
    }

    private static Integer diceRoll()
    {
        final int lowestNumber = 1;
        final int highestNumber = 6;
        Random r = new Random();
        return r.nextInt((highestNumber + 1) - lowestNumber) + lowestNumber;
    }

    public Integer getLastRollResult()
    {
        return this.lastRollResult;
    }

    public void setLastRollResult(Integer newRollResult)
    {
        this.lastRollResult = newRollResult;
    }
}
