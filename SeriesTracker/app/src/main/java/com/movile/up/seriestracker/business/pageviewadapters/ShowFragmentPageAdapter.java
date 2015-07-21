package com.movile.up.seriestracker.business.pageviewadapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.movile.up.seriestracker.fragments.ShowInformationFragment;
import com.movile.up.seriestracker.fragments.ShowSeasonsFragment;

/**
 * Created by android on 7/21/15.
 */
public class ShowFragmentPageAdapter extends FragmentPagerAdapter {


    public ShowFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //trocar para constante
        if(position==0){
            return new ShowInformationFragment();
        }

        if(position==1){
            return  new ShowSeasonsFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position ==1 ){
            return "INFO";
        }
        else{
            return "SEASONS";
        }
    }
}

