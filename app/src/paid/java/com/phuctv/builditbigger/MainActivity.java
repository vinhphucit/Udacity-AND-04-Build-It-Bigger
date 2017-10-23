package com.phuctv.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.phuctv.builditbigger.tasks.ApiTask;
import com.phuctv.jokeshower.JokeDisplayActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnTellJoke;
    private ApiTask.TellJokeCallback callback;
    private View mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callback = new ApiTask.TellJokeCallback() {

            @Override
            public void onLoadJoke() {
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void tellJoke(String joke) {
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
                Intent intent = new Intent(MainActivity.this, JokeDisplayActivity.class);
                intent.putExtra(JokeDisplayActivity.ARGS_JOKE, joke);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        };
        mProgressBar = findViewById(R.id.progressbar);
        btnTellJoke = (Button) findViewById(R.id.btnTellJoke);

        btnTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ApiTask(callback).execute();
            }
        });
    }
}
