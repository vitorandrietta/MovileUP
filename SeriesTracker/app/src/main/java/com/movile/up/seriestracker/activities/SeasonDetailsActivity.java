package com.movile.up.seriestracker.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.business.listadapters.EpisodeListAdapter;
import com.movile.up.seriestracker.business.presenters.SeasonDetailsPresenter;
import com.movile.up.seriestracker.interfaces.view.SeasonDetailsView;
import com.movile.up.seriestracker.model.models.Episode;
import com.movile.up.seriestracker.model.models.Season;

import java.util.ArrayList;
import java.util.List;




public class SeasonDetailsActivity extends ActionBarActivity implements SeasonDetailsView {

    public static final String SHOW ="SHOW";
    public static final String SEASON ="SEASON";
    public static final String EPISODE ="EPISODE";

    private ListView episodeList;
    private EpisodeListAdapter episodeListAdapter;
    private SeasonDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_details);
        episodeList = (ListView) findViewById(R.id.seasonEpisodesList);
        View header = getLayoutInflater().inflate(R.layout.season_header_layout,null);
        episodeList.addHeaderView(header);
        presenter = new SeasonDetailsPresenter(this,this);
        episodeListAdapter = new EpisodeListAdapter(this,R.layout.episode_list_item_view,this);
        this.episodeList.setAdapter(episodeListAdapter);
        presenter.presentSeason("breaking-bad", 3L);
        //setar season e serie aqui
        this.episodeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SeasonDetailsActivity.this,EpisodeDetailsActivity.class);
                intent.putExtra(SHOW,"breaking-bad");
                intent.putExtra(SEASON,3L);
                intent.putExtra(EPISODE,l+1L);
                startActivity(intent);
          }
        });

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

        //Glide.with(this).
          //  load(season.images().poster().get("full")).
            //    into(seasonImage);

        ImageView seasonThumbnail = (ImageView) findViewById(R.id.seasonThumbnail);;

        Glide.with(this).
               load(season.images().poster().get("full")).
              into(seasonThumbnail);

        TextView rating = (TextView) findViewById(R.id.seasonRating);
        rating.setText(String.format("%.1f",season.rating()));



    }

    @Override
    public void displaySeasonEpisodes(List<Episode> episodeDetails) {
        this.episodeListAdapter.notifyListChanged(episodeDetails);
    }


}
