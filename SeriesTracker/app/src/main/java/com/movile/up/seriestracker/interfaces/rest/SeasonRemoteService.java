package com.movile.up.seriestracker.interfaces.rest;

import com.movile.up.seriestracker.util.ApiConfiguration;
import com.movile.up.seriestracker.model.models.Episode;
import com.movile.up.seriestracker.model.models.Season;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by root on 20/07/15.
 */
public interface SeasonRemoteService {
    @Headers({
            "trakt-api-key: " + ApiConfiguration.API_KEY,
            "trakt-api-version: " + ApiConfiguration.API_VERSION
    })
    @GET("/shows/{show}/seasons/{season}?extended=full,images")
    void getSeasonEpisodes(
            @Path("show") String show,
            @Path("season") long season,
            Callback<List<Episode>> callback);

    @Headers({
            "trakt-api-key: " + ApiConfiguration.API_KEY,
            "trakt-api-version: " + ApiConfiguration.API_VERSION
    })
    @GET("/shows/{show}/seasons?extended=full,images")
    void getSeasons(
            @Path("show") String show,
            Callback<List<Season>> callback);
}

