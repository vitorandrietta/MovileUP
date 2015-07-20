package com.movile.up.seriestracker.interfaces.rest;

import com.movile.up.seriestracker.configuration.ApiConfiguration;
import com.movile.up.seriestracker.model.models.Episode;
import com.movile.up.seriestracker.model.models.Season;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by root on 20/07/15.
 */
public interface SeasonRemoteService {
    @Headers({

            "trakt-api-version: "+ ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}/seasons/{season}?extended=episodes,images")
    void getSeasonDetails(
            @Path("show") String show,
            @Path("season") long season,
            Callback<Season> callback);
}
