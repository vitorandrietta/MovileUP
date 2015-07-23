package com.movile.up.seriestracker.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.business.adapters.pageradapters.ShowFragmentPageAdapter;
import com.movile.up.seriestracker.business.adapters.recyclerviewadapters.SeasonsRecyclerAdapter;
import com.movile.up.seriestracker.business.presenters.ShowDetailsPresenter;
import com.movile.up.seriestracker.configuration.ImageTypes;
import com.movile.up.seriestracker.configuration.InformationKeys;
import com.movile.up.seriestracker.configuration.Status;
import com.movile.up.seriestracker.interfaces.callback.presenter.ShowPresenter;
import com.movile.up.seriestracker.interfaces.view.ShowDetailsView;
import com.movile.up.seriestracker.model.models.Show;


public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements ShowDetailsView {


    private ShowDetailsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        this.configureToolbar();
        this.showLoading();
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        Intent intent = getIntent();
        String showSlug = intent.getStringExtra(InformationKeys.SHOW);
        ShowFragmentPageAdapter showViewPagerAdapter = new ShowFragmentPageAdapter(getSupportFragmentManager(),showSlug);
        pager.setAdapter(showViewPagerAdapter);
        presenter = new ShowDetailsPresenter(this,this);
        presenter.processShow(showSlug);
        getSupportActionBar().setTitle(showSlug.replaceAll("-"," "));
        this.hideLoading();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_details, menu);
        return true;
    }

    @Override
    public void displayShow(Show show) {

        if(show.status().equalsIgnoreCase(Status.SEASON_ENDED)){
            FrameLayout endedCard = (FrameLayout) findViewById(R.id.ended_card);
            endedCard.setVisibility(View.VISIBLE);
        }

        TextView rating = (TextView) findViewById(R.id.showRating);
        TextView publishedYear = (TextView) findViewById(R.id.showYear);
        ImageView showImage = (ImageView) findViewById(R.id.showImage);
        rating.setText(String.format("%.1f",show.rating()));
        publishedYear.setText(show.year().toString());
        Glide.with(this)
                .load(show.images().thumb().get(ImageTypes.IMAGE_FULL))
                .centerCrop()
                .into(showImage);
    }
}
