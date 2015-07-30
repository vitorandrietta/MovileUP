package com.movile.up.seriestracker.activities;

import android.nfc.Tag;
import android.os.Bundle;
import android.widget.GridView;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.OneoffTask;
import com.google.android.gms.gcm.Task;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activities.support.BaseNavigationDrawer;
import com.movile.up.seriestracker.business.adapters.gridadapters.GridShowAdapter;
import com.movile.up.seriestracker.business.presenters.ShowsDetailsPresenter;
import com.movile.up.seriestracker.business.services.gcm.GcmUpdateTrigger;
import com.movile.up.seriestracker.interfaces.view.ShowsDetailsView;
import com.movile.up.seriestracker.model.models.Show;

import java.util.List;

public class ShowsActivity extends BaseNavigationDrawer implements ShowsDetailsView {

    private GridShowAdapter showAdapter;
    private ShowsDetailsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows);
        this.configureNavigation();

        showAdapter = new GridShowAdapter(this,R.layout.show_image_layout);
        GridView showsGrid = (GridView) findViewById(R.id.shows_grid_view);
        showsGrid.setAdapter(showAdapter);
        presenter =  new ShowsDetailsPresenter(this,this);
        presenter.processShows();

        OneoffTask updateTask = new OneoffTask.Builder()
                .setService(GcmUpdateTrigger.class)
                .setTag("lalalal")
                .setExecutionWindow(2,3)
                .setUpdateCurrent(true)
                .setRequiredNetwork(Task.NETWORK_STATE_CONNECTED).build();

        GcmNetworkManager.getInstance(this).schedule(updateTask);


    }

    @Override
    public void displayShowsDetails(List<Show> shows) {
        this.showAdapter.updateGrid(shows);
    }
}
