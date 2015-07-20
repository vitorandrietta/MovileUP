package com.movile.up.seriestracker.interfaces.rest;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.movile.up.seriestracker.business.connection.ConnectionManager;
import com.movile.up.seriestracker.configuration.ApiConfiguration;
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

            "trakt-api-version: "+ ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}/seasons/{season}/episodes/{episode}?extended=full,image")
    void getEpisodeDetails(
            @Path("show") String show,
            @Path("season") long season,
            @Path("episode") long episode,
            Callback<Episode> callback);
}
