package com.movile.up.seriestracker.interfaces.rest;

import com.movile.up.seriestracker.util.ApiConfiguration;
import com.movile.up.seriestracker.model.models.Episode;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by root on 16/07/15.
 */
public interface EpisodeRemoteService {

    @Headers({
            "trakt-api-key: " + ApiConfiguration.API_KEY,
            "trakt-api-version: " + ApiConfiguration.API_VERSION
      })

    @GET("/shows/{show}/seasons/{season}/episodes/{episode}?extended=full,images")
    void getEpisodeDetails(
            @Path("show") String show,
            @Path("season") long season,
            @Path("episode") long episode,
            Callback<Episode> callback);
}
