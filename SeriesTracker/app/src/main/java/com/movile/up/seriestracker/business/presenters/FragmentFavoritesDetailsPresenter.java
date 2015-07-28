package com.movile.up.seriestracker.business.presenters;


import android.content.Context;
import android.support.v4.app.LoaderManager;


import com.movile.up.seriestracker.business.loadercallback.FavoritesLoaderCallback;
import com.movile.up.seriestracker.interfaces.callback.presenter.FragmentFavoritesPresenter;
import com.movile.up.seriestracker.interfaces.view.FavoritesFragmentDetailsView;

/**
 * Created by root on 27/07/15.
 */
public class FragmentFavoritesDetailsPresenter implements FragmentFavoritesPresenter {

    private Context context;
    private FavoritesFragmentDetailsView favoritesFragmentDetailsView;
    private LoaderManager loaderManager;

    public FragmentFavoritesDetailsPresenter(FavoritesFragmentDetailsView
                                                fragmentDetailsView,Context context, LoaderManager loaderManager){
        this.favoritesFragmentDetailsView = fragmentDetailsView;
        this.context = context;
        this.loaderManager = loaderManager;
    }

    @Override
    public void presentFavorites() {
        this.loaderManager.initLoader(
           0, null, new FavoritesLoaderCallback(this.favoritesFragmentDetailsView, this.context)
        ).forceLoad();
    }
}
