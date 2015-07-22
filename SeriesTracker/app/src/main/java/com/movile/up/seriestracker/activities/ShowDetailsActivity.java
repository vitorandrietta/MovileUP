package com.movile.up.seriestracker.activities;

import android.graphics.Color;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.business.pageradapters.ShowFragmentPageAdapter;
import com.movile.up.seriestracker.business.recyclerviewadapters.SeasonsRecyclerAdapter;


public class ShowDetailsActivity extends AppCompatActivity {

    private  SeasonsRecyclerAdapter seasonsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);



        ShowFragmentPageAdapter showviewPagerAdapter = new ShowFragmentPageAdapter(getSupportFragmentManager());
        PagerTabStrip tab = (PagerTabStrip) findViewById(R.id.pager_tab);
        tab.setTextColor(Color.WHITE);
        tab.setTabIndicatorColor(Color.WHITE);
        pager.setAdapter(showviewPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_show_details, menu);

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
}
