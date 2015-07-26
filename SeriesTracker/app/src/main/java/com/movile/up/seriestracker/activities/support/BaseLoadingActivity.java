package com.movile.up.seriestracker.activities.support;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.movile.up.seriestracker.R;

public class BaseLoadingActivity extends AppCompatActivity {

    public void showLoading() {
        findViewById(R.id.base_loading_container).setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        findViewById(R.id.base_loading_container).setVisibility(View.GONE);
    }

}
