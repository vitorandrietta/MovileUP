package com.movile.up.seriestracker;

import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;


public class EpisodeDetailsActivity extends ActionBarActivity {

    private static final String TAG ="EpisodeDetailsActivity";
    private TextView episodeTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_details);
        this.episodeTitleView = (TextView) findViewById(R.id.episodeTitle);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Stop");
        this.episodeTitleView.setText("Stop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Resume");
        this.episodeTitleView.setText("Resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Restart");
        this.episodeTitleView.setText("Restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Destroy");
        this.episodeTitleView.setText("Destroy");
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
