package com.movile.up.seriestracker.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.business.adapters.gridadapters.GridShowAdapter;
import com.movile.up.seriestracker.business.presenters.ShowsDetailsPresenter;
import com.movile.up.seriestracker.business.services.UpdateService;
import com.movile.up.seriestracker.interfaces.callback.presenter.ShowsPresenter;
import com.movile.up.seriestracker.interfaces.view.ShowsDetailsView;
import com.movile.up.seriestracker.model.models.Show;

import java.util.List;

public class ShowsActivity extends AppCompatActivity implements ShowsDetailsView {

    private GridShowAdapter showAdapter;
    private ShowsDetailsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows);
        showAdapter = new GridShowAdapter(this,R.layout.show_image_layout);
        GridView showsGrid = (GridView) findViewById(R.id.shows_grid_view);
        showsGrid.setAdapter(showAdapter);
      //  presenter =  new ShowsDetailsPresenter(this,this);
     //   presenter.processShows();

        PendingIntent pendingIntent = PendingIntent.getService(this, 0, new Intent(this, UpdateService.class), 0);
        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP,100, 1, pendingIntent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_shows, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayShowsDetails(List<Show> shows) {
        this.showAdapter.updateGrid(shows);
    }
}
