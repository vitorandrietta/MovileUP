package com.movile.up.seriestracker.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import com.movile.up.seriestracker.business.presenters.ShowDetailsPresenter;
import com.movile.up.seriestracker.database_dbflow.FavoriteEntity;
import com.movile.up.seriestracker.interfaces.view.FavButtonClick;
import com.movile.up.seriestracker.util.ImageTypes;
import com.movile.up.seriestracker.util.InformationKeys;
import com.movile.up.seriestracker.util.Status;
import com.movile.up.seriestracker.interfaces.view.ShowDetailsView;
import com.movile.up.seriestracker.model.models.Show;


public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements ShowDetailsView ,FavButtonClick {


    private ShowDetailsPresenter presenter;
    private String showSlug;
    private String showTitle;
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
        showSlug = intent.getStringExtra(InformationKeys.SHOW_SLUG);
        showTitle = intent.getStringExtra(InformationKeys.SHOW_TITLE);
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

        presenter.loadFavoriteButton(new FavoriteEntity(this.showSlug,this.showTitle),this);
        this.hideLoading();

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
          this.favoriteButton.
          setImageResource(R.drawable.show_details_favorite_on);
          this.favoriteButton.setBackgroundTintList
                  (getResources().getColorStateList(R.color.default_color_second));
        }
        else {
          this.favoriteButton.setImageResource(R.drawable.show_details_favorite_off);
          this.favoriteButton. setBackgroundTintList(getResources().getColorStateList
                  (R.color.default_color_third));
        }


    }

    @Override
    public void loadButtonFirstState(FavoriteEntity entity) {
        this.favoriteButtonState = (entity != null);
        this.changeButtonVisualState(this.favoriteButtonState);
    }

    @Override
    public void onFavButtonClickCallback() {
        this.favoriteButtonState = !this.favoriteButtonState;
        this.favoriteButtonAnimationTransition(this.favoriteButtonState);

        if(this.favoriteButtonState){
            new InsertFavoriteTask().execute(new FavoriteEntity(this.showSlug, this.showTitle));
        }
        else{
           new DeleteFavoriteTask().execute(new FavoriteEntity(this.showSlug, this.showTitle));
        }
    }

    @Override
    public void favoriteButtonAnimationTransition(final boolean state) {
        ObjectAnimator scaleXAnimationHide = ObjectAnimator.ofFloat
                (this.favoriteButton,View.SCALE_X,1f,0f);
        scaleXAnimationHide.setDuration(150L);

        scaleXAnimationHide.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                ShowDetailsActivity.this.changeButtonVisualState(state);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ShowDetailsActivity.this.changeButtonVisualState(state);
                ObjectAnimator scaleXAnimationShow = ObjectAnimator.
                        ofFloat(ShowDetailsActivity.this.favoriteButton,View.SCALE_X,0f,1f);
                scaleXAnimationShow.setDuration(150L);
                scaleXAnimationShow.start();

            }
        });

        scaleXAnimationHide.start();
    }
}
