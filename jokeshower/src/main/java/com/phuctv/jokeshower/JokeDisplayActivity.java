package com.phuctv.jokeshower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class JokeDisplayActivity extends AppCompatActivity {
    public static final String ARGS_JOKE = "ARGS_JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        String joke = getIntent().getStringExtra(ARGS_JOKE);
        TextView textViewJoke = (TextView) findViewById(R.id.tv_joke);
        textViewJoke.setText(joke);
    }
}
