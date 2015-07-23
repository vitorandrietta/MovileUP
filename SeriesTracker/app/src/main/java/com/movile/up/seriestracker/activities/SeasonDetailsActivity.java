package com.movile.up.seriestracker.activities;

import android.content.Intent;
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
import com.movile.up.seriestracker.business.adapters.listadapters.EpisodeListAdapter;
import com.movile.up.seriestracker.business.presenters.SeasonDetailsPresenter;
import com.movile.up.seriestracker.configuration.ImageTypes;
import com.movile.up.seriestracker.configuration.InformationKeys;
import com.movile.up.seriestracker.interfaces.view.EpisodeItemListClickListener;
import com.movile.up.seriestracker.interfaces.view.SeasonDetailsView;
import com.movile.up.seriestracker.model.models.Episode;
import com.movile.up.seriestracker.model.models.Season;

import java.util.List;




public class SeasonDetailsActivity extends BaseNavigationToolbarActivity  implements SeasonDetailsView, EpisodeItemListClickListener {



    private ListView episodeList;
    private EpisodeListAdapter episodeListAdapter;
    private SeasonDetailsPresenter presenter;
    private long seasonNumber;
    private String show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_details);
        this.showLoading();
        this.configureToolbar();
        episodeList = (ListView) findViewById(R.id.seasonEpisodesList);
        View header = getLayoutInflater().inflate(R.layout.season_header_layout,null);
        episodeList.addHeaderView(header);
        presenter = new SeasonDetailsPresenter(this,this);
        episodeListAdapter = new EpisodeListAdapter(this,R.layout.episode_list_item_view,this);
        this.episodeList.setAdapter(episodeListAdapter);
        Intent intent = getIntent();
        this.seasonNumber = intent.getLongExtra(InformationKeys.SEASON,0l);
        this.show = intent.getStringExtra(InformationKeys.SHOW);
        presenter.presentSeason(show,seasonNumber);
        String actionBarTitle = show.concat(" S".concat(Long.toString(seasonNumber)));
        getSupportActionBar().setTitle(actionBarTitle.replaceAll("-"," "));
        this.episodeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SeasonDetailsActivity.this.performItemClickAction(l);
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

        Glide
                .with(this).
                load(season.images().poster().get(ImageTypes.IMAGE_FULL)).centerCrop().
                into(seasonImage);

        ImageView seasonThumbnail = (ImageView) findViewById(R.id.seasonThumbnail);;

        Glide.with(this).
               load(season.images().thumb().get(ImageTypes.IMAGE_FULL)).
                centerCrop().
               into(seasonThumbnail);

        TextView rating = (TextView) findViewById(R.id.seasonRating);
        rating.setText(String.format("%.1f",season.rating()));
        this.hideLoading();
    }

    @Override
    public void displaySeasonEpisodes(List<Episode> episodeDetails) {
        this.episodeListAdapter.notifyListChanged(episodeDetails);

    }


    @Override
    public void performItemClickAction(long episode) {
        if(episode==-1){
            return;
        }

        Intent intent = new Intent(SeasonDetailsActivity.this,EpisodeDetailsActivity.class);
        intent.putExtra(InformationKeys.SHOW,this.show);
        intent.putExtra(InformationKeys.SEASON,this.seasonNumber);
        intent.putExtra(InformationKeys.EPISODE, episode + 1L);
        startActivity(intent);

    }
}
