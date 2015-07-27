package com.movile.up.seriestracker.fragments;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.business.presenters.ShowDetailsPresenter;
import com.movile.up.seriestracker.business.presenters.ShowFragmentDetailsPresenter;
import com.movile.up.seriestracker.interfaces.view.ShowFragmentDetailsView;
import com.movile.up.seriestracker.util.FormatUtil;
import com.movile.up.seriestracker.util.InformationKeys;
import com.movile.up.seriestracker.interfaces.view.ShowDetailsView;
import com.movile.up.seriestracker.model.models.Show;

import java.util.Date;


public class ShowInformationFragment extends Fragment implements ShowFragmentDetailsView {
    private static  final String TAG = ShowInformationFragment.class.getSimpleName();
    private View root;
    private ShowFragmentDetailsPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_show_information, container, false);
        presenter = new ShowFragmentDetailsPresenter(getActivity(),this);
        String show = this.getArguments().getString(InformationKeys.SHOW_SLUG);
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

        Date episodeDate = FormatUtil.formatDate(show.firstAired());
        String showBeginTime = FormatUtil.formatDate(episodeDate);

        showHomepage.setText(showHomepage.getText().toString().concat(" "+show.network().toString()));
        showEpisodesNumber.setText(showEpisodesNumber.getText().toString().concat(" "+Long.toString(show.airedEpisodes())));
        showCountry.setText(showCountry.getText().toString().concat(" "+show.country()));
        startedShowDate.setText(startedShowDate.getText().toString().concat(" "+showBeginTime));
        showStatus.setText(showStatus.getText().toString().concat(" "+show.status()));
        showLanguage.setText(showLanguage.getText().toString().concat(" "+show.language()));
        description.setText(description.getText().toString().concat(" "+show.overview()));
    }


}
