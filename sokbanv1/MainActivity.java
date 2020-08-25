package com.example.sokbanv1;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.AdapterView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Game game;
    private boolean running = true;
    int seconds = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
        game.addLevel("Level3", 5, 7,
                "#######" +
                        "#+x...#" +
                        "#..w..#" +
                        "#..+x.#" +
                        "#######");
        game.addLevel("Level2", 5, 6,
                "######" +
                        "#+x..#" +
                        "#..w.#" +
                        "#.++.#" +
                        "######");
        game.addLevel("Level1", 5, 6,
                "######" +
                        "#+x..#" +
                        "#..w.#" +
                        "#....#" +
                        "######");
        makeButton();
        updateScore();
        updateLevel();
        elapseTimer();


    }

    public void makeButton() {
        final GridView gridView = (GridView) findViewById(R.id.levelDisplay);

        final Button upButton = (Button) findViewById(R.id.up);
        upButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.move(Direction.UP);
                updateLevel();
            }
        });
        final Button downButton = (Button) findViewById(R.id.down);
        downButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.move(Direction.DOWN);
                updateLevel();
            }
        });
        final Button leftButton = (Button) findViewById(R.id.left);
        leftButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.move(Direction.LEFT);
                updateLevel();
            }
        });
        final Button rightButton = (Button) findViewById(R.id.right);
        rightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.move(Direction.RIGHT);
                updateLevel();
            }
        });
        Button levelOneButton = (Button) findViewById(R.id.levelOneButton);
        levelOneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.setCurrentLevel(3);
                updateLevel();
            }
        });
        Button levelTwoButton = (Button) findViewById(R.id.levelTwoButton);
        levelTwoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.setCurrentLevel(2);
                updateLevel();
            }
        });
        Button levelThreeButton = (Button) findViewById(R.id.levelThreeButton);
        levelThreeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.setCurrentLevel(1);
                updateLevel();
            }
        });
        Button pauseButton = (Button) findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                running = !running;
                if (running == false) {
                    gridView.setVisibility(View.INVISIBLE);
                    upButton.setVisibility(View.INVISIBLE);
                    downButton.setVisibility(View.INVISIBLE);
                    rightButton.setVisibility(View.INVISIBLE);
                    leftButton.setVisibility(View.INVISIBLE);
                } else {
                    gridView.setVisibility(View.VISIBLE);
                    upButton.setVisibility(View.VISIBLE);
                    downButton.setVisibility(View.VISIBLE);
                    rightButton.setVisibility(View.VISIBLE);
                    leftButton.setVisibility(View.VISIBLE);
                }
            }
        });
        final MediaPlayer gameSound = MediaPlayer.create(this, R.raw.game_music);
        Switch musicSwitch = (Switch) findViewById(R.id.musicSwitch);
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gameSound.start();
                } else {
                    gameSound.pause();
                }
            }
        });
    }


    public void updateScore() {
        Integer completedCount = game.getCurrentLevel().getCompletedCount();
        Integer moveCount = game.getCurrentLevel().getMoveCount();
        Integer targetCount = game.getCurrentLevel().targetCount;
        String levelName = game.getCurrentLevelName();
        TextView scoreDisplay = (TextView) findViewById(R.id.textView);
        TextView nameDisplay = (TextView) findViewById(R.id.levelName);
        scoreDisplay.setText("" + "Completed Targets " + completedCount + "\nMoves " + moveCount + "\nTargets " + targetCount);
        nameDisplay.setText("" + levelName);


    }

    public void updateLevel() {
        GridView gridView;
        gridView = (GridView) findViewById(R.id.levelDisplay);
        gridView.setNumColumns(game.getCurrentLevel().getWidth() + 1);
        char[] chars = game.getCurrentLevel().getLeveltext().toCharArray();
        String numbers[] = new String[chars.length];
        int i = 0;
        for (char c : chars) {
            numbers[i] = Character.toString(c);
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, numbers);

        gridView.setAdapter(adapter);
        updateScore();

    }

    public void elapseTimer() {
        final TextView timeView
                = findViewById(
                R.id.timer);
        final Handler handler
                = new Handler();

        handler.post(new Runnable() {

            public void run() {
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time
                        = String
                        .format(Locale.getDefault(),
                                "%02d:%02d",
                                minutes, secs);
                timeView.setText(time);

                if (running) {
                    seconds++;

                }
                handler.postDelayed(this, 1000);
            }


        });
    }
}







