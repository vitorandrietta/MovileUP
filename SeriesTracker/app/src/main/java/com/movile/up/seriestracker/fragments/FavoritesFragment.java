package com.movile.up.seriestracker.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.business.adapters.cursoradapters.FavoritesAdapter;
import com.movile.up.seriestracker.business.presenters.FragmentFavoritesDetailsPresenter;
import com.movile.up.seriestracker.interfaces.view.FavoritesFragmentDetailsView;

public class FavoritesFragment extends Fragment implements FavoritesFragmentDetailsView {

    private FavoritesAdapter favoriteAdapter;
    private View listHeaderView;
    private FragmentFavoritesDetailsPresenter presenter;
    private View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        ListView favoritesList = (ListView) root.findViewById(R.id.favoriteList);
        listHeaderView = inflater.inflate(R.layout.favorite_list_header_layout, null);
        favoritesList.addHeaderView(listHeaderView);
        favoriteAdapter = new FavoritesAdapter(getActivity(),null,0);
        favoritesList.setAdapter(favoriteAdapter);
        presenter = new FragmentFavoritesDetailsPresenter(this,getActivity(),getActivity().
        getSupportLoaderManager());
        this.root = root;
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.presentFavorites();
    }

    @Override
    public void displayFavorites(Cursor favoritesQuery) {
        ImageView tvHeaderImage = (ImageView) this.listHeaderView.findViewById(R.id.tv_favorite_image);
        TextView notFoundInformation = (TextView) this.root.findViewById(R.id.not_found_favorites_text);

        if(favoritesQuery.moveToFirst()){
           tvHeaderImage.setImageResource(R.drawable.favorites_header_tv_happy);
            notFoundInformation.setVisibility(View.INVISIBLE);
        }
        else{
            tvHeaderImage.setImageResource(R.drawable.favorites_header_tv_unhappy);

            notFoundInformation.setVisibility(View.VISIBLE);
        }

        this.favoriteAdapter.update(favoritesQuery);
    }
}
