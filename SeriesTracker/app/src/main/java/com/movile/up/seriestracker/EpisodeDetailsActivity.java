package com.movile.up.seriestracker;

import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import com.movile.up.seriestracker.business.OnOperationListener;
import com.movile.up.seriestracker.model.Episode;


public class EpisodeDetailsActivity extends ActionBarActivity implements OnOperationListener<Episode> {

    private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();
    private String estadoSalvo;

    @Override
    public void onOperationSuccess(Episode result) {
        TextView episodeDescription = (TextView) findViewById(R.id.episodeDescription);
        TextView episodeTitle = (TextView) findViewById(R.id.episodeTitle);
        TextView episodeBeginTime = (TextView) findViewById(R.id.episodeBeginTime);

        episodeDescription.setText(result.overview());
        episodeTitle.setText(result.title());
        episodeBeginTime.setText(result.firstAired());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_episode_details);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String msg = savedInstanceState.getString("estadoSalvo");

           Log.d(TAG,msg);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("estadoSalvo", "passei aqui");
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Stop");
        this.estadoSalvo = "passei por aqui";

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Resume");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Restart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Destroy");
        this.estadoSalvo = "passei por aqui";

    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle peristentState) {
        super.onCreate(savedInstanceState, peristentState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.estadoSalvo = "passei por aqui";

    }
}
