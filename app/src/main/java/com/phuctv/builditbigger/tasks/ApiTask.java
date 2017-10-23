package com.phuctv.builditbigger.tasks;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.phuctran.backend.jokeapi.Jokeapi;
import com.phuctv.builditbigger.BuildConfig;

import java.io.IOException;

/**
 * Created by phuctran on 10/20/17.
 */

public class ApiTask extends AsyncTask<Void, Void, String> {
    private final TellJokeCallback mTellJokeCallback;
    private Jokeapi mJokeApi;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mTellJokeCallback != null)
            mTellJokeCallback.onLoadJoke();
    }

    @Override
    protected void onPostExecute(String jokeResponse) {
        super.onPostExecute(jokeResponse);
        if (mTellJokeCallback != null)
            mTellJokeCallback.tellJoke(jokeResponse);
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            return mJokeApi.tellJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface TellJokeCallback {
        void onLoadJoke();

        void tellJoke(String joke);
    }

    public ApiTask(TellJokeCallback tellJokeCallback) {
        this.mTellJokeCallback = tellJokeCallback;
        if (mJokeApi == null) {
            Jokeapi.Builder builder = new Jokeapi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(BuildConfig.API_URL);
            mJokeApi = builder.build();
        }
    }
}
