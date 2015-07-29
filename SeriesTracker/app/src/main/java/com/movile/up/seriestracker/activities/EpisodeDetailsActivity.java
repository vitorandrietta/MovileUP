package com.movile.up.seriestracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activities.support.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.business.presenters.EpisodeDetailsPresenter;
import com.movile.up.seriestracker.util.FormatUtil;
import com.movile.up.seriestracker.util.ImageTypes;
import com.movile.up.seriestracker.util.InformationKeys;
import com.movile.up.seriestracker.interfaces.callback.presenter.EpisodePresenter;
import com.movile.up.seriestracker.interfaces.view.EpisodeDetailsView;
import com.movile.up.seriestracker.model.models.Episode;

import java.util.Date;


public class EpisodeDetailsActivity extends BaseNavigationToolbarActivity implements EpisodeDetailsView {

    private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();
    private EpisodePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_details);
        this.configureToolbar();
        this.showLoading();
        Intent intent = getIntent();
        String show = intent.getStringExtra(InformationKeys.SHOW_SLUG);
        Long season = intent.getLongExtra(InformationKeys.SEASON, 0L);
        Long episodes = intent.getLongExtra(InformationKeys.EPISODE,0L);
        String actionBarTitle = ("S").concat(season.toString()).concat("E".concat(episodes.toString()));
        getSupportActionBar().setTitle(actionBarTitle);
        presenter = new EpisodeDetailsPresenter(this,this);
        presenter.presentEpisode(show, season, episodes);
    }


    @Override
    public void displayEpisode(Episode episode) {
        Date episodeDate = FormatUtil.formatDate(episode.firstAired());
        String episodeFormatedBeginTime = FormatUtil.formatDate(episodeDate);
        TextView episodeDescriptionText = (TextView) findViewById(R.id.episodeDescription);
        TextView episodeTitleText = (TextView) findViewById(R.id.episodeTitle);
        TextView episodeBeginTimeText = (TextView) findViewById(R.id.episodeBeginTime);
        episodeDescriptionText.setText(episode.overview());
        episodeTitleText.setText(episode.title());
        episodeBeginTimeText.setText(episodeFormatedBeginTime);

        String episodeImageUrl = episode.images().screenshot().get(ImageTypes.IMAGE_FULL);


        ImageView episodeImage = (ImageView)findViewById(R.id.episodeImage);
        Glide.with(this)
                .load(episodeImageUrl)
                .into(episodeImage);
        this.hideLoading();
    }
}
