package com.movile.up.seriestracker.interfaces;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.movile.up.seriestracker.business.connection.ConnectionManager;
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

            "trakt-api-version: "+"2",
            "trakt-api-key: " +"f5e0f8914a5b9f0c9f2cbbf6372ee143bfb51a2d294c68624478da8a3757a6d6"
    })
    @GET("/shows/{show}/seasons/{season}/episodes/{episode}?extended=full,images")
    void getEpisodeDetails(
            @Path("show") String show,
            @Path("season") String season,
            @Path("episode") String episode,
            Callback<Episode> callback);
}
