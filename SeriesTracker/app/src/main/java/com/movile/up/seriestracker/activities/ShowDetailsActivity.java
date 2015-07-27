package com.movile.up.seriestracker.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activities.support.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.business.adapters.pageradapters.ShowFragmentPageAdapter;
import com.movile.up.seriestracker.business.assynctask.DeleteFavoriteTask;
import com.movile.up.seriestracker.business.assynctask.InsertFavoriteTask;
import com.movile.up.seriestracker.business.assynctask.IsFavoriteTask;
import com.movile.up.seriestracker.business.presenters.ShowDetailsPresenter;
import com.movile.up.seriestracker.interfaces.view.FavButtonClick;
import com.movile.up.seriestracker.util.ImageTypes;
import com.movile.up.seriestracker.util.InformationKeys;
import com.movile.up.seriestracker.util.Status;
import com.movile.up.seriestracker.interfaces.view.ShowDetailsView;
import com.movile.up.seriestracker.model.models.Show;


public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements ShowDetailsView ,FavButtonClick {


    private ShowDetailsPresenter presenter;
    private String showSlug;
    private FloatingActionButton favoriteButton;
    private boolean favoriteButtonState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        this.configureToolbar();
        this.showLoading();
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        Intent intent = getIntent();
        showSlug = intent.getStringExtra(InformationKeys.SHOW);
        ShowFragmentPageAdapter showViewPagerAdapter = new ShowFragmentPageAdapter(getSupportFragmentManager(),showSlug);
        pager.setAdapter(showViewPagerAdapter);
        presenter = new ShowDetailsPresenter(this,this);
        presenter.processShow(showSlug);
        getSupportActionBar().setTitle(showSlug.replaceAll("-", " "));
        favoriteButton = (FloatingActionButton) findViewById(R.id.show_details_favorite);


        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDetailsActivity.this.onFavButtonClickCallback();
            }
        });

        new IsFavoriteTask(this).execute(this.showSlug);
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

    @Override
    public void changeButtonVisualState(boolean state) {
        if(state) {
            this.favoriteButton.setImageResource(R.drawable.show_details_favorite_on);
            this.favoriteButton.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_second));
        }

        else {
            this.favoriteButton.setImageResource(R.drawable.show_details_favorite_off);
            this.favoriteButton.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_third));
        }
    }

    @Override
    public void setButtonState(boolean state) {
        this.favoriteButtonState = state;
    }


    @Override
    public void onFavButtonClickCallback() {
        this.favoriteButtonState = !this.favoriteButtonState;
        this.changeButtonVisualState(this.favoriteButtonState);

        if(this.favoriteButtonState){
            new InsertFavoriteTask().execute(this.showSlug);
        }
        else{
           new DeleteFavoriteTask().execute(this.showSlug);
        }
    }
}
