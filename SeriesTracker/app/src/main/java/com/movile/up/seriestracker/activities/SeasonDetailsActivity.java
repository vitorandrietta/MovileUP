package com.movile.up.seriestracker.activities;

import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.business.holders.EpisodeListItemHolder;
import com.movile.up.seriestracker.interfaces.view.SeasonDetailsView;
import com.movile.up.seriestracker.model.models.Season;

import java.util.ArrayList;


public class SeasonDetailsActivity extends ActionBarActivity implements SeasonDetailsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_details);
        ListView view = (ListView) findViewById(R.id.seasonEpisodesList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_season_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displaySeasonDetails(Season season) {
        ImageView seasonImage = (ImageView) findViewById(R.id.seasonImage);

        Glide.with(this).
            load(season.images().poster().get("full")).
                into(seasonImage);

        ImageView seasonThumbnail = (ImageView) findViewById(R.id.seasonThumbnail);;

        Glide.with(this).
                load(season.images().poster().get("full")).
                into(seasonThumbnail);

        TextView rating = (TextView) findViewById(R.id.seasonRating);
        rating.setText(Double.toString(season.rating()));

    }

    @Override
    public void displaySeasonEpisodes(EpisodeListItemHolder holder, ArrayList<String> episodeDetails, int pos) {
        String episodeNumber = "E".concat(Integer.toString(pos));
        holder.getEpisodeNumber().setText(episodeNumber);

        String episodeTitle = episodeDetails.get(pos);
        holder.getEpisodeTitle().setText(episodeTitle);
    }

}
