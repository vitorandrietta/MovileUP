package com.movile.up.seriestracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.business.presenters.SeasonsFragmentPresenter;
import com.movile.up.seriestracker.business.adapters.recyclerviewadapters.SeasonsRecyclerAdapter;
import com.movile.up.seriestracker.configuration.InformationKeys;
import com.movile.up.seriestracker.interfaces.view.ShowSeasonsView;
import com.movile.up.seriestracker.model.models.Season;

import java.util.List;

public class ShowSeasonsFragment extends Fragment implements ShowSeasonsView {

    private SeasonsRecyclerAdapter seasonsAdapter;
    private SeasonsFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_seasons, container, false);
        RecyclerView recyclerView = (RecyclerView)  view.findViewById(R.id.recycler_view_seasons);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        String show = savedInstanceState.getString(InformationKeys.SHOW);
        seasonsAdapter = new SeasonsRecyclerAdapter(getActivity(),R.layout.season_item_layout,show);
        recyclerView.setAdapter(seasonsAdapter);
        presenter = new SeasonsFragmentPresenter(this,getActivity());
        presenter.processSeasons(show);
        return view;

    }


    @Override
    public void displaySeasons(List<Season> seasons) {
        seasonsAdapter.updateContents(seasons);
    }


}
