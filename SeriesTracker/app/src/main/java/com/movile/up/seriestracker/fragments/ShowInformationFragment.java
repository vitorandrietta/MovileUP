package com.movile.up.seriestracker.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.business.presenters.ShowDetailsPresenter;
import com.movile.up.seriestracker.configuration.InformationKeys;
import com.movile.up.seriestracker.interfaces.callback.presenter.ShowPresenter;
import com.movile.up.seriestracker.interfaces.view.ShowDetailsView;
import com.movile.up.seriestracker.model.models.Show;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ShowInformationFragment extends Fragment implements ShowDetailsView {
    private static  final String TAG = ShowInformationFragment.class.getSimpleName();
    private View root;
    private ShowDetailsPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_show_information, container, false);
        presenter = new ShowDetailsPresenter(getActivity(),this);
        String show = savedInstanceState.getString(InformationKeys.SHOW);
        presenter.processShow(show);
        return  this.root;

    }

    @Override
    public void displayShow(Show show) {
        TextView description = (TextView) root.findViewById(R.id.showDescription);
        LinearLayout genresOutContainer = (LinearLayout) root.findViewById(R.id.showGenres);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        TextView showHomepage = (TextView) root.findViewById(R.id.showHomepage);
        TextView showEpisodesNumber = (TextView) root.findViewById(R.id.showEpisodesNumber);
        TextView showCountry = (TextView) root.findViewById(R.id.showCountry);
        TextView startedShowDate = (TextView) root.findViewById(R.id.startedShowDate);
        TextView showStatus = (TextView) root.findViewById(R.id.showStatus);
        TextView showLanguage = (TextView) root.findViewById(R.id.showlanguage);

        LinearLayout container = (LinearLayout) inflater.inflate(R.layout.genre_container_layout,null);
        int cont = 0;
        for(String genre:show.genres()){

            if(cont==3){
                genresOutContainer.addView(container);
                container = (LinearLayout) inflater.inflate(R.layout.genre_container_layout,null);

            }

            View cardGenre = inflater.inflate(R.layout.genre_card_layout,null);
            TextView genreText = (TextView) cardGenre.findViewById(R.id.genreCard);
            genreText.setText(genre);
            container.addView(cardGenre);
            cont++;
        }
        if(cont%3!=0){
            genresOutContainer.addView(container);
        }

        SimpleDateFormat utcToDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date showDate = null;
        String showBeginTime=null;

        try {
             showDate = utcToDateFormat.parse(show.firstAired());
             showBeginTime= new SimpleDateFormat("yyyy").format(showDate);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "Error converting episode begining time to datetime");
        }


        showHomepage.setText(showHomepage.getText().toString().concat(" "+show.network().toString()));
        showEpisodesNumber.setText(showEpisodesNumber.getText().toString().concat(" "+Long.toString(show.airedEpisodes())));
        showCountry.setText(showCountry.getText().toString().concat(" "+show.country()));
        startedShowDate.setText(startedShowDate.getText().toString().concat(" "+showBeginTime));
        showStatus.setText(showStatus.getText().toString().concat(" "+show.status()));
        showLanguage.setText(showLanguage.getText().toString().concat(" "+show.language()));
        description.setText(description.getText().toString().concat(" "+show.overview()));
    }
}
