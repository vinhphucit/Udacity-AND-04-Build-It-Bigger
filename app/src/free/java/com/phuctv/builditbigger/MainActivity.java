package com.phuctv.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.phuctv.builditbigger.tasks.ApiTask;
import com.phuctv.jokeshower.JokeDisplayActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnTellJoke;
    private ApiTask.TellJokeCallback callback;
    private InterstitialAd mInterstitialAd;
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
            public void tellJoke(final String joke) {

                mInterstitialAd = new InterstitialAd(MainActivity.this);
                mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        mInterstitialAd.show();
                        if (mProgressBar != null) {
                            mProgressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        super.onAdFailedToLoad(errorCode);
                        if (mProgressBar != null) {
                            mProgressBar.setVisibility(View.GONE);
                        }
                        loadResult(joke);
                    }

                    @Override
                    public void onAdClosed() {
                        loadResult(joke);
                    }
                });

                AdRequest ar = new AdRequest
                        .Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .build();
                mInterstitialAd.loadAd(ar);
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

    private void loadResult(String joke) {

        Intent intent = new Intent(MainActivity.this, JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.ARGS_JOKE, joke);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}
