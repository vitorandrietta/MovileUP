package com.movile.up.seriestracker.business.adapters.pageradapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.up.seriestracker.configuration.InformationKeys;
import com.movile.up.seriestracker.fragments.ShowInformationFragment;
import com.movile.up.seriestracker.fragments.ShowSeasonsFragment;

/**
 * Created by android on 7/21/15.
 */
public class ShowFragmentPageAdapter extends FragmentPagerAdapter {

    public static final int INFO_OPTION=0;
    public static final int SEASONS_OPTION=1;
    public static final String SEASON ="SEASON";
    public static final String INFO = "INFO";
    private String showName;

    public ShowFragmentPageAdapter(FragmentManager fm,String showName) {
        super(fm);
        this.showName = showName;
    }

    @Override
    public Fragment getItem(int position) {

        Bundle fragmentsParameters = new Bundle();
        fragmentsParameters.putString(InformationKeys.SHOW,this.showName);

        if(position==INFO_OPTION){
            ShowInformationFragment showInformationFragment = new ShowInformationFragment();
            showInformationFragment.setArguments(fragmentsParameters);
            return showInformationFragment;
        }

        if(position==SEASONS_OPTION){
            ShowSeasonsFragment showSeasonsFragment = new ShowSeasonsFragment();
            showSeasonsFragment.setArguments(fragmentsParameters);
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

