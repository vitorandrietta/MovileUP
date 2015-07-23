package com.movile.up.seriestracker.business.adapters.pageradapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.up.seriestracker.fragments.ShowInformationFragment;
import com.movile.up.seriestracker.fragments.ShowSeasonsFragment;

/**
 * Created by android on 7/21/15.
 */
public class ShowFragmentPageAdapter extends FragmentPagerAdapter {

    public static final int INFO_OPTION=0;
    public static final int SEASONS_OPTION=0;

    public ShowFragmentPageAdapter(FragmentManager fm,String showName) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position==0){
            ShowInformationFragment showInformationFragment = new ShowInformationFragment();
            return showInformationFragment;
        }

        if(position==1){
           ShowSeasonsFragment showSeasonsFragment = new ShowSeasonsFragment();
           return showSeasonsFragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position ==INFO_OPTION ){
            return "INFO";
        }
        else{
            return "SEASONS";
        }
    }
}

