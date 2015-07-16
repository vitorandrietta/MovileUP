package com.movile.up.seriestracker.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.PersistableBundle;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.business.assynctask.RemoteImageAsyncTask;
import com.movile.up.seriestracker.business.asynctaksloaders.EpisodeLoaderCallback;
import com.movile.up.seriestracker.interfaces.ImageLoader;
import com.movile.up.seriestracker.interfaces.OnOperationListener;
import com.movile.up.seriestracker.model.models.Episode;

import java.text.SimpleDateFormat;
import java.util.Date;


public class EpisodeDetailsActivity extends Activity implements OnOperationListener<Episode>,ImageLoader {

    private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();
    private String estadoSalvo;

    @Override
    public void onOperationSuccess(Episode result) {
        SimpleDateFormat utcToDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date episodeDate = null;
        String episodeFormatedBeginTime=null;
        try {
            episodeDate = utcToDateFormat.parse(result.firstAired());
            episodeFormatedBeginTime = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm").format(episodeDate);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "Error converting episode begining time to datetime");
        }
        TextView episodeDescriptionText = (TextView) findViewById(R.id.episodeDescription);
        TextView episodeTitleText = (TextView) findViewById(R.id.episodeTitle);
        TextView episodeBeginTimeText = (TextView) findViewById(R.id.episodeBeginTime);
        episodeDescriptionText.setText(result.overview());
        episodeTitleText.setText(result.title());
        episodeBeginTimeText.setText(episodeFormatedBeginTime);

        String episodeImageUrl = result.images().screenshot().get("full");
        new RemoteImageAsyncTask(this).execute(episodeImageUrl);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(
                0, null, new EpisodeLoaderCallback(this,this)
        ).forceLoad();
        setContentView(R.layout.activity_episode_details);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String msg = savedInstanceState.getString("estadoSalvo");

        Log.d(TAG, msg);

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

    @Override
    public void LoadImage(Bitmap image) {
        ImageView episodeImage = (ImageView)findViewById(R.id.episodeImage);
        episodeImage.setImageBitmap(image);
    }
}
